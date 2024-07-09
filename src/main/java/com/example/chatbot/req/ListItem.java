package com.example.chatbot.req;

import lombok.Data;

import java.util.Map;

@Data
public class ListItem {
    private String title;                       // 제목
    private String description;                 // 설명
    private String imageUrl;                    // 이미지 주소
    private Link link;                          // 클릭시 작동하는 링크
    private String action;                      // 클릭시 수행할 액션
    private String blockId;                     // blockId를 갖는 블록을 호출합니다. (바로가기 응답의 블록 연결 기능과 동일)  items의 title이 사용자의 발화로 나가게 됩니다
    private String messageText;                 // 사용자의 발화로 messageText를 내보냅니다. (바로가기 응답의 메세지 연결 기능과 동일)
    private Map<String,Object> extra;           // block이나 message action으로 블록 호출시, 스킬 서버에 추가적으로 제공하는 정보



}
