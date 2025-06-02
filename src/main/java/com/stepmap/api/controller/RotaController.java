package com.stepmap.api.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ors-api")
@CrossOrigin(origins = "https://stepmap-sm.web.app")
public class RotaController {

    private final WebClient webClient;

    @Value("${ors.api.key}")
    private String orsApiKey;

    public RotaController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openrouteservice.org").build();
    }

    @PostMapping("/v2/directions/driving-car/geojson")
    public Mono<ResponseEntity<Object>> obterRota(@RequestBody Map<String, Object> requestBody) {
        return webClient.post()
                .uri("/v2/directions/driving-car/geojson")
                .header("Authorization", orsApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(Object.class)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.badRequest()
                            .body(Map.of("error", "Erro ao consultar rota: " + e.getMessage())));
                });
    }
}