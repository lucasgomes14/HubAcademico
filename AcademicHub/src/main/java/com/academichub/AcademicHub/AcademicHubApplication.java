package com.academichub.AcademicHub;

import com.academichub.AcademicHub.model.Course;
import com.academichub.AcademicHub.model.User;
import com.academichub.AcademicHub.model.UserType;
import com.academichub.AcademicHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class AcademicHubApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AcademicHubApplication.class, args);
	}
	@Bean
	// testes de repository
	CommandLineRunner runner() {
		return args -> {
			User user = new User();

			user.setName("Lucas");
			user.setLastName("Lima");
			user.setDateAndTimeOfUserCreation(LocalDateTime.now());
			user.setEmail("lucas.lima.6@academico.ifpb.edu.br");
			user.setPassword("123");
			user.setUserType(UserType.STUDENT);
			user.setCourse(Course.SYSTEMS_ANALYSIS_AND_DEVELOPMENT);

			userRepository.save(user);
		};
	}
}
