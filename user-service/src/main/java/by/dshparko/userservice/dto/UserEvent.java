package by.dshparko.userservice.dto;

public record UserEvent(UserAction action,
                        String username,
                        String email,
                        String password) {
}

