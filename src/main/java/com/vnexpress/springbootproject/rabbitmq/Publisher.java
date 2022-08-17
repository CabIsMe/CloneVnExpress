package com.vnexpress.springbootproject.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.PostConstruct;

public class Publisher {
    private RabbitTemplate rabbitTemplate;
    private String queue;

    public Publisher(RabbitTemplate rabbitTemplate, String queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @PostConstruct
    public void postMessages() {
        rabbitTemplate.convertAndSend(queue, "1 Pepperoni");
        rabbitTemplate.convertAndSend(queue, "3 Margarita");
        rabbitTemplate.convertAndSend(queue, "1 Ham and Pineapple (yuck)");
    }
}
