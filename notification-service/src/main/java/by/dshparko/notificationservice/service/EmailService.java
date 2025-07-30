package by.dshparko.notificationservice.service;

import by.dshparko.notificationservice.model.UserEvent;
import by.dshparko.notificationservice.util.NotificationMessageBuilder;
import by.dshparko.notificationservice.util.NotificationSubjectBuilder;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private final NotificationMessageBuilder messageBuilder;
    private final NotificationSubjectBuilder subjectBuilder;

    public void send(UserEvent event) throws MessagingException {
        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(event.adminEmail());
        helper.setSubject(subjectBuilder.build(event));
        helper.setText(messageBuilder.build(event), false);
        mailSender.send(message);
    }
}

