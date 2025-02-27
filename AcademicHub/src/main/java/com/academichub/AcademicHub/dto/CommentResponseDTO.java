package com.academichub.AcademicHub.dto;

import java.time.LocalDateTime;

public record CommentResponseDTO(String comment,
                                 String name,
                                 LocalDateTime dateTime) {
}
