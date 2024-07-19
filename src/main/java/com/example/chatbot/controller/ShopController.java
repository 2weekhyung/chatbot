package com.example.chatbot.controller;

import com.example.chatbot.model.*;
import com.example.chatbot.req.*;
import com.example.chatbot.res.*;
import com.example.chatbot.service.ShopService;
import com.example.chatbot.service.productService;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/jushop")
public class ShopController {
    private final Log log = LogFactory.getLog(ShopController.class);

    public static final String REGEXP_KR = "^[가-힣]*$";
    public static final String REGEXP_Phone="^[\\d]{11}+$";

    @Autowired
    private productService productService;
    @Autowired
    private ShopService shopService;

    /**
     * 쇼핑 메인화면(66864b145be16c33805ac6bb)
     * @param kareq
     * @return
     */
    @RequestMapping("/start")
    public KakaoResDto start(@RequestBody KakaoReqDto kareq) {
        log.info("/jushop/start");

        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        KakaoBasicCardtResDto BasicCardDto = new KakaoBasicCardtResDto();
        Map<String,Object> ItemCardMap = new HashMap<>();

        // 제목 및 설명
        BasicCardDto.setTitle("춘식이 가게");
        BasicCardDto.setDescription("저희 가게에 방문해주셔서 감사합니다!!☺");


        // 썸네일 설정
        Thumbnail th = new Thumbnail();
            th.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeiIRbKS632gPBbPwiuC3G0g9vT22CJl-KWQ&s");
        BasicCardDto.setThumbnail(th);


        // 버튼 설정
        Button bu = new Button();
            bu.setLabel("상품보러가기");
            bu.setAction("block");
            bu.setBlockId(Block.list);

            //bu.setBlockId("66864ae45be16c33805ac6b8");

        Button bu1 = new Button();
            bu1.setLabel("주문내역 보기");
            bu1.setAction("block");
            bu1.setBlockId(Block.orderlist);

        List<Button> buttons = new ArrayList<>();
            buttons.add(bu);
            buttons.add(bu1);
        BasicCardDto.setButtons(buttons);


        // 모양에 맞춰서 넣기
        ItemCardMap.put("basicCard", BasicCardDto);
        outputs.add(ItemCardMap);


        // 퀵 버튼
        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies bu3 = new QuickReplies();

        bu3.setAction("block");
        bu3.setLabel("처음으로 가기");
        bu3.setBlockId("6683638bd8c1fc74f973f8ad");

        Quicklist.add(bu3);

        return kaResDto;

    }


