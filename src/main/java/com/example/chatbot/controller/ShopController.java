package com.example.chatbot.controller;

import com.example.chatbot.model.Block;
import com.example.chatbot.req.*;
import com.example.chatbot.res.*;
import com.example.chatbot.service.productService;
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

@RestController
@RequestMapping("/jushop")
public class ShopController {
    private final Log log = LogFactory.getLog(ShopController.class);

    @Autowired
    private productService productService;

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
            bu.setBlockId("66864ae45be16c33805ac6b8");


        List<Button> buttons = new ArrayList<>();
            buttons.add(bu);
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
            extra.put("Description",commerResDto.getDescription());
            extra.put("Price",commerResDto.getPrice());
            extra.put("Discount",commerResDto.getDiscount());
            extra.put("ImageUrl",th.getImageUrl());
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
            extra2.put("Description",commerResDto1.getDescription());
            extra2.put("Price",commerResDto1.getPrice());
            extra2.put("Discount",commerResDto1.getDiscount());
            extra2.put("ImageUrl",th1.getImageUrl());
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
        commerResDto2.setDescription("춘식 소주 디스펜서 🍶 ");
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
            extra3.put("Description",commerResDto2.getDescription());
            extra3.put("Price",commerResDto2.getPrice());
            extra3.put("Discount",commerResDto2.getDiscount());
            extra3.put("ImageUrl",th2.getImageUrl());
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
        Map<String, Object> extra = new HashMap<>();
            extra.put("Title",imageTitle.getTitle());
            extra.put("Description",imageTitle.getDescription());
            extra.put("Price",item.getDescription());
            extra.put("ImageUrl",th.getImageUrl());
        bu.setExtra(extra);
        buttons.add(bu);
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

        // 베이스 카드
        KakaoBasicCardtResDto BaiscCardDto =new KakaoBasicCardtResDto();
            BaiscCardDto.setTitle(extras.get("Title").toString());
            BaiscCardDto.setDescription(extras.get("Description").toString()+"\n"+"가격: "+ extras.get("Price").toString());
                Thumbnail th = new Thumbnail();
                th.setImageUrl(extras.get("ImageUrl").toString());
            BaiscCardDto.setThumbnail(th);
        BasicCardMap.put("basicCard",BaiscCardDto);
        outputs.add(BasicCardMap);


        // 파라미터 값 가져오기
        Map<String,String> parmMAp = kareq.getAction().getParams();
        String Test ="주문자:"+parmMAp.get("order_name")+"\n핸드폰 번호 :"+parmMAp.get("order_phone")+"\n예약날짜"+parmMAp.get("reser_date");

        // 텍스트 박스
        kaTextDTO.setTitle("주문자 정보");
        kaTextDTO.setDescription(Test);


        // 버튼 설정
        List<Button> buttons = new ArrayList<>();
        Button bu = new Button();
            bu.setLabel("네");
            bu.setAction("block");
            bu.setBlockId(Block.orderlist);
        buttons.add(bu);
            // extra 설정
            Map<String, Object> extra = new HashMap<>();
                extra.put("Title",extras.get("Title").toString());
                extra.put("userId",kareq.getUserRequset().getUser().getId());
                extra.put("Description",extras.get("Description").toString());
                extra.put("Price",extras.get("Price").toString());
                extra.put("ImageUrl",extras.get("ImageUrl").toString());
                extra.put("orderStatus","구매 중");
            bu.setExtra(extra);
        kaTextDTO.setButtons(buttons);

    /*
        Button bu = new Button();
            bu.setLabel("다시 입력하기");
            bu.setAction("block");
            bu.setBlockId(Block.order);
    */

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

    @RequestMapping(value = "orderlist")
    public KakaoResDto orderlist(@RequestBody KakaoReqDto kareq ){
        log.info("/jushop/orderlist");
        log.info(kareq);

        KakaoResDto kaResDto = new KakaoResDto();

        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        Map<String,Object> TextMap = new HashMap<>();
        List<Map<String,Object>>outputs=kaTempResDto.getOutputs();

        // extra 값 가져오기
        Map<String,Object> extras = kareq.getAction().getClientExtra();
        
        // 텍스트 박스
        KakaoTextResDto textResDto = new KakaoTextResDto();
        textResDto.setText("주문 내역");
        TextMap.put("simpleText",textResDto);
        outputs.add(TextMap);


        try {

            // 케로셀 박스
            KakaoCarouselCardtResDto carouselDto = new KakaoCarouselCardtResDto();
            carouselDto.setType("itemCard");
            List<Object> ListItems = carouselDto.getItems();

            // 아이템 박스
            KakaoItemCardResDto itemCardDto = new KakaoItemCardResDto();
            ImageTitle imageTitle = new ImageTitle();
                imageTitle.setImageUrl(extras.get("ImageUrl").toString());
                imageTitle.setTitle(extras.get("orderStatus").toString());
            itemCardDto.setImageTitle(imageTitle);

            List<ItemList> ItemLists = itemCardDto.getItemList();
            ItemList item = new ItemList();
                item.setTitle("상품명:");
                item.setDescription(extras.get("Title").toString());
            ItemLists.add(item);

            ItemList item2 = new ItemList();
                item2.setTitle("상품설명");
                item2.setDescription(extras.get("Description").toString());
            ItemLists.add(item2);

            ItemList item3 = new ItemList();
                item3.setTitle("가격:");
                item3.setDescription(extras.get("Price").toString());
            ItemLists.add(item3);

            itemCardDto.setItemListAlignment("left");

            // 버튼 설정
            List<Button> buttons = new ArrayList<>();
            Button bu = new Button();
                bu.setLabel("구매 확정");
                bu.setAction("block");
                bu.setBlockId(Block.orderlist);
            buttons.add(bu);

            // extra 설정
            Map<String, Object> extra = new HashMap<>();
                extra.put("Title",item.getDescription());
                extra.put("BotId",kareq.getBot().getId());
                extra.put("Description",item2.getDescription());
                extra.put("Price",item3.getDescription());
                extra.put("ImageUrl",imageTitle.getImageUrl());
                extra.put("orderStatus","구매 확정");
            bu.setExtra(extra);


            Button bu2 = new Button();
            bu2.setLabel("구매 취소");
            bu2.setAction("block");
            bu2.setBlockId(Block.orderlist);
            buttons.add(bu2);
            // extra 설정
            Map<String, Object> extra2 = new HashMap<>();
                extra2.put("Title",item.getDescription());
                extra2.put("BotId",kareq.getBot().getId());
                extra2.put("Description",item2.getDescription());
                extra2.put("Price",item3.getDescription());
                extra2.put("ImageUrl",imageTitle.getImageUrl());
                extra2.put("orderStatus","구매 취소");
            bu.setExtra(extra2);
            itemCardDto.setButtons(buttons);
            ListItems.add(itemCardDto);

            Map<String,Object> Counsel1dMap = new HashMap<>();
            Counsel1dMap.put("carousel",carouselDto);
            outputs.add(Counsel1dMap);

            kaTempResDto.setOutputs(outputs);
        }catch (Exception e){
            log.info("ASDDADAS"+e.toString());
        }



        // 퀵 버튼
        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies bu3 = new QuickReplies();

        bu3.setAction("block");
        bu3.setLabel("메인화면");
        bu3.setBlockId(Block.mainhome);

        Quicklist.add(bu3);

        log.info("return 값 : " + kaResDto);
        return kaResDto;
    }



}
