package com.dgu.wantToGraduate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class WantToGraduateApplication {

	public static void main(String[] args) {
		SpringApplication.run(WantToGraduateApplication.class, args);
	}

}
