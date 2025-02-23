package com.academichub.AcademicHub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academichub.AcademicHub.util.AuthUtil;
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
        System.out.println("Notificação criada: " + notification);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        userId = AuthUtil.getAuthenticatedUserId(); 

        if (userId == null) {
            System.out.println("Erro: Usuário não autenticado!");
            return List.of();
        }

        List<Notification> notifications = notificationRepository.findByUserIdAndReadFalse(userId);
        System.out.println("Buscando notificações não lidas para userId: " + userId);
        System.out.println("Notificações encontradas: " + notifications);
        return notifications;
    }

    public void markAllNotificationsAsRead(Long userId) {
        // Obtém todas as notificações não lidas para o usuário
        List<Notification> unreadNotifications = notificationRepository.findByUserIdAndReadFalse(userId);
        
        // Marca todas como lidas
        for (Notification notification : unreadNotifications) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }
}
