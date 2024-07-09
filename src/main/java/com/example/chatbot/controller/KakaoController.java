package com.example.chatbot.controller;


import com.example.chatbot.req.*;
import com.example.chatbot.res.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/kakao")
public class KakaoController {
    private final Log log = LogFactory.getLog(KakaoController.class);

    @RequestMapping(value = "/text" )
    public KakaoResDto test(@RequestBody KakaoReqDto kareq) throws Exception {
        log.info("/kakao/text");

        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        KakaoTextResDto kaTextResDto = new KakaoTextResDto();

        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        Map<String,Object> textMap = new HashMap<>();

        kaTextResDto.setText("간단한 텍스트 요소입니다.");
        textMap.put("simpleText",kaTextResDto);
        outputs.add(textMap);

        return kaResDto;

    }

    @RequestMapping(value = "/simpleImage" )
    public KakaoResDto Image(@RequestBody KakaoReqDto kareq) throws Exception {
        log.info("/kakao/simpleImage");
        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        KakaoImagetResDto kaImagetResDto = new KakaoImagetResDto();

        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        Map<String,Object> imageMap = new HashMap<>();

        kaImagetResDto.setAltText("보물상자입니다.");
        kaImagetResDto.setImageUrl("https://t1.kakaocdn.net/openbuilder/sample/lj3JUcmrzC53YIjNDkqbWK.jpg");
        imageMap.put("simpleImage",kaImagetResDto);

        outputs.add(imageMap);
        return kaResDto;
    }

    @RequestMapping(value = "/textCard" )
    public KakaoResDto TextCard(@RequestBody KakaoReqDto kareq) throws Exception {
        log.info("/kakao/textCard");
        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        KakaoTextCardtResDto kaTextCardtResDto = new KakaoTextCardtResDto();
        Button bu = new Button();
        Button buu = new Button();

        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        Map<String,Object> textCardMap = new HashMap<>();
        List<Button> buttons = kaTextCardtResDto.getButtons();


        kaTextCardtResDto.setTitle("날씨 알리미에 오신 것을 환영합니다.☺");
        kaTextCardtResDto.setDescription("안녕하세요 오늘의 날씨를 알려줄게요.");

        buu.setAction("webLink");
        buu.setLabel("네이버날씨 보러가기");
        buu.setWebLinkUrl("https://weather.naver.com/today");


        bu.setAction("webLink");
        bu.setLabel("다음날씨 보러가기");
        bu.setWebLinkUrl("https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&q=%EB%82%A0%EC%94%A8");


        buttons.add(bu);
        buttons.add(buu);


        textCardMap.put("textCard",kaTextCardtResDto);

        outputs.add(textCardMap);
        return kaResDto;
    }

    @RequestMapping(value = "/basicCard" )
    public KakaoResDto BasicCard(@RequestBody KakaoReqDto kareq) throws Exception {
        log.info("/kakao/basicCard");
        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();

        KakaoBasicCardtResDto kaBasicCardtResDto = new KakaoBasicCardtResDto();

        Button bu = new Button();
        Button buu = new Button();

        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        Map<String,Object> textCardMap = new HashMap<>();
        List<Button> buttons = kaBasicCardtResDto.getButtons();
        Thumbnail th = new Thumbnail();

        th.setImageUrl("https://t1.kakaocdn.net/openbuilder/sample/lj3JUcmrzC53YIjNDkqbWK.jpg");

        kaBasicCardtResDto.setTitle("보물상자안");
        kaBasicCardtResDto.setDescription("보물상자안에는 무엇이 있을꾜??");
        kaBasicCardtResDto.setThumbnail(th);

        buu.setAction("message");
        buu.setLabel("열어보기");
        buu.setMessageText ("짜잔! 우리가 찾던 보물입니다 🤠");
        

        bu.setAction("webLink");
        bu.setLabel("구경하기");
        bu.setWebLinkUrl("https://e.kakao.com/t/hello-ryan");


        buttons.add(bu);
        buttons.add(buu);


        textCardMap.put("basicCard",kaBasicCardtResDto);

        outputs.add(textCardMap);
        return kaResDto;
    }

