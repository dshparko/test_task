package by.dshparko.notificationservice.util;

import by.dshparko.notificationservice.model.UserEvent;
import org.springframework.stereotype.Component;

@Component
public class NotificationSubjectBuilder {

    public String build(UserEvent event) {
        return switch (event.action().name().toUpperCase()) {
            case "CREATE" -> String.format("User with username %s was successfully created.", event.username());
            case "UPDATE" -> String.format("User with username %s was successfully updated.", event.username());
            case "DELETE" -> String.format("User with username %s was successfully deleted.", event.username());
            default -> "Incorrect parameters";
        };
    }

}