    /**
     * 쇼핑 리스트(66864ae45be16c33805ac6b8)
     * @param kareq
     * @return
     */
    @RequestMapping("/list")
    public KakaoResDto list(@RequestBody KakaoReqDto kareq) {
        log.info("/jushop/list");

        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        Map<String,Object> calCardMap = new HashMap<>();
        KakaoCarouselCardtResDto  carousel = new KakaoCarouselCardtResDto();
        carousel.setType("commerceCard");

        // 상품 정보 담을 리스트
        List<Object> Itmes = carousel.getItems();
        
        /////////////
        KakaoCommerceCardResDto commerResDto = new KakaoCommerceCardResDto();
        // 상품이름 및 설명
        commerResDto.setTitle("오므라이스 키링");
        commerResDto.setDescription("맛있는 키링 오므라이스☺");
        commerResDto.setPrice(5000);
        commerResDto.setCurrency("won");
        commerResDto.setDiscount(500);

        // 썸네일 설정
        Thumbnail th = new Thumbnail();
            th.setImageUrl("https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png");
            List<Thumbnail> Thumbnails = commerResDto.getThumbnails();
            Thumbnails.add(th);
        commerResDto.setThumbnails(Thumbnails);

        // 프로필 설정
        Profile pro = new Profile();
            pro.setImageUrl("https://st.kakaocdn.net/shoppingstore/store/home/brand/20240522095824_a8c96efa9b7544158660ce85c6a1d99a.png");
            pro.setNickname("춘식이");
        commerResDto.setProfile(pro);


        // 버튼 설정
        Button bu = new Button();
            bu.setLabel("상품 상세보기");
            bu.setAction("block");
        bu.setBlockId(Block.detail);

        // extra ?
        Map<String, Object> extra = new HashMap<>();
            extra.put("Title",commerResDto.getTitle());
            extra.put("userId",kareq.getUserRequest().getUser().getId());

            extra.put("Description",commerResDto.getDescription());
            extra.put("Price",commerResDto.getPrice());
            extra.put("Discount",commerResDto.getDiscount());
            extra.put("ImageUrl",th.getImageUrl());
            extra.put("proNo",1);
        bu.setExtra(extra);


        List<Button> buttons = new ArrayList<>();
        buttons.add(bu);
        commerResDto.setButtons(buttons);

        Itmes.add(commerResDto);
        ///////////////

        //두번째 상품
        //////////////
        KakaoCommerceCardResDto commerResDto1 = new KakaoCommerceCardResDto();
        // 상품이름 및 설명
        commerResDto1.setTitle("안경닦이 ");
        commerResDto1.setDescription("6컷 춘식 안경닦이");
        commerResDto1.setPrice(5000);
        commerResDto1.setCurrency("won");
        commerResDto1.setDiscount(500);

        // 썸네일 설정
        Thumbnail th1 = new Thumbnail();
        th1.setImageUrl("https://st.kakaocdn.net/thumb/P750x750/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fshoppingstore%2Fproduct%2F20240621172756_874016ce6bcf468e96095a37a4177d0f.jpg");
        List<Thumbnail> Thumbnails1 = commerResDto1.getThumbnails();
        Thumbnails1.add(th1);
        commerResDto1.setThumbnails(Thumbnails1);

        // 프로필 설정
        Profile pro1 = new Profile();
        pro1.setImageUrl("https://st.kakaocdn.net/shoppingstore/store/home/brand/20240304093026_c88ebabcad5849e48120613ac136fae6.jpg");
        pro1.setNickname("춘식이");
        commerResDto1.setProfile(pro1);

        // 버튼 설정
        Button bu1 = new Button();
        bu1.setLabel("상품 상세보기");
        bu1.setAction("block");
        bu1.setBlockId(Block.detail);


        // extra 설정
        Map<String, Object> extra2 = new HashMap<>();
            extra2.put("Title",commerResDto1.getTitle());
            extra2.put("userId",kareq.getUserRequest().getUser().getId());
            extra2.put("Description",commerResDto1.getDescription());
            extra2.put("Price",commerResDto1.getPrice());
            extra2.put("Discount",commerResDto1.getDiscount());
            extra2.put("ImageUrl",th1.getImageUrl());
            extra2.put("proNo",2);
        bu1.setExtra(extra2);

        List<Button> buttons1 = new ArrayList<>();
        buttons1.add(bu1);
        commerResDto1.setButtons(buttons1);

        Itmes.add(commerResDto1);
        //////////////


        // 3번쨰 상품
        //////////////
        KakaoCommerceCardResDto commerResDto2 = new KakaoCommerceCardResDto();
        // 상품이름 및 설명
        commerResDto2.setTitle("쏘주 디스펜서");
        commerResDto2.setDescription("춘식 소주 디스펜서!!");
        commerResDto2.setPrice(5000);
        commerResDto2.setCurrency("won");
        commerResDto2.setDiscount(500);

        // 썸네일 설정
        Thumbnail th2 = new Thumbnail();
        th2.setImageUrl("https://st.kakaocdn.net/thumb/R890x0/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fshoppingstore%2Feditor%2F20240625171656_ddfb9390758c46dda5630a79ff432b08.jpg");
        List<Thumbnail> Thumbnails2 = commerResDto2.getThumbnails();
        Thumbnails2.add(th2);
        commerResDto2.setThumbnails(Thumbnails2);

        // 프로필 설정
        Profile pro2 = new Profile();
        pro2.setImageUrl("https://st.kakaocdn.net/thumb/C400x400/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fshoppingstore%2Fstore%2F20240528180123_b9a6145839c346e3a34304f14163d941.png");
        pro2.setNickname("춘식이");
        commerResDto2.setProfile(pro2);


        // 버튼 설정
        Button bu2 = new Button();
        bu2.setLabel("상품 상세보기");
        bu2.setAction("block");
        bu2.setBlockId(Block.detail);

        // extra 설정
        Map<String, Object> extra3 = new HashMap<>();
            extra3.put("Title",commerResDto2.getTitle());
            extra3.put("userId",kareq.getUserRequest().getUser().getId());
            extra3.put("Description",commerResDto2.getDescription());
            extra3.put("Price",commerResDto2.getPrice());
            extra3.put("Discount",commerResDto2.getDiscount());
            extra3.put("ImageUrl",th2.getImageUrl());
            extra3.put("proNo",3);
        bu2.setExtra(extra3);


        List<Button> buttons2 = new ArrayList<>();
        buttons2.add(bu2);
        commerResDto2.setButtons(buttons2);

        Itmes.add(commerResDto2);
        /////////////

        calCardMap.put("carousel",carousel);
        outputs.add(calCardMap);

        // 퀵 버튼
        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies bu3 = new QuickReplies();

        bu3.setAction("block");
        bu3.setLabel("메인화면");
        bu3.setBlockId(Block.mainhome);

        Quicklist.add(bu3);

        return kaResDto;
    }


