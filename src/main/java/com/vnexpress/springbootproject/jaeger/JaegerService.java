package com.vnexpress.springbootproject.jaeger;

import com.vnexpress.springbootproject.jaeger.JaegerConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JaegerService {
    private WebClient webClient;

    public JaegerService(WebClient webClient) {
        this.webClient = webClient;
    }
    public Mono<String> get(Long id){
        return webClient.get().uri("localhost:8081/recieve/"+id)
                .retrieve().bodyToMono(String.class);

    }
}
