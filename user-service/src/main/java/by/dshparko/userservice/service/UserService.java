package by.dshparko.userservice.service;

import by.dshparko.userservice.database.entity.User;
import by.dshparko.userservice.database.repository.UserRepository;
import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.exception.UserNotFoundException;
import by.dshparko.userservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserDto> findAllUsers() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public UserDto getUser(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public UserDto createUser(UserDto dto) {
        User user = repository.save(mapper.toEntity(dto));
        return mapper.toDto(user);

    }

    @Transactional
    public UserDto updateUser(Long id, UserDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setUsername(dto.username());
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        return mapper.toDto(repository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
