package com.meetgenie.backend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {

        System.out.println("Received: " + message);

        return "Server says: " + message;
    }
}