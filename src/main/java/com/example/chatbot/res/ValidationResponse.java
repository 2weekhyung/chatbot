package com.example.chatbot.res;

import lombok.Data;

@Data
public class ValidationResponse {
    private String status;
    private String value;
    private Object data;
    private String message;
}
