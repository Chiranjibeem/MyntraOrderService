package com.rmt.program.RuralMassionTraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebSecurity
@EnableWebMvc
@EnableJpaAuditing
@EnableTransactionManagement
public class RuralMassionTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuralMassionTrainingApplication.class, args);
	}

}
