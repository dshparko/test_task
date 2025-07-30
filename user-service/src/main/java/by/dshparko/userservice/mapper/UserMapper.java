package by.dshparko.userservice.mapper;

import by.dshparko.userservice.database.entity.User;
import by.dshparko.userservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder encoder;

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                encode(user.getPassword()),
                user.getRole()
        );
    }

    public User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.id())
                .username(dto.username())
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .email(dto.email())
                .password(encode(dto.password()))
                .role(dto.role())
                .build();
    }

    public UserDto toEmailDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    private String encode(String password) {
        return password != null ? encoder.encode(password) : null;
    }
}
