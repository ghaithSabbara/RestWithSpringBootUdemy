package com.restapi.restwithspringbootudemy.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.restapi.restwithspringbootudemy.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("application/x-yaml");

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE",
				"CONNECT");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configuer) {

// Via Extension Parameter : http://localhost:8080/api/person/v1.json
//		configuer.favorParameter(false).defaultContentType(MediaType.APPLICATION_JSON)
//				.mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);

// Query Parameter http://localhost:8080/api/person/v1?mediaType=xml
//		configuer.favorPathExtension(false).useRegisteredExtensionsOnly(false).favorParameter(true)
//				.ignoreAcceptHeader(true).parameterName("mediaType").defaultContentType(MediaType.APPLICATION_JSON)
//				.mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);

// Header Parameter in accept param of Header
		configuer.favorPathExtension(false).useRegisteredExtensionsOnly(false).favorParameter(false)
				.ignoreAcceptHeader(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("x-yaml", MEDIA_TYPE_YAML);

	}
}
