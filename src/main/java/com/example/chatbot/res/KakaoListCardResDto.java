package com.example.chatbot.res;

import com.example.chatbot.req.Button;
import com.example.chatbot.req.ListItem;
import com.example.chatbot.req.Profile;
import com.example.chatbot.req.Thumbnail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KakaoListCardResDto {

    private ListItem header;                                     // 카드의 상단항목
    private List<ListItem> items = new ArrayList<>();                                      // 카드의 각 아이템
    private List<Button> buttons = new ArrayList<>();

}
