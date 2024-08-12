package com.example.chatbot.service;

import com.example.chatbot.req.KakaoReqDto;
import com.example.chatbot.res.KakaoCallBack;
import com.example.chatbot.res.KakaoResDto;
import com.example.chatbot.res.KakaoTemplateResDto;
import com.example.chatbot.res.KakaoTextCardtResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CallBackService {

    @Autowired
    ShopService shopService;

    /*public void reqCallBack(KakaoReqDto reqData, KakaoResDto resResult ){
        if(reqData.getUserRequest().getCallbackUrl() !=null){
            shopService.callboack(reqData.getUserRequest().getCallbackUrl(),resResult,(Type) KakaoCallBack.class);
        }
    }*/

    /**
     * 콜백 응답 메시지
     * @return
     */
    public KakaoResDto callback() {
        KakaoTemplateResDto template = new KakaoTemplateResDto();

        KakaoResDto result = new KakaoResDto();

        List<Map<String,Object>> outputs = new ArrayList<>();
        Map<String,Object>  output = new HashMap<>();

        template.setOutputs(outputs);
        result.setTemplate(template);

        template.setOutputs(outputs);
        result.setTemplate(template);

        KakaoTextCardtResDto textCard = new KakaoTextCardtResDto ();
        textCard.setTitle("");
        textCard.setDescription("콜백 함수 호출");
        output.put("textCard",textCard);
        outputs.add(output);
        return  result;
    }
}
