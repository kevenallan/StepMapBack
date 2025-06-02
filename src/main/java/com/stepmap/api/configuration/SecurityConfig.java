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
        CorsConfiguration corsConfig = new CorsConfiguration();

        // Mais flexível que addAllowedOrigin
        corsConfig.addAllowedOriginPattern("https://stepmap-sm.web.app");
        
        corsConfig.addAllowedMethod("*");   // libera todos métodos
        corsConfig.addAllowedHeader("*");   // libera todos headers
        corsConfig.setAllowCredentials(true);  // se não usar credenciais pode ser false

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        System.out.println("CorsWebFilter carregado com origem: https://stepmap-sm.web.app");
        return new CorsWebFilter(source);
    }
}
