package com.example.chatbot.req;


import lombok.Data;

@Data
public class UserRequset {
    private String timezone;    // 사용자의 시간대를 반환
    private String callbackUrl; // 콜백 URL
    private Block block;
    private String utterance;   // 봇시스템에 전달된 사용자의 발화
    private String lang;        // 사용자의 언어
    private User user;          // 사용자의 정보

}
