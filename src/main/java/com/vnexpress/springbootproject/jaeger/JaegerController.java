package com.vnexpress.springbootproject.jaeger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jaeger/client")
public class JaegerController {
    private JaegerService jaegerService;

    public JaegerController(JaegerService jaegerService) {
        this.jaegerService = jaegerService;
    }

    @GetMapping("/{id}")
    public Mono<String> get(@PathVariable("id") Long id) {
        return jaegerService.get(id);
    }
}