    /**
     * 쇼핑 디테일(6686631b5be16c33805acb95)
     * @param kareq
     * @return
     */
    @RequestMapping(value="detail")
    public KakaoResDto detail(@RequestBody KakaoReqDto kareq ){
        log.info("/jushop/detail");
        log.info(kareq);

        // kareq.getAction().getParams()        // 파라미터 얻어오는 거

        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        KakaoItemCardResDto ItemCardDto = new KakaoItemCardResDto();

        // extra 값 가져오기
        Map<String,Object> extraMap = kareq.getAction().getClientExtra();

        // 제목 설명
        ImageTitle imageTitle = new ImageTitle();
        imageTitle.setTitle(extraMap.get("Title").toString());
        imageTitle.setDescription(extraMap.get("Description").toString());
        ItemCardDto.setImageTitle(imageTitle);

        // 썸네일 설정
        Thumbnail th = new Thumbnail();
        th.setImageUrl(extraMap.get("ImageUrl").toString());
        th.setWidth(800);
        th.setHeight(800);
        ItemCardDto.setThumbnail(th);

        // 아이템 리스트 설정
        List<ItemList> itemLists = ItemCardDto.getItemList();
        ItemList item = new ItemList();
        item.setTitle("할인된 금액");
        item.setDescription(String.valueOf (Integer.parseInt(extraMap.get("Price").toString()) - Integer.parseInt(extraMap.get("Discount").toString())));
        itemLists.add(item);

        // 리스트 정렬
        ItemCardDto.setItemListAlignment("right");

        // 버튼 설정
        List<Button> buttons = new ArrayList<>();
        Button bu = new Button();
        bu.setLabel("구매");
        bu.setAction("block");
        bu.setBlockId(Block.order);
        bu.setExtra(kareq.getAction().getClientExtra());

        // extra 설정
        bu.setExtra(extraMap);
        buttons.add(bu);

        Button bu1 = new Button();
        bu1.setLabel("뒤로가기");
        bu1.setAction("block");
        bu1.setBlockId(Block.list);
        buttons.add(bu1);
        ItemCardDto.setButtons(buttons);

        // 버튼 정렬
        ItemCardDto.setButtonLayout("vertical");

        Map<String,Object> ItemCardMap = new HashMap<>();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        ItemCardMap.put("itemCard",ItemCardDto);
        outputs.add(ItemCardMap);
        return kaResDto;
    }

