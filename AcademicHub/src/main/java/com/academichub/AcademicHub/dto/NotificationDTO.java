package com.academichub.AcademicHub.dto;

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
