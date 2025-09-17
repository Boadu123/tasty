package com.example.notification_service.controller;

import com.example.notification_service.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // This is the endpoint that triggers the notification
    @PostMapping("/send-notification")
    public String sendNotification() {
        // IMPORTANT: In a real app, you get this token from your database for a specific user
        String hardcodedDeviceToken = "DEVICE_TOKEN_FROM_CLIENT_APP";

        try {
            notificationService.sendNotification(
                    hardcodedDeviceToken,
                    "Hello from Spring Boot!",
                    "This is your first push notification!"
            );
            return "Notification sent successfully!";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Error sending notification.";
        }
    }

    @PostMapping("/register-device")
    public String registerDevice(@RequestParam String token, @RequestParam String userId) {
        // In a real app, you'd have a UserService or DeviceService
        // to save this token to your database, associated with the userId.
        System.out.println("Saving token for user: " + userId + ", token: " + token);
        return "Token registered!";
    }
}
