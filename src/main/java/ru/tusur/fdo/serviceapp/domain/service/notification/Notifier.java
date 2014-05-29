package ru.tusur.fdo.serviceapp.domain.service.notification;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain.service.notification
 * by Oleg Alekseev
 * 29.05.14.
 */
public interface Notifier {

    public void sendNotification(String from, String to, String topic, String body);

}
