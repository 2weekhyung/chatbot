package com.example.chatbot.req;

import lombok.Data;

import java.util.Map;

@Data
public class Action {
    private String id;                      // 스킬의 고유한 식별자
    private String name;                    // 설정된 스킬의 이름 
    private Map<String,String> params;      // 사용자의 발화로부터 추출한 파라미터 정보
    private Map<String,DetailParam> detailParams;      // 사용자의 발화로부터 추출한 파라미터 정보
    private Map<String,Object> clientExtra;      // extra로 넣은 데이터

}
