package com.example.dish.service;

import com.example.dish.event.DishEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DishEventProducer {

    private static final String TOPIC = "dish-events";
    private final KafkaTemplate<String, DishEvent> kafkaTemplate;

    public DishEventProducer(KafkaTemplate<String, DishEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDishEvent(DishEvent event) {
        // We use the dish ID as the key. This ensures all events for the same dish
        // go to the same partition, preserving order.
        String key = String.valueOf(event.data().id());
        kafkaTemplate.send(TOPIC, key, event);
        System.out.println("Sent dish event: " + TOPIC +  event);
    }
}