    @RequestMapping(value = "/commerceCard" )
    public KakaoResDto commerceCard(@RequestBody KakaoReqDto kareq) throws Exception {
        log.info("/kakao/commerceCard");
        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();

        KakaoCommerceCardResDto commerceCardResDto = new KakaoCommerceCardResDto();

        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        Map<String,Object> textCardMap = new HashMap<>();
        List<Button> buttons = commerceCardResDto.getButtons();

        List<Thumbnail> thlist = commerceCardResDto.getThumbnails();
        Thumbnail th = new Thumbnail();
        Link link = new Link();


        commerceCardResDto.setTitle("빈티지 목재 보물 상자(Medium size)");
        commerceCardResDto.setDescription("이 보물 상자 안에는 무엇이 들어있을까요~!~!~!~!~!~~!😝");
        commerceCardResDto.setPrice(1009000);
        commerceCardResDto.setDiscount(555500);
        commerceCardResDto.setCurrency("won");

        th.setImageUrl("https://t1.kakaocdn.net/openbuilder/sample/lj3JUcmrzC53YIjNDkqbWK.jpg");
        link.setWeb("https://store.kakaofriends.com/kr/products/1542");
        link.setMobile("https://store.kakaofriends.com/kr/products/1542");
        th.setLink(link);
        thlist.add(th);

        commerceCardResDto.setThumbnails(thlist);

        Profile pro = new Profile();
        pro.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4BJ9LU4Ikr_EvZLmijfcjzQKMRCJ2bO3A8SVKNuQ78zu2KOqM");
        pro.setNickname("라이언 스토어");
        commerceCardResDto.setProfile(pro);

        Button bu = new Button();
        Button buu = new Button();
        Button buuu = new Button();

        bu.setLabel("전화하기");
        bu.setAction("phone");
        bu.setPhoneNumber(
                "354-86-000070");

        buu.setLabel("구매하기");
        buu.setAction("webLink");
        buu.setWebLinkUrl("https://store.kakaofriends.com/kr/products/1542");

        buuu.setLabel("공유하기");
        buuu.setAction("share");

        buttons.add(bu);
        buttons.add(buu);
        buttons.add(buuu);


        textCardMap.put("commerceCard",commerceCardResDto);

        outputs.add(textCardMap);
        return kaResDto;
    }

    @RequestMapping(value="/listCard")
    public KakaoResDto listCard(@RequestBody KakaoReqDto kareq){
        log.info("/kakao/listCard");
        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        KakaoListCardResDto listCardDto = new KakaoListCardResDto();

        Map<String,Object> listCardMap = new HashMap<>();

        ListItem listHeader = new ListItem();
        listHeader.setTitle("챗봇 관리자센터를 소개합니다.");
        listCardDto.setHeader(listHeader);

        List<ListItem> list = listCardDto.getItems();
        ListItem item = new ListItem();
        item.setTitle("챗봇 관리자센터");
        item.setDescription("새로운 AI의 내일과 일상의 변화");
        item.setImageUrl("https://t1.kakaocdn.net/openbuilder/sample/img_001.jpg");

        Link link = new Link();
        link.setMobile("https://namu.wiki/w/%EB%9D%BC%EC%9D%B4%EC%96%B8(%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%94%84%EB%A0%8C%EC%A6%88)");
        link.setWeb("https://namu.wiki/w/%EB%9D%BC%EC%9D%B4%EC%96%B8(%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%94%84%EB%A0%8C%EC%A6%88)");
        item.setLink(link);
        list.add(item);


        ListItem item1 = new ListItem();
        item1.setTitle("챗봇 관리자센터");
        item1.setDescription("카카오 채널 챗봇 만들기");
        item1.setImageUrl("https://t1.kakaocdn.net/openbuilder/sample/img_002.jpg");
        item1.setAction("block");
        item1.setBlockId("6683646b9fd56869adfb028e");
        //Map<String,Object> extraMap = new HashMap<>();
        list.add(item1);

        ListItem item2 = new ListItem();
        item2.setTitle("Kakao i Voice Service");
        item2.setDescription("보이스봇 / KVS 제휴 신청하기");
        item2.setImageUrl("https://t1.kakaocdn.net/openbuilder/sample/img_003.jpg");
        item2.setAction("message");
        item2.setMessageText ("Kakao i Voice Service");
        list.add(item2);

        Button bu = new Button();
        bu.setLabel("구경가기");
        bu.setAction("block");
        bu.setBlockId("65cb0a1466538f507ce5e3e1");
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(bu);
        listCardDto.setButtons(buttonList);

        listCardMap.put("listCard",listCardDto);

        outputs.add(listCardMap);
        return kaResDto;
    }

    @RequestMapping(value="/ItemCard")
    public KakaoResDto ItemCard(@RequestBody KakaoReqDto kareq){
        log.info("/kakao/ItemCard");
        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();
        KakaoItemCardResDto ItemCardDto = new KakaoItemCardResDto();
        Map<String,Object> ItemCardMap = new HashMap<>();

        ImageTitle image1 = new ImageTitle();
        image1.setTitle("DOFQTK");
        image1.setDescription("Boarding Number");
        ItemCardDto.setImageTitle(image1);
        ItemCardDto.setTitle("");
        ItemCardDto.setDescription("");

        Thumbnail th = new Thumbnail();
        th.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeiIRbKS632gPBbPwiuC3G0g9vT22CJl-KWQ&s");

        th.setWidth(800);
        th.setHeight(800);
        ItemCardDto.setThumbnail(th);

        Profile pr= new Profile();
        pr.setTitle("AA Airline");
        pr.setImageUrl("https://t1.kakaocdn.net/openbuilder/docs_image/aaairline.jpg");
        ItemCardDto.setProfile(pr);

        List<ItemList> itemLists = ItemCardDto.getItemList();
        ItemList item = new ItemList();
        item.setTitle("Flight");
        item.setDescription("KE0605");
        itemLists.add(item);

        ItemList item2 = new ItemList();
        item2.setTitle("Boards");
        item2.setDescription("8:50 AM");
        itemLists.add(item2);

        ItemList item3 = new ItemList();
        item3.setTitle("Departs");
        item3.setDescription("9:50 AM");
        itemLists.add(item3);

        ItemList item4 = new ItemList();
        item4.setTitle("Terminal");
        item4.setDescription("1");
        itemLists.add(item4);

        ItemList item5 = new ItemList();
        item5.setTitle("Gate");
        item5.setDescription("C24");
        itemLists.add(item5);

        ItemCardDto.setItemListAlignment("right");


        ItemListSummary itemListSummary = new ItemListSummary();
        itemListSummary.setTitle("Total");
        itemListSummary.setDescription("$4,032.54");
        ItemCardDto.setItemListSummary(itemListSummary);

        Button bu = new Button();
        bu.setLabel("View Boarding Pass");
        bu.setAction("webLink");
        bu.setWebLinkUrl("https://namu.wiki/w/%EC%A0%95%EC%97%B0");

        List<Button> buttons = new ArrayList<>();
        buttons.add(bu);
        ItemCardDto.setButtons(buttons);

        ItemCardDto.setButtonLayout("vertical");

        ItemCardMap.put("itemCard",ItemCardDto);

        outputs.add(ItemCardMap);


        return kaResDto;
    }

    @RequestMapping(value="/carousel")
    public KakaoResDto carousel(@RequestBody KakaoReqDto kareq){
        log.info("/kakao/carousel");
        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();

        KakaoCarouselCardtResDto  carousel = new KakaoCarouselCardtResDto();
        //List<KakaoBasicCardtResDto> basicItmes = carousel.getBasicItems();
        List<Object> Itmes = carousel.getItems();

        carousel.setType("basicCard");

        KakaoBasicCardtResDto Kbasic = new KakaoBasicCardtResDto();

        Kbasic.setTitle("보물상자");
        Kbasic.setDescription("보물상자안에는 뭐가 있을까");

        Thumbnail th = new Thumbnail();
        th.setImageUrl("https://t1.kakaocdn.net/openbuilder/sample/lj3JUcmrzC53YIjNDkqbWK.jpg");
        th.setWidth(800);
        th.setHeight(800);
        Kbasic.setThumbnail(th);


        List<Button> buttons = new ArrayList<>();
        buttons.add(new Button("message","열어보기","짜잔! 우리가 찾던 보물입니다"));
        buttons.add(new Button("webLink","구경하기","https://e.kakao.com/t/hello-ryan"));
        Kbasic.setButtons(buttons);

        Itmes.add(Kbasic);

        KakaoBasicCardtResDto Kbasic2 = new KakaoBasicCardtResDto();

        Kbasic.setTitle("보물상자2");
        Kbasic.setDescription("보물상자2안에는 뭐가 있을까");

        Thumbnail th2 = new Thumbnail();
        th2.setImageUrl("https://t1.kakaocdn.net/openbuilder/sample/lj3JUcmrzC53YIjNDkqbWK.jpg");
        th2.setWidth(800);
        th2.setHeight(800);
        Kbasic2.setThumbnail(th2);


        List<Button> buttons2 = new ArrayList<>();
        Button bu3 = new Button();
        bu3.setAction("message");
        bu3.setLabel("열어보기3");
        bu3.setMessageText("짜잔! 우리가 찾던 보물입니다3");

        Button bu22 = new Button();
        bu22.setAction("webLink");
        bu22.setLabel("구경하기3");
        bu22.setWebLinkUrl("https://e.kakao.com/t/hello-ryan");

        buttons2.add(bu3);
        buttons2.add(bu22);

        Kbasic2.setButtons(buttons2);
        Itmes.add(Kbasic2);



        //
        Map<String,Object> CounseldMap = new HashMap<>();
        CounseldMap.put("carousel",carousel);

        outputs.add(CounseldMap);

        return kaResDto;

    }

