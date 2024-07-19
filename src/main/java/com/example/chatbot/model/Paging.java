package com.example.chatbot.model;

import lombok.Data;

@Data
public class Paging {
    private int page=1;                  // 현재 페이지 번호
    private int pageSize =5;            // 페이지당 출력할 데이터 개수
    private int totalCnt;
    private int totalPage;
    private int startPage;
    private int endPage;
    private String BotUserId;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStartPage() {
        return (page - 1) * pageSize ;
    }

    public int getEndPage() {
        return (page * pageSize );
    }

    public int getPageSize() {
        return pageSize;
    }



    // ■ 전체 페이지 수를 구하는 메소드
    // pageSize : 한 페이지에 표시할 데이터(게시물)의 수
    // dataCount : 전체 데이터(게시물) 수
    //                            10                 8
    public int getPageCount(int pageSize, int dataCount) {
        int pageCount = 0;

        pageCount = dataCount / pageSize;

        if (dataCount % pageSize != 0)
            pageCount++;

        return pageCount;
    }



}
