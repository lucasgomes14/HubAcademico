package com.academichub.AcademicHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "tb_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "moderator", nullable = false)
    private String moderator;
    
}