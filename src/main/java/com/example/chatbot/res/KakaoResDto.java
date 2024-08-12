package com.example.chatbot.res;

import lombok.Data;

import java.util.Map;

@Data
public class KakaoResDto {
    private String version = "2.0";
    private boolean useCallback = true;   // 콜백 api 사용 여부
    private KakaoTemplateResDto template = new KakaoTemplateResDto();
    private Map<String,String> data;
}
