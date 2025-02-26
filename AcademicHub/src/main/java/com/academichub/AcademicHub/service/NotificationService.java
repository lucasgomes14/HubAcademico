package com.academichub.AcademicHub.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.academichub.AcademicHub.dto.NotificationDTO;


public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(String message, String type, String user, String post) {
        NotificationDTO notification = new NotificationDTO(message, type, user, post);
        messagingTemplate.convertAndSend("/notifications", notification);
    }
}
