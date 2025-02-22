package com.academichub.AcademicHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academichub.AcademicHub.model.notification.Notification;
import com.academichub.AcademicHub.repository.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(Long userId, String action, Long referenceId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setAction(action);
        notification.setReferenceId(referenceId);
        notification.setRead(false);
        notificationRepository.save(notification);  
    }
}
