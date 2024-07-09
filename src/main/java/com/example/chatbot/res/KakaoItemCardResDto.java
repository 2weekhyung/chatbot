package com.example.chatbot.res;

import com.example.chatbot.req.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KakaoItemCardResDto {

    private Thumbnail thumbnail;
    private Head head ;
    private Profile profile ;
    private ImageTitle imageTitle ;
    private List<ItemList> itemList = new ArrayList<>() ;   // 아이템 목록 정보입니다.
    private String itemListAlignment ;                      // "left" 혹은 "right"만 입력 가능
    private ItemListSummary itemListSummary ;               // 아이템 가격 정보입니다.

    private String title ;
    private String description ;
    private List<Button> buttons = new ArrayList<>();
    private String buttonLayout ;                           // 버튼 정렬정보



}

