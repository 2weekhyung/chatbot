package com.example.chatbot.req;

import lombok.Data;

@Data
public class Thumbnail {

    private String imageUrl;        // 이미지의 url
    private Link link;              // 이미지 클릭시 작동하는 link
    private boolean fixedRatio;     /// 정사각형 or 와이드형으로 할지 정하는 거

    private int width;         // 이미지의 넓이
    private int height;        // 이미지의 높이


}
