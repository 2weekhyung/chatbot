package com.example.chatbot.model;

import lombok.Data;

@Data
public class Producet {
    private int OrderNo;                    // 흠
    private int ProductNo;                  // 상품번호
    private String ProductName;             // 상품 이름
    private String ProductPrict;            // 상품설명
    private String ProductImg;              // 이미지 주소
    private int OrderPrice;                 // 상품 가격
    private int DiscountPrice;              // 상품 할인 가격

    private String BotUserId;               // 유저 정보
    private String OrderName;               // 주문자 이름
    private String OrderPhone;              // 주문자 전화번호
    private String OrderStatus;             // 주문 상태
    private String ReservrDate;             // 예약일자
    private String OrderDate;               // 주문일자

    public Producet(){}


    public Producet( String ProductName, String ProductPrict, int OrderPrice,int DiscountPrice, String ProductImg)
    {

        this.ProductName = ProductName;
        this.ProductPrict = ProductPrict;
        this.OrderPrice = OrderPrice;
        this.DiscountPrice = DiscountPrice;
        this.ProductImg = ProductImg;


    }


}
