package com.academichub.AcademicHub.model.user;

import lombok.Getter;

@Getter
public enum Course {
    SYSTEMS_ANALYSIS_AND_DEVELOPMENT("system analysis and development"),
    COMPUTING("computing"),
    RENEWABLE_ENERGY_SYSTEMS("renewable energy systems");

    private final String course;

    private Course(String course) {
        this.course = course;
    }
}