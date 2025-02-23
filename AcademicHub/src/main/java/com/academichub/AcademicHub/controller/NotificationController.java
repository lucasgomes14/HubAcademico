package com.academichub.AcademicHub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academichub.AcademicHub.model.notification.Notification;
import com.academichub.AcademicHub.service.NotificationService;
import com.academichub.AcademicHub.util.AuthUtil;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getNotifications(@PathVariable Long userId) {
        return notificationService.getUnreadNotifications(userId);
    } 

    @PostMapping
    public ResponseEntity<?> markAllAsRead() {
        Long userId = AuthUtil.getAuthenticatedUserId(); // Obtém o ID do usuário autenticado a partir do JWT
        if (userId == null) {
            return ResponseEntity.status(401).build(); // Caso o usuário não esteja autenticado
        }
        notificationService.markAllNotificationsAsRead(userId); // Marca todas as notificações como lidas
        return ResponseEntity.ok().build(); // Retorna uma resposta 200 OK
    }
}
