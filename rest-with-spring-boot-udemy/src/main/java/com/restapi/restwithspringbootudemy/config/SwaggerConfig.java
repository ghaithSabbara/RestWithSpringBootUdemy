package com.restapi.restwithspringbootudemy.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.restapi.restwithspringbootudemy"))
				.paths(PathSelectors.any()).build().tags(new Tag("PersonEndpoint", "Description For Person"))
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("RestFull API With Spring Boot", "Some description about your api", "V1",
				"terms Of Service Url",
				new Contact("Ghaith SABBARA", "www.restapi.restwithspringbootudemy.com", "ghaith.sabara@gmail.com"),
				"license of API", " license Url", Collections.emptyList());
	}
}
