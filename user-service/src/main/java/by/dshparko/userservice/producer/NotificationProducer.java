package by.dshparko.userservice.producer;

import by.dshparko.userservice.dto.UserAction;
import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.dto.UserEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic}")
    private String topic;

    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendUserEvent(UserAction type, UserDto user) throws JsonProcessingException {
        try {
            UserEvent event = new UserEvent(type, user.username(), user.email(), user.password());
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, message);
        } catch (Exception e) {
            throw e;
        }
    }
}
