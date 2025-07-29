package by.dshparko.userservice.service;

import by.dshparko.userservice.database.entity.User;
import by.dshparko.userservice.database.repository.UserRepository;
import by.dshparko.userservice.dto.UserAction;
import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.exception.UserNotFoundException;
import by.dshparko.userservice.mapper.UserMapper;
import by.dshparko.userservice.producer.NotificationProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final NotificationProducer notificationProducer;

    public List<UserDto> findAllUsers() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public UserDto getUser(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public UserDto createUser(UserDto dto) throws JsonProcessingException {
        notificationProducer.sendUserEvent(UserAction.CREATE, dto);
        User user = repository.save(mapper.toEntity(dto));
        return mapper.toDto(user);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto dto) throws JsonProcessingException {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        notificationProducer.sendUserEvent(UserAction.UPDATE, dto);
        user.setUsername(dto.username());
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        return mapper.toDto(repository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) throws JsonProcessingException {
        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        User user = optionalUser.get();
        repository.deleteById(id);

        notificationProducer.sendUserEvent(UserAction.DELETE, mapper.toDto(
                user
        ));
    }

}
