package by.dshparko.userservice.dto;

import by.dshparko.userservice.database.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto(Long id,

                      @Size(min = 4, max = 60, message = "The username should be 4 to 60 characters")
                      String username,

                      @Size(min = 4, max = 60, message = "The firstname should be 4 to 60 characters")
                      String firstname,

                      @Size(min = 4, max = 60, message = "The firstname should be 4 to 60 characters")
                      String lastname,

                      @Email
                      String email,

                      @Size(min = 6, message = "Password must be at least 6 characters")
                      String password,

                      @NotNull(message = "Role is obligatory")
                      Role role) {
}