    @RequestMapping(value="/carousel1")
    public KakaoResDto carousel1(@RequestBody KakaoReqDto kareq){
        log.info("/kakao/carousel1");
        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();

        KakaoCarouselCardtResDto  carousel = new KakaoCarouselCardtResDto();

        List<Object> listItmes = carousel.getItems();

        carousel.setType("listCard");

        KakaoListCardResDto Klist = new KakaoListCardResDto();

        ListItem listItem = new ListItem();
        listItem.setTitle("샌드위치");
        Klist.setHeader(listItem);

        List<ListItem> list = Klist.getItems();

        ListItem listItem1 = new ListItem();
        listItem1.setTitle("햄치즈");
        listItem1.setDescription("4,500원");
        listItem1.setImageUrl("https://t1.kakaocdn.net/openbuilder/docs_image/02_img_01.jpg");
        list.add(listItem1);

        ListItem listItem2 = new ListItem();
        listItem2.setTitle("베이컨 아보카도");
        listItem2.setDescription("5,500원");
        listItem2.setImageUrl("https://t1.kakaocdn.net/openbuilder/docs_image/02_img_02.jpg");
        list.add(listItem2);


        List<Button> buttons = new ArrayList<>();
        Button bu = new Button();
        bu.setAction("message");
        bu.setLabel("더보기");
        bu.setMessageText("샌드위치 더보기");
        buttons.add(bu);

        Klist.setButtons(buttons);
        listItmes.add(Klist);


        //
        List<Object> listItmes2 = carousel.getItems();

        KakaoListCardResDto Klist2 = new KakaoListCardResDto();

        ListItem listItem222 = new ListItem();
        listItem222.setTitle("커피");
        Klist2.setHeader(listItem222);

        List<ListItem> list2 = Klist2.getItems();

        ListItem listItem11 = new ListItem();
        listItem11.setTitle("아메리카노");
        listItem11.setDescription("1,800원");
        listItem11.setImageUrl("https://t1.kakaocdn.net/openbuilder/docs_image/02_img_05.jpg");
        list2.add(listItem11);

        ListItem listItem22 = new ListItem();
        listItem22.setTitle("카페라떼");
        listItem22.setDescription("5,500원");
        listItem22.setImageUrl("https://t1.kakaocdn.net/openbuilder/docs_image/02_img_06.jpg");
        list2.add(listItem22);


        List<Button> buttons2 = new ArrayList<>();
        Button bu2 = new Button();
        bu2.setAction("message");
        bu2.setLabel("더보기");
        bu2.setMessageText("커피 더보기");
        buttons2.add(bu2);

        Klist2.setButtons(buttons2);
        listItmes2.add(Klist2);

        //
        Map<String,Object> CounseldMap = new HashMap<>();
        CounseldMap.put("carousel",carousel);

        outputs.add(CounseldMap);




        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies bu3 = new QuickReplies();
        QuickReplies bu4 = new QuickReplies();
        bu3.setAction("message");
        bu3.setLabel("인기 메뉴");
        bu3.setMessageText("인기 메뉴");

        Quicklist.add(bu3);
        bu4.setAction("message");
        bu4.setLabel("최근 메뉴");
        bu4.setMessageText("최근 메뉴");
        Quicklist.add(bu4);


        return kaResDto;

    }

    @RequestMapping(value="/carousel2")
    public KakaoResDto carousel2(@RequestBody KakaoReqDto kareq){

        KakaoResDto kaResDto = new KakaoResDto();
        KakaoTemplateResDto kaTempResDto = kaResDto.getTemplate();
        List<Map<String,Object>> outputs = kaTempResDto.getOutputs();

        KakaoCarouselCardtResDto carousel = new KakaoCarouselCardtResDto();
        carousel.setType("itemCard");
        List<Object> listItmes = carousel.getItems();


        KakaoTextResDto textResDto = new KakaoTextResDto();
        textResDto.setText("총 2개의 예약 내역이 있습니다. 취소할 예약을 선택해주세요");

        Map<String,Object> textMap = new HashMap<>();

        textMap.put("simpleText",textResDto);

        KakaoItemCardResDto Kitem = new KakaoItemCardResDto();
        ImageTitle imageTitle = new ImageTitle();
        imageTitle.setTitle("예약 완료");
        imageTitle.setImageUrl("https://t1.kakaocdn.net/openbuilder/docs_image/wine.jpg");

        Kitem.setImageTitle(imageTitle);

        List<ItemList> ItemLists = Kitem.getItemList();

        ItemList itemList = new ItemList();
        itemList.setTitle("매장명");
        itemList.setDescription("판교 A스퀘어점");

        ItemList itemList2 = new ItemList();
        itemList2.setTitle("예약 일시");
        itemList2.setDescription("2022.12.25, 19:30");

        ItemList itemList3 = new ItemList();
        itemList3.setTitle("예약 인원");
        itemList3.setDescription("4명");

        ItemList itemList4 = new ItemList();
        itemList4.setTitle("예약금");
        itemList4.setDescription("40,000원 (결제 완료)");

        ItemLists.add(itemList);
        ItemLists.add(itemList2);
        ItemLists.add(itemList3);
        ItemLists.add(itemList4);

        Kitem.setItemListAlignment("left");


        List<Button> buttons = Kitem.getButtons();
        Button bu = new Button("message","예약 정보","예약 정보");
        Button bu2 = new Button("message","예약 취소","예약 취소");
        buttons.add(bu);
        buttons.add(bu2);

        //
        KakaoItemCardResDto Kitem2 = new KakaoItemCardResDto();
        ImageTitle imageTitle2 = new ImageTitle();
        imageTitle2.setTitle("결제 대기");
        imageTitle2.setImageUrl("https://t1.kakaocdn.net/openbuilder/docs_image/pizza.jpg");

        Kitem2.setImageTitle(imageTitle2);

        List<ItemList> ItemLists2 = Kitem2.getItemList();

        ItemList itemList22 = new ItemList();
        itemList22.setTitle("매장명");
        itemList22.setDescription("정자역점");

        ItemList itemList233 = new ItemList();
        itemList233.setTitle("예약 일시");
        itemList233.setDescription("2022.12.25, 19:25");

        ItemList itemList355 = new ItemList();
        itemList355.setTitle("예약 인원");
        itemList355.setDescription("3명");

        ItemList itemList414 = new ItemList();
        itemList414.setTitle("예약금");
        itemList414.setDescription("40,000원 (결제 대기)");

        ItemLists2.add(itemList22);
        ItemLists2.add(itemList233);
        ItemLists2.add(itemList355);
        ItemLists2.add(itemList414);

        Kitem2.setItemListAlignment("left");


        List<Button> buttons15 = Kitem2.getButtons();
        Button bu233 = new Button("message","예약 취소","예약 취소");
        Button bu111 = new Button("message","결제","결제");
        buttons15.add(bu111);
        buttons15.add(bu233);


        listItmes.add(Kitem);
        listItmes.add(Kitem2);

        ////

        Map<String,Object> Counsel1dMap = new HashMap<>();
        Counsel1dMap.put("carousel",carousel);

        outputs.add(textMap);
        outputs.add(Counsel1dMap);




        List<Object> Quicklist = kaTempResDto.getQuickReplies();

        QuickReplies bu3 = new QuickReplies();
        QuickReplies bu4 = new QuickReplies();
        bu3.setAction("message");
        bu3.setLabel("인기 메뉴");
        bu3.setMessageText("인기 메뉴");

        Quicklist.add(bu3);
        bu4.setAction("message");
        bu4.setLabel("최근 메뉴");
        bu4.setMessageText("최근 메뉴");
        Quicklist.add(bu4);

        ////



        return kaResDto;
    }



}