    /**
     *  쇼핑 주문 (6687579af2b2f84cb556a2b4)
     * @param kareq
     * @return
     */
    @RequestMapping(value="/order")
    public KakaoResDto order(@RequestBody KakaoReqDto kareq ){
        log.info("/jushop/order");
        log.info(kareq);

        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        KakaoTextCardtResDto kaTextDTO = new KakaoTextCardtResDto();
        Map<String,Object> TextCardMap = new HashMap<>();
        Map<String,Object> BasicCardMap = new HashMap<>();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();


        // extra 값 가져오기
        Map<String,Object> extras = kareq.getAction().getClientExtra();
        log.info("-------------");
        log.info(extras.toString());
        log.info("-------------");

        // extra 값 유효성 검사
        if(extras.get("Title") == null || extras.get("Title").toString().isEmpty()){

            kaTextDTO.setTitle("이름 정보에서 오류가 발생했습니다...!");

            // 이전 버튼 설정
            List<Button> errButtons = new ArrayList<>();
            Button ErrorBu = new Button();
            ErrorBu.setLabel("이전으로");
            ErrorBu.setBlockId(Block.detail);
            errButtons.add(ErrorBu);
            kaTextDTO.setButtons(errButtons);

            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }


        if(extras.get("userId") == null || extras.get("userId").toString().isEmpty()){

            kaTextDTO.setTitle("사용자 정보에서 오류가 발생했습니다...!");
            // 이전 버튼 설정
            List<Button> errButtons = new ArrayList<>();
            Button ErrorBu = new Button();
                ErrorBu.setLabel("이전으로");
                ErrorBu.setBlockId(Block.detail);
            errButtons.add(ErrorBu);
            kaTextDTO.setButtons(errButtons);

            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }



        if(extras.get("Description") == null || extras.get("Description").toString().isEmpty()){

            kaTextDTO.setTitle("상품 설명 정보에서 오류가 발생했습니다...!");

            // 이전 버튼 설정
            List<Button> errButtons = new ArrayList<>();
            Button ErrorBu = new Button();
            ErrorBu.setLabel("이전으로");
            ErrorBu.setBlockId(Block.detail);
            errButtons.add(ErrorBu);
            kaTextDTO.setButtons(errButtons);
            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }
        if(extras.get("Price") == null || extras.get("Price").toString().isEmpty()){

            kaTextDTO.setTitle("상품 가격 정보에서 오류가 발생했습니다...!");


            // 이전 버튼 설정
            List<Button> buttons = new ArrayList<>();
            Button ErrorBu = new Button();
            ErrorBu.setLabel("이전으로");
            ErrorBu.setBlockId(Block.detail);
            buttons.add(ErrorBu);
            kaTextDTO.setButtons(buttons);
            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }
        if(extras.get("ImageUrl") == null || extras.get("ImageUrl").toString().isEmpty()){

            kaTextDTO.setTitle("상품 이미지 정보에서 오류가 발생했습니다...!");

            // 이전 버튼 설정
            List<Button> buttons = new ArrayList<>();
            Button ErrorBu = new Button();
            ErrorBu.setLabel("이전으로");
            ErrorBu.setBlockId(Block.detail);
            buttons.add(ErrorBu);
            kaTextDTO.setButtons(buttons);

            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }
        if(extras.get("proNo") == null || extras.get("proNo").toString().isEmpty()){

            kaTextDTO.setTitle("상품 번호 정보에서 오류가 발생했습니다...!");


            // 이전 버튼 설정
            List<Button> buttons = new ArrayList<>();
            Button ErrorBu = new Button();
            ErrorBu.setLabel("이전으로");
            ErrorBu.setBlockId(Block.detail);
            buttons.add(ErrorBu);
            kaTextDTO.setButtons(buttons);
            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }


        Map<String,String> parmMAp = kareq.getAction().getParams();

        // 파라미터 값 유효성 검사
        if(parmMAp.get("order_name") == null || parmMAp.get("order_name").toString().isEmpty()){
            kaTextDTO.setTitle("주문자 이름 정보에서 오류가 등장했네요...!");

            // 버튼 설정
            List<Button> buttons = new ArrayList<>();
            Button ErrorBu = new Button();
            ErrorBu.setLabel("다시하기");
            ErrorBu.setBlockId(Block.order);
            buttons.add(ErrorBu);
            kaTextDTO.setButtons(buttons);

            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }
        if(parmMAp.get("order_phone") == null || parmMAp.get("order_phone").toString().isEmpty()){
            kaTextDTO.setTitle("주문자 전화번호 정보에서 오류가 등장했네요...!");

            // 버튼 설정
            List<Button> buttons = new ArrayList<>();
            Button ErrorBu = new Button();
            ErrorBu.setLabel("다시하기");
            ErrorBu.setBlockId(Block.order);
            buttons.add(ErrorBu);
            kaTextDTO.setButtons(buttons);
            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }
        if(parmMAp.get("reser_date") == null || parmMAp.get("reser_date").toString().isEmpty()){
            kaTextDTO.setTitle("날짜 정보에서 오류가 등장했네요...!");

            // 버튼 설정
            List<Button> buttons = new ArrayList<>();
            Button ErrorBu = new Button();
            ErrorBu.setLabel("다시하기");
            ErrorBu.setBlockId(Block.order);
            buttons.add(ErrorBu);
            kaTextDTO.setButtons(buttons);
            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }


        // 날짜 역직렬화 하기
        Gson gson = new Gson();
        //log.info(gson.toJson(params));
        log.info(gson.toJson(extras));

        // 파라미터 값 가져오기
        KakaoDate kakaoDate = gson.fromJson(parmMAp.get("reser_date").toString(),KakaoDate.class);
        String deliverDate = kakaoDate.getValue();

        String error ="";
        Boolean isValidTypeKr= Pattern.matches(REGEXP_KR, parmMAp.get("order_name"));
        Boolean isValidTypePhone= Pattern.matches(REGEXP_Phone, parmMAp.get("order_phone"));
        if(!isValidTypeKr){
            // 텍스트 박스
            error="이름을 제대로 입력해주세요";
            kaTextDTO.setTitle("에러 발생");
            kaTextDTO.setDescription(error);
                // 버튼 설정
                List<Button> buttons = new ArrayList<>();
                Button bu = new Button();
                bu.setLabel("다시 입력하기");
                bu.setAction("block");
                bu.setBlockId(Block.order);
                buttons.add(bu);

                Button bu1 = new Button();
                bu1.setLabel("뒤로가기");
                bu1.setAction("block");
                bu1.setBlockId(Block.list);
                buttons.add(bu1);

                // extra 설정
                Map<String, Object> extra = new HashMap<>();
                extra.put("Title",extras.get("Title").toString());
                extra.put("userId",kareq.getUserRequest().getUser().getId());
                extra.put("Description",extras.get("Description").toString());
                extra.put("Price",extras.get("Price").toString());
                extra.put("ImageUrl",extras.get("ImageUrl").toString());
                extra.put("Discount",extras.get("Discount").toString());
                extra.put("proNo",extras.get("proNo").toString());

                bu.setExtra(extra);
                kaTextDTO.setButtons(buttons);
            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }
        if(!isValidTypePhone){
            // 텍스트 박스
            error ="전화번호를 제대로 입력해주세요 혹은 '-' 빼고 입력해주세요";
            kaTextDTO.setTitle("에러 발생");
            kaTextDTO.setDescription(error);
                // 버튼 설정
                List<Button> buttons = new ArrayList<>();
                Button bu = new Button();
                bu.setLabel("다시 입력하기");
                bu.setAction("block");
                bu.setBlockId(Block.order);
                buttons.add(bu);

                Button bu1 = new Button();
                bu1.setLabel("뒤로가기");
                bu1.setAction("block");
                bu1.setBlockId(Block.list);
                buttons.add(bu1);

                // extra 설정
                    Map<String, Object> extra = new HashMap<>();
                    extra.put("Title",extras.get("Title").toString());
                    extra.put("userId",kareq.getUserRequest().getUser().getId());
                    extra.put("Description",extras.get("Description").toString());
                    extra.put("Price",extras.get("Price").toString());
                    extra.put("ImageUrl",extras.get("ImageUrl").toString());
                    extra.put("Discount",extras.get("Discount").toString());
                    extra.put("proNo",extras.get("proNo").toString());
            bu.setExtra(extra);
            kaTextDTO.setButtons(buttons);
            TextCardMap.put("textCard",kaTextDTO);
            outputs.add(TextCardMap);
            return kaResDto;
        }

        log.info("주문자 정보 출력 시작");
        
        String Test ="주문자:"+parmMAp.get("order_name")+"\n핸드폰 번호 :"+parmMAp.get("order_phone")+"\n예약날짜"+deliverDate;

        // 베이스 카드
        KakaoBasicCardtResDto BaiscCardDto =new KakaoBasicCardtResDto();
            BaiscCardDto.setTitle(extras.get("Title").toString());
            BaiscCardDto.setDescription(extras.get("Description").toString()+"\n"+"가격: "+ extras.get("Price").toString()+"원");
                Thumbnail th = new Thumbnail();
                th.setImageUrl(extras.get("ImageUrl").toString());
            BaiscCardDto.setThumbnail(th);
        BasicCardMap.put("basicCard",BaiscCardDto);
        outputs.add(BasicCardMap);


        // 텍스트 박스
        kaTextDTO.setTitle("주문자 정보");
        kaTextDTO.setDescription(Test);


        // 버튼 설정
        List<Button> buttons = new ArrayList<>();
        Button bu = new Button();
            bu.setLabel("구매가 완료 되었습니다. 주문내역으로 가기");
            bu.setAction("block");
            bu.setBlockId(Block.orderlist);
        buttons.add(bu);
            // extra 설정
            Map<String, Object> extra = new HashMap<>();
                extra.put("Title",extras.get("Title").toString());
                extra.put("userId",kareq.getUserRequest().getUser().getId());
                extra.put("Description",extras.get("Description").toString());
                extra.put("Price",extras.get("Price").toString());
                extra.put("ImageUrl",extras.get("ImageUrl").toString());
                extra.put("orderStatus","01");
            bu.setExtra(extra);
        kaTextDTO.setButtons(buttons);
        log.info("주문자 정보 끝 시작");


        TextCardMap.put("textCard",kaTextDTO);
        outputs.add(TextCardMap);

        log.info("퀵버튼 시작");
        // 퀵 버튼
        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies bu3 = new QuickReplies();

        bu3.setAction("block");
        bu3.setLabel("메인화면");
        bu3.setBlockId(Block.mainhome);

        Quicklist.add(bu3);
        log.info("퀵버튼 끝");
        // 데이터 입력
        Producet producet = new Producet();
        producet.setProductNo(Integer.parseInt(extras.get("proNo").toString()));
        producet.setBotUserId(extra.put("userId",kareq.getUserRequest().getUser().getId()).toString());
        producet.setProductName(extras.get("Title").toString());
        producet.setProductImg(extras.get("ImageUrl").toString());
        producet.setProductPrict(extras.get("Description").toString());

        producet.setOrderPrice(Integer.parseInt(extras.get("Price").toString()));
        producet.setOrderName(parmMAp.get("order_name"));
        producet.setOrderPhone(parmMAp.get("order_phone"));
        producet.setOrderStatus("01");
        producet.setReservrDate(deliverDate);

        shopService.insertOrder(producet);
        log.info(kaResDto);
        return kaResDto;
    }

    /**
     *  주문 내역 (66877fca827d6e0f8f582e72)
     * @param kareq
     * @return
     */
    @RequestMapping(value = "orderlist")
    public KakaoResDto orderlist(@RequestBody KakaoReqDto kareq ){
        log.info("/jushop/orderlist");
        log.info(kareq);

        String userID =kareq.getUserRequest().getUser().getId().toString();

        // 페이징
        Paging paging = new Paging();
        paging.setBotUserId(userID);

        // extra 값 가져오기
        Map<String,Object> extras = kareq.getAction().getClientExtra();
        // 현재 페이지 설정
        if(extras.get("page") != null && !extras.get("page").toString().isEmpty()){
            paging.setPage(Integer.parseInt(extras.get("page").toString()));
        }

        KakaoResDto kaResDto = new KakaoResDto();

        List<Producet> arr = shopService.selectOrder(paging);

        /*
        List<Producet> arr = new ArrayList<>();
        arr = shopService.selectOrder(paging);
        */
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        Map<String,Object> TextMap = new HashMap<>();
        List<Map<String,Object>>outputs=kaTempResDto.getOutputs();

        if(arr.isEmpty()){
            KakaoTextResDto textResDto = new KakaoTextResDto();
            textResDto.setText("주문 하신 내역이 없습니다.");
            TextMap.put("simpleText",textResDto);
            outputs.add(TextMap);
            // 퀵 버튼
            List<Object> Quicklist = kaTempResDto.getQuickReplies();

            QuickReplies bu3 = new QuickReplies();

            bu3.setAction("block");
            bu3.setLabel("메인화면");
            bu3.setBlockId(Block.mainhome);

            Quicklist.add(bu3);

            return kaResDto;
        }
        // 텍스트 박스
        KakaoTextResDto textResDto = new KakaoTextResDto();
        textResDto.setText("주문 내역");
        TextMap.put("simpleText",textResDto);
        outputs.add(TextMap);


        // 케로셀 박스 시작
        KakaoCarouselCardtResDto carouselDto = new KakaoCarouselCardtResDto();
        carouselDto.setType("itemCard");
        List<Object> ListItems = carouselDto.getItems();


        for(Producet product : arr){
            // 아이템 박스
            KakaoItemCardResDto itemCardDto = new KakaoItemCardResDto();
            ImageTitle imageTitle = new ImageTitle();
                imageTitle.setImageUrl(product.getProductImg());
            if(product.getOrderStatus().equals("01")){
                imageTitle.setTitle("접수 중");
            }
            else if(product.getOrderStatus().equals("02")){
                imageTitle.setTitle("주문 취소");
            }
            else if(product.getOrderStatus().equals("03")){
                imageTitle.setTitle("주문 확정");
            }

            itemCardDto.setImageTitle(imageTitle);

            List<ItemList> ItemLists = itemCardDto.getItemList();
            ItemList item = new ItemList();
                item.setTitle("상품명:");
                item.setDescription(product.getProductName());
            ItemLists.add(item);

            ItemList item2 = new ItemList();
                item2.setTitle("상품설명:");
                item2.setDescription(product.getProductPrict());
            ItemLists.add(item2);

            ItemList item3 = new ItemList();
                item3.setTitle("가격:");
                item3.setDescription(String.valueOf(product.getOrderPrice()) +"원");
            ItemLists.add(item3);

            itemCardDto.setItemListAlignment("left");

            // 버튼 설정
            List<Button> buttons = new ArrayList<>();
            Button bu = new Button();
                bu.setLabel("구매 확정");
                bu.setAction("block");
                bu.setBlockId(Block.orderconfrim);
                // extra 설정
                Map<String, Object> extra = new HashMap<>();
                extra.put("orderStatus","03");
                extra.put("ProductNo",product.getOrderNo());
                bu.setExtra(extra);
            buttons.add(bu);

            Button bu2 = new Button();
                bu2.setLabel("구매 취소");
                bu2.setAction("block");
                bu2.setBlockId(Block.orderconfrim);
                // extra 설정
                Map<String, Object> extra1 = new HashMap<>();
                extra1.put("orderStatus","02");
                extra1.put("ProductNo",product.getOrderNo());
                bu2.setExtra(extra1);
            buttons.add(bu2);

            itemCardDto.setButtons(buttons);
            ListItems.add(itemCardDto);

        }
            Map<String,Object> Counsel1dMap = new HashMap<>();
            Counsel1dMap.put("carousel",carouselDto);
            outputs.add(Counsel1dMap);
        // 케로셀 끝
            
            
        // 퀵 버튼
        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies mainQuick = new QuickReplies();
        mainQuick.setAction("block");
        mainQuick.setLabel("메인화면");
        mainQuick.setBlockId(Block.mainhome);
        Quicklist.add(mainQuick);


        // 전체 데이터 세기
        paging.setTotalCnt(shopService.countOrder(userID));
        log.info("전체데이터  값 : " + paging.getTotalCnt());
        // 전체 페이지 개수 구하기
        int totalPage=paging.getPageCount(paging.getPageSize(),paging.getTotalCnt());
        log.info("전체 페이지 값 : " + totalPage);


        // 첫 페이지거나 현재 페이지가 전체 페이지 수보다 적을 때
        if (paging.getPage() == 1) {
            log.info("첫 페이지");

            log.info("다음 페이지 추가");
            QuickReplies nextpage = new QuickReplies();
            Map<String,Object> nextExtra = new HashMap<>();
            nextExtra.put("page",paging.getPage() + 1);
            nextpage.setAction("block");
            nextpage.setLabel("다음");
            nextpage.setBlockId(Block.orderlist);
            nextpage.setExtra(nextExtra);
            Quicklist.add(nextpage);
        }
        // 현재 페이지가 마지막 페이지일 때
        else if (paging.getPage() == totalPage) {
            log.info("현재 페이지가 마지막 페이지");
                QuickReplies previouspage = new QuickReplies();
                Map<String,Object> previousExtra = new HashMap<>();
                previousExtra.put("page",paging.getPage() - 1);

                previouspage.setAction("block");
                previouspage.setLabel("이전");
                previouspage.setBlockId(Block.orderlist);
                Quicklist.add(previouspage);
        }
        else {
            log.info("다음 이전 버튼 둘다 있는 페이지");
            QuickReplies previouspage = new QuickReplies();
            Map<String,Object> previousExtra = new HashMap<>();
            previousExtra.put("page",paging.getPage()-1);

            previouspage.setAction("block");
            previouspage.setLabel("이전");
            previouspage.setBlockId(Block.orderlist);
            Quicklist.add(previouspage);

            QuickReplies nextpage = new QuickReplies();
            Map<String,Object> nextExtra = new HashMap<>();
            nextExtra.put("page",paging.getPage()+1);
            nextpage.setAction("block");
            nextpage.setLabel("다음");
            nextpage.setBlockId(Block.orderlist);
            nextpage.setExtra(nextExtra);
            Quicklist.add(nextpage);
        }

        log.info("return 값 : " + kaResDto);
        return kaResDto;
    }


