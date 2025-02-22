package com.academichub.AcademicHub.service;

import java.util.List;

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

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId);
    }

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }
}
