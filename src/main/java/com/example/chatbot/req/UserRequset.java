package com.example.chatbot.req;

import lombok.Data;

import org.springframework.cglib.core.Block;

@Data
public class UserRequset {
    private String timezone;    // 사용자의 시간대를 반환
    private Block block;        // 사용자의 발화에 반응한 블록의 정보
    private String utterance;   // 봇시스템에 전달된 사용자의 발화
    private String lang;        // 사용자의 언어
    private User user;          // 사용자의 정보

}
