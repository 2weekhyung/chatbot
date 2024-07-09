package com.example.chatbot.res;

import lombok.Data;

@Data
public class KakaoResDto {
    private String version = "2.0";
    private KakaoTemplateResDto template = new KakaoTemplateResDto();
}
