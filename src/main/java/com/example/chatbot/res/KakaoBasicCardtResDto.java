package com.example.chatbot.res;

import com.example.chatbot.req.Button;
import com.example.chatbot.req.Thumbnail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class KakaoBasicCardtResDto {
    private String title;           // 카드의 제목
    private String description;     // 카드에 대한 상세 설명
    private Thumbnail thumbnail;       // 카드의 상단 이미지

    private List<Button> buttons = new ArrayList<>();     // 카드의 버튼을 포함
}
