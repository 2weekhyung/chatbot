package com.example.chatbot.res;

import com.example.chatbot.req.CarouselHeader;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KakaoCarouselCardtResDto
{
    private String type;            // 켈로셀 타입

    private List<Object> Items= new ArrayList<>();                 // 케로셀 아이템입니다.

    private CarouselHeader header;                                  // 케로셀의 커버를 제공합니다.

}
