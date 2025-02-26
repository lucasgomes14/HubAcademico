package com.academichub.AcademicHub.model.notification;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class NotificationDTO {
    private String message;
    private String type; // like, comment, follow
    private String user;
    private String post;
}
