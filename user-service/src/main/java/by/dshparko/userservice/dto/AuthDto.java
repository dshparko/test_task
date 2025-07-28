package by.dshparko.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthDto(

        @NotBlank(message = "Username must be filled")
        @Size(min = 4, max = 60, message = "The name should be 4 to 50 characters")
        String username,

        @NotBlank(message = "Password must not be empty")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password
) {
}
