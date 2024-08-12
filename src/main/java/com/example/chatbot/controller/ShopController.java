package com.example.chatbot.controller;

import com.example.chatbot.mapper.ShopMapper;
import com.example.chatbot.model.*;
import com.example.chatbot.model.Block;
import com.example.chatbot.req.*;
import com.example.chatbot.res.*;
import com.example.chatbot.service.CallBackService;
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
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private CallBackService callBackService;

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



        Paging paging = new Paging();


        String pageStr;
        int currentPage =1;

        if (kareq.getAction() != null && kareq.getAction().getClientExtra() != null) {
            Map<String,Object> extraMap = kareq.getAction().getClientExtra();
            Object pageObj = extraMap.get("page");
            if (pageObj != null) {
                try {
                    currentPage = Integer.parseInt(pageObj.toString());
                } catch (NumberFormatException e) {
                    log.info("Current page: " + e);

                }
            }
        }

        paging.setPage(currentPage);
        log.info("Current page: " + currentPage);
        ////

        ////


        // 전체 데이터 세기
        paging.setTotalCnt(shopService.countProduct());
        log.info("전체데이터  값 : " + paging.getTotalCnt());
        // 전체 페이지 개수 구하기
            int totalPage=paging.getPageCount(paging.getPageSize(),paging.getTotalCnt());
            log.info("전체 페이지 값 : " + totalPage);
            log.info("현재 페이지 값 : " + paging.getPage());
            log.info("Start 페이지 값 : " + paging.getStartPage());
            log.info("End 페이지 값 : " + paging.getEndPage());
        //

        // 상품 정보 담을 리스트
        List<Object> Itmes = carousel.getItems();

        List<Producet> producetList = shopService.selectProduct(paging);
        for(Producet p : producetList) {
            KakaoCommerceCardResDto commerResDto = new KakaoCommerceCardResDto();

            // 상품이름 및 설명
            commerResDto.setTitle(p.getProductName());
            commerResDto.setDescription(p.getProductPrict());
            commerResDto.setPrice(p.getOrderPrice());
            commerResDto.setCurrency("won");
            commerResDto.setDiscount(p.getDiscountPrice());

            // 썸네일 설정
            Thumbnail th = new Thumbnail();
                th.setImageUrl(p.getProductImg());
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

            // extra
            Map<String, Object> extra = new HashMap<>();
                extra.put("Title",commerResDto.getTitle());
                extra.put("userId",kareq.getUserRequest().getUser().getId());
                extra.put("Description",commerResDto.getDescription());
                extra.put("Price",commerResDto.getPrice());
                extra.put("Discount",commerResDto.getDiscount());
                extra.put("ImageUrl",th.getImageUrl());
                extra.put("proNo",p.getProductNo());
            bu.setExtra(extra);

            List<Button> buttons = new ArrayList<>();
            buttons.add(bu);
            commerResDto.setButtons(buttons);

            Itmes.add(commerResDto);

        }

        calCardMap.put("carousel",carousel);
        outputs.add(calCardMap);
        // 퀵 버튼
        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies bu3 = new QuickReplies();

        bu3.setAction("block");
        bu3.setLabel("메인화면");
        bu3.setBlockId(Block.mainhome);

            // 첫 페이지거나 현재 페이지가 전체 페이지 수보다 적을 때
            if (paging.getPage() == 1) {
                log.info("첫 페이지");

                log.info("다음 페이지 추가");
                QuickReplies nextpage = new QuickReplies();
                Map<String,Object> nextExtra = new HashMap<>();
                nextExtra.put("page",paging.getPage() +1);
                nextpage.setAction("block");
                nextpage.setLabel("다음");
                nextpage.setBlockId(Block.list);
                nextpage.setExtra(nextExtra);
                Quicklist.add(nextpage);
                log.info("다음 버튼 눌러서 현재 페이지 +1");
            }
            // 현재 페이지가 마지막 페이지일 때
            else if (paging.getPage() == totalPage) {
                log.info("현재 페이지가 마지막 페이지");

                QuickReplies previouspage = new QuickReplies();
                Map<String, Object> previousExtra = new HashMap<>();
                previousExtra.put("page", paging.getPage() - 1);
                previouspage.setAction("block");
                previouspage.setLabel("이전");
                previouspage.setBlockId(Block.list);
                previouspage.setExtra(previousExtra);
                Quicklist.add(previouspage);
                log.info("이전 버튼 눌러서 현재 페이지 -1");
            } else {
                log.info("다음 이전 버튼 둘 다 있는 페이지");

                QuickReplies previouspage = new QuickReplies();
                Map<String, Object> previousExtra = new HashMap<>();
                previousExtra.put("page", paging.getPage() - 1);
                previouspage.setAction("block");
                previouspage.setLabel("이전");
                previouspage.setBlockId(Block.list);
                previouspage.setExtra(previousExtra);
                Quicklist.add(previouspage);
                log.info("이전 버튼 눌러서 현재 페이지 -1");

                QuickReplies nextpage = new QuickReplies();
                Map<String, Object> nextExtra = new HashMap<>();
                nextExtra.put("page", paging.getPage() + 1);
                nextpage.setAction("block");
                nextpage.setLabel("다음");
                nextpage.setBlockId(Block.list);
                nextpage.setExtra(nextExtra);
                Quicklist.add(nextpage);
                log.info("다음 버튼 눌러서 현재 페이지 +1");
            }
        Quicklist.add(bu3);

        //callBackService.reqCallBack(kareq,kaResDto);
        shopService.callboack(kareq.getUserRequest().getCallbackUrl(),kaResDto );
        //return kaResDto;
        return callBackService.callback();
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

            Button bu3 = new Button();
            bu3.setLabel("주문자 정보 수정");
            bu3.setAction("block");
            bu3.setBlockId(Block.orderconfrim);
            // extra 설정
            Map<String, Object> extra3 = new HashMap<>();
            extra3.put("orderStatus","02");
            extra3.put("ProductNo",product.getOrderNo());
            bu3.setExtra(extra3);
            buttons.add(bu3);

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
            nextExtra.put("page",paging.getPage() +1);
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
            Map<String, Object> previousExtra = new HashMap<>();
            previousExtra.put("page", paging.getPage() - 1);
            previouspage.setAction("block");
            previouspage.setLabel("이전");
            previouspage.setBlockId(Block.orderlist);
            previouspage.setExtra(previousExtra);
            Quicklist.add(previouspage);
            log.info("이전 버튼 눌러서 현재 페이지 -1");
        } else {
            log.info("다음 이전 버튼 둘 다 있는 페이지");

            QuickReplies previouspage = new QuickReplies();
            Map<String, Object> previousExtra = new HashMap<>();
            previousExtra.put("page", paging.getPage() - 1);
            previouspage.setAction("block");
            previouspage.setLabel("이전");
            previouspage.setBlockId(Block.orderlist);
            previouspage.setExtra(previousExtra);
            Quicklist.add(previouspage);
            log.info("이전 버튼 눌러서 현재 페이지 -1");

            QuickReplies nextpage = new QuickReplies();
            Map<String, Object> nextExtra = new HashMap<>();
            nextExtra.put("page", paging.getPage() + 1);
            nextpage.setAction("block");
            nextpage.setLabel("다음");

            nextpage.setBlockId(Block.orderlist);
            nextpage.setExtra(nextExtra);
            Quicklist.add(nextpage);
            log.info("다음 버튼 눌러서 현재 페이지 +1");
        }


        //log.info("return 값 : " + kaResDto);
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


    /**
     * 검증 api(이름)
     * @param req
     * @return
     */
    @RequestMapping(value = "/checkName")
    public ValidationResponse checkName(@RequestBody ValidationRequset req)
    {
        log.info("/jushop/checkName");
        log.info(req);
        ValidationResponse res = new ValidationResponse();


        Value value= req.getValue();
        Boolean isValidTypeKr= Pattern.matches(REGEXP_KR, value.getOrigin());
        if(value.getOrigin()== null || value.getOrigin().isEmpty() || !isValidTypeKr)
        {

            res.setStatus("FAIL");
            res.setMessage("주문자 이름이 잘못 되었습니다.");
            res.setValue(value.getOrigin());

        }
        else
        {
            res.setStatus("SUCCESS");
        }
        log.info(res);
        return res;
    }

    /**
     * 검증 api( 핸드폰 번호)
     * @param req
     * @return
     */
    @RequestMapping(value = "/checkPhone")
    public ValidationResponse checkPhone(@RequestBody ValidationRequset req)
    {
        log.info("/jushop/checkName");
        log.info(req);
        ValidationResponse res = new ValidationResponse();


        Value value= req.getValue();
        Boolean isValidTypePhone= Pattern.matches(REGEXP_Phone, value.getOrigin());
        if(value.getOrigin()== null || value.getOrigin().isEmpty() || !isValidTypePhone)
        {

            res.setStatus("FAIL");
            res.setMessage("주문자 번호가 잘못 되었습니다. ( - 제외)");
            res.setValue(value.getOrigin());

        }
        else
        {
            res.setStatus("SUCCESS");
        }
        log.info(res);
        return res;
    }
}

