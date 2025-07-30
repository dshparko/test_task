package by.dshparko.userservice.producer;

import by.dshparko.userservice.dto.UserAction;
import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.dto.UserEvent;
import by.dshparko.userservice.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final AdminService adminService;

    @Value("${kafka.topic}")
    private String topic;

    public void sendUserEvent(UserAction type, UserDto user) throws JsonProcessingException {
        List<String> adminEmails = adminService.getAdminEmails();

        for (String admin : adminEmails) {
            UserEvent event = new UserEvent(type, user.username(), admin, user.password(), user.email());
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, message);
        }
    }
}
