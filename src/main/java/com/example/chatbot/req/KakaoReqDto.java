package com.example.chatbot.req;

import lombok.Data;
import org.apache.catalina.User;
import org.springframework.cglib.core.Block;

@Data
public class KakaoReqDto {
    private Intent intent;
    private Bot bot;
    private Action action;
    private UserRequset userRequset;
    private Block block;
}
