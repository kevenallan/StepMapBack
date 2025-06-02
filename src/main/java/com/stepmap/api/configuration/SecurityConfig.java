package com.stepmap.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

	@Bean
	public CorsWebFilter corsWebFilter() {
	    System.out.println("CorsWebFilter foi criado!");

	    CorsConfiguration corsConfig = new CorsConfiguration();
	    
	    // Permite qualquer origem (útil para testes)
	    corsConfig.addAllowedOriginPattern("*");

	    // Permite qualquer método (GET, POST, PUT, DELETE, OPTIONS etc)
	    corsConfig.addAllowedMethod("*");

	    // Permite qualquer header
	    corsConfig.addAllowedHeader("*");
	    
	    // Não permite envio de credenciais (cookies, auth headers)
	    corsConfig.setAllowCredentials(false);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    
	    // Aplica essa config para todas as rotas da API
	    source.registerCorsConfiguration("/**", corsConfig);

	    System.out.println("CorsWebFilter carregado com origem: * (aberto para testes)");
	    return new CorsWebFilter(source);
	}
}
