package com.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enable JPA auditing
public class ArticleAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleAPIApplication.class, args);
	}

}
