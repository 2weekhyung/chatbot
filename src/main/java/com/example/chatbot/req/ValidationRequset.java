package com.example.chatbot.req;

import lombok.Data;

@Data
public class ValidationRequset {
    private Boolean isInSlotFilling;
    private User user;
    private String utterance;
    private Value value;

}
