package by.dshparko.notificationservice.util;

import by.dshparko.notificationservice.model.UserEvent;
import org.springframework.stereotype.Component;

@Component
public class NotificationMessageBuilder {

    public String build(UserEvent event) {
        return switch (event.action().name().toUpperCase()) {
            case "CREATE" ->
                    String.format("Create user with username - %s, password - %s, email - %s.", event.username(), event.password(), event.userEmail());
            case "UPDATE" ->
                    String.format("Update user with username - %s, password - %s, email - %s.", event.username(), event.password(), event.userEmail());
            case "DELETE" ->
                    String.format("Delete user with username - %s, password - %s, email - %s.", event.username(), event.password(), event.userEmail());
            default -> "Incorrect parameters";
        };
    }
}
