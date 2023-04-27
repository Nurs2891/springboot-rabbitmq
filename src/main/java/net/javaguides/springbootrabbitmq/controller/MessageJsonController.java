package net.javaguides.springbootrabbitmq.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.springbootrabbitmq.dto.User;
import net.javaguides.springbootrabbitmq.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MessageJsonController {

    private final RabbitMQJsonProducer jsonProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }
}