    /**
     *  주문 수정 (668b8762c9d3e36142490f65)
     * @param kareq
     * @return
     */
    @RequestMapping(value = "confirm")
    public KakaoResDto orderconfirm(@RequestBody KakaoReqDto kareq ){
        log.info("/jushop/confirm");
        log.info(kareq);

        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        KakaoTextCardtResDto kaTextDTO = new KakaoTextCardtResDto();
        Map<String,Object> TextCardMap = new HashMap<>();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();


        // extra 값 가져오기
        Map<String,Object> extras = kareq.getAction().getClientExtra();


        Producet producet = new Producet();
        producet.setOrderStatus(extras.get("orderStatus").toString());
        producet.setOrderNo(Integer.parseInt(extras.get("ProductNo").toString()));
        producet.setBotUserId(kareq.getUserRequest().getUser().getId());


        shopService.updateOrder(producet);

        String a ="";
        if(extras.get("orderStatus").toString().equals("02")){
            a= "주문을 취소 했습니다.";
        }
        else if(extras.get("orderStatus").toString().equals("03")){
            a= "주문을 확정 지었습니다,";
        }

        // 텍스트 박스
        kaTextDTO.setTitle(a);


        // 버튼 설정
        List<Button> buttons = new ArrayList<>();
        Button bu = new Button();
            bu.setLabel("주문 내역 돌아가기");
            bu.setAction("block");
            bu.setBlockId(Block.orderlist);
            buttons.add(bu);
        kaTextDTO.setButtons(buttons);

        TextCardMap.put("textCard",kaTextDTO);
        outputs.add(TextCardMap);

        // 퀵 버튼
        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies bu3 = new QuickReplies();

        bu3.setAction("block");
        bu3.setLabel("메인화면");
        bu3.setBlockId(Block.mainhome);

        Quicklist.add(bu3);

        return kaResDto;
    }

}
