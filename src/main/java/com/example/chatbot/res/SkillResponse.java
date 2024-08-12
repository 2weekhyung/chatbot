package com.example.chatbot.res;

import lombok.Data;

import java.util.Map;

@Data
public class SkillResponse {

    private boolean useCallback = true;   // 콜백 api 사용 여부
    private String version ;
    private KakaoTemplateResDto template;
    private Map<String,String> data;
}
