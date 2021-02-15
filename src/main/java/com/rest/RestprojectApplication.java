package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication

public class RestprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestprojectApplication.class, args);
	}

	/*@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.securitySchemes(Collections.singletonList(new ApiKey(RestSecurityFilter.HTTP_REQUEST_API_KEY, RestSecurityFilter.HTTP_REQUEST_API_KEY, "header")))
				.useDefaultResponseMessages(false);
	}
	private ApiKey apiKey() {
		return new ApiKey("Bearer", "Authorization", "header");
	}*/

}
