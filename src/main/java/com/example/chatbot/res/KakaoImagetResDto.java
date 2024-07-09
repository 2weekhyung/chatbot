package com.example.chatbot.res;

import lombok.Data;

@Data
public class KakaoImagetResDto {
    private String imageUrl;    // 이미지 주소
    private String altText;     // url이 유효하지 않는 경우 나오는 텍스트
}
