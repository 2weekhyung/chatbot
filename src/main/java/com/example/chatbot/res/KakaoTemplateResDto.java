package com.example.chatbot.res;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class KakaoTemplateResDto {
    private List<Map<String, Object>> outputs = new ArrayList<>();
    private List<Object> QuickReplies = new ArrayList<>();


}
