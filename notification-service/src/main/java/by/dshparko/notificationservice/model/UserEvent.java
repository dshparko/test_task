package by.dshparko.notificationservice.model;

public record UserEvent(UserAction action,
                        String username,
                        String adminEmail,
                        String password,
                        String userEmail) {
}

