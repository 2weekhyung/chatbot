package com.example.chatbot.req;

import lombok.Data;

import java.util.Map;

@Data
public class Button {
    private String label;                                // 버튼에 적힌 문구
    private String action;                               // 버튼 클릭시 수행될 작업
    private String webLinkUrl;                           // 웹 브라우저를 열고 주소로  이동

    private String messageText;                          // message: 사용자의 발화로 messageText를 내보냅니다
    private String phoneNumber;                          // 전화 번호
    private String blockId;                              // 블럭 id
    private Map<String,Object> extra;                    // 추가적으로 제공하는 정보

    public Button(){};
    public Button(String action, String label, String value){
        this.action = action;
        this.label = label;

        if(action.equals("message")) this.messageText = value;
        else if(action.equals("webLink")) this.webLinkUrl = value;
        else if(action.equals("block")) this.blockId = value;
    };


}
