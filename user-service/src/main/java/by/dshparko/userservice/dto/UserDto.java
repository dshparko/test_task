package by.dshparko.userservice.dto;

import by.dshparko.userservice.database.entity.Role;

public record UserDto(Long id,
                      String username,
                      String firstname,
                      String lastname,
                      String email,
                      String password,
                      Role role) {
}
