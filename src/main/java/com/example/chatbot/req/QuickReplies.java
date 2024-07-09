package com.example.chatbot.req;

import lombok.Data;

@Data
public class QuickReplies {

   private String label;
   private String action;
   private String messageText;
   private String blockId;
   
   
   // 이건 내맘대로 타임변경 가능
   private String extra;

}
