package com.springjwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerUIConfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Ecommerce Application Swagger Api").version("1.0").contact(
						new Contact().email("amu9118@gmail.com").name("Amarnath Kothamasu").url("www.url.com"))
						.description("This API documentation provides details for the Ecommerce website, offering various features for online shopping.")
						.summary("List of Endpoint")
						.termsOfService("https://your-ecommerce-website.com/terms-of-service"))
				.components(new Components().addSecuritySchemes("bearer-key",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement().addList("bearer-key"));
	}

}
