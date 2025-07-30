package by.dshparko.userservice.service;

import by.dshparko.userservice.database.entity.Role;
import by.dshparko.userservice.database.entity.User;
import by.dshparko.userservice.database.repository.UserRepository;
import by.dshparko.userservice.dto.UserAction;
import by.dshparko.userservice.dto.CustomUserDetails;
import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.exception.UserNotFoundException;
import by.dshparko.userservice.mapper.UserMapper;
import by.dshparko.userservice.producer.NotificationProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final NotificationProducer notificationProducer;

    public List<UserDto> findAllUsers() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public UserDto getUser(Long id) throws AccessDeniedException {
        verifyAccess(id);
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public UserDto createUser(UserDto dto) throws JsonProcessingException {
        User user = repository.save(mapper.toEntity(dto));
        notificationProducer.sendUserEvent(UserAction.CREATE, mapper.toEmailDto(user));
        return mapper.toDto(user);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto dto) throws JsonProcessingException, AccessDeniedException {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        boolean isAdmin = verifyAccess(user.getId());

        user.setUsername(dto.username());
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(isAdmin ? dto.role() : user.getRole());

        User updated = repository.save(user);
        notificationProducer.sendUserEvent(UserAction.UPDATE, mapper.toEmailDto(updated));

        return mapper.toDto(updated);
    }

    @Transactional
    public void deleteUser(Long id) throws JsonProcessingException, AccessDeniedException {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        verifyAccess(user.getId());
        repository.delete(user);
        notificationProducer.sendUserEvent(UserAction.DELETE, mapper.toEmailDto(user));
    }

    private boolean verifyAccess(Long targetUserId) throws AccessDeniedException {
        User currentUser = getCurrentUser();
        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isSelf = Objects.equals(currentUser.getId(), targetUserId);


        if (!(isAdmin || isSelf)) {
            throw new AccessDeniedException("You can only work with your own account.");
        }
        return isAdmin;
    }

    private User getCurrentUser() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails(User user))) {
            throw new AccessDeniedException("User is not authenticated.");
        }

        return user;
    }
}