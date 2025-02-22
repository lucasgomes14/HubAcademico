package com.academichub.AcademicHub.model.notification;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String action;
    private Long referenceId;
    private Boolean read;
    private LocalDateTime dateCreation = LocalDateTime.now();
}
