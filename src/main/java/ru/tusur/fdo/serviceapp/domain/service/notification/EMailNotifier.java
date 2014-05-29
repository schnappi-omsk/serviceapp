package ru.tusur.fdo.serviceapp.domain.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain.service.notification
 * by Oleg Alekseev
 * 29.05.14.
 */
@Service
public class EMailNotifier implements Notifier {

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendNotification(String from, String to, String topic, String body){
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            message.setSubject(topic, "UTF-8");
            helper.setText(body, true);
            sender.send(message);
        } catch (MessagingException e) {
            //TODO: implement logging
        }
    }

}
