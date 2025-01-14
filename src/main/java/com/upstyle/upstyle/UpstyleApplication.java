package com.upstyle.upstyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaAuditing
public class UpstyleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpstyleApplication.class, args);
	}

}
