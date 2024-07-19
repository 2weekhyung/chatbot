package com.example.chatbot.req;

import lombok.Data;

@Data
public class User {
    private String id;
    private String type;
    private Object properties;
}
