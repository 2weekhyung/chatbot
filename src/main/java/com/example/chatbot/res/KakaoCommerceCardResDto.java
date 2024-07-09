package com.example.chatbot.res;

import com.example.chatbot.req.Button;
import com.example.chatbot.req.Profile;
import com.example.chatbot.req.Thumbnail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KakaoCommerceCardResDto {

    private String title;                                     // 제품의 이름
    private String description;                               // 제품의 설명
    private int price;                                        // 제품의 가격
    private String currency;                                  // 제품의 가격에 대한 통화
    private int discount;                                     // 제품의 가격에 대한 할인할 금액
    private int discountRate;                                 // 제품의 할인율
    private int dicountedPrice;                               // 제품의 가격에 대한 할인된 가격
    private List<Thumbnail> thumbnails = new ArrayList<>();   // 썸네일(제품 사진)
    private Profile profile;                                  // 제품을 판매하는 프로필 정보
    private List<Button> buttons = new ArrayList<>();         // 다양한 액션을 수행할 버튼

    public KakaoCommerceCardResDto(){};
    public KakaoCommerceCardResDto(String title,String description, int price ) {


    }
}
