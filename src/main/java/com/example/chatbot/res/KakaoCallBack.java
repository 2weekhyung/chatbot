package com.example.chatbot.res;

import lombok.Data;

@Data
public class KakaoCallBack {
    private String taskId;  // 해당 리퀘스트에 대한 uid (결과 조회를 위한 id)
    private String status;  // one of ["SUCCESS", "FAIL", "ERROR"]
    private String message; // 상태에 대한 세부 메시지
    private Long timestamp; // task 생성 unixtimestamp

}
