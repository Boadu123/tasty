package com.example.notification_service.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    // This method sends a notification to a specific device token
    public void sendNotification(String token, String title, String body) throws FirebaseMessagingException {

        // Create a new notification
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        // Build the message payload
        Message message = Message.builder()
                .setToken(token) // The recipient's device token
                .setNotification(notification)
                // You can also add custom data here using .putData("key", "value")
                .build();

        // Send the message using the FirebaseMessaging instance
        FirebaseMessaging.getInstance().send(message);
    }
}
