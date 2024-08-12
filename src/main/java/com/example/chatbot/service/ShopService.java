package com.example.chatbot.service;

import com.example.chatbot.mapper.ShopMapper;
import com.example.chatbot.model.Paging;
import com.example.chatbot.model.Producet;
import com.example.chatbot.req.KakaoReqDto;
import com.example.chatbot.res.KakaoCallBack;
import com.example.chatbot.res.KakaoResDto;
import com.example.chatbot.res.KakaoTemplateResDto;
import com.example.chatbot.res.KakaoTextCardtResDto;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {
    @Autowired
    private ShopMapper ShopMapper;

    private static final Logger log = LoggerFactory.getLogger(ShopService.class);



    /**
     * 상품 입력
     * @param param
     * @return
     */
    public int insertProduct(Producet param){
        return ShopMapper.insertProduct(param);
    }
    public List<Producet> selectProduct(Paging paging){
        return ShopMapper.selectProduct(paging);
    }

    /**
     * 주문 입력
     * @param param
     * @return
     */
    public int insertOrder(Producet param){
        return ShopMapper.insertOrder(param);
    }

    /**
     * 주문내역 출력
     * @param paging
     * @return
     */
    public List<Producet> selectOrder(Paging paging){
        return ShopMapper.selectOrder(paging);
    }


     /** 게시뭃 업데이트
     * @param param
     * @return
     */

     public int updateOrder(Producet param){
        return ShopMapper.updateOrder(param);
    }

    /**
     *  게시물 개수
     */
    public int countOrder(String userId ){
        return ShopMapper.countOrder(userId );
    }

    /**
     * 상품 개수
     * @return
     */
    public int countProduct(){
        return ShopMapper.countProduct();
    }

    /**
     * 카카오 콜백    ,Type classOfT
     */
     public KakaoCallBack callboack(String reqUrl, Object req_date){
         KakaoCallBack kakaoCallBack = new KakaoCallBack();
         Gson gson = new Gson();
         String inputLine = "";
         String jsonValue = "";
         StringBuffer outResult = new StringBuffer();
         try {
             log.info("=====[KAKAO Callback API 요청 : " + reqUrl + "]=====");
             jsonValue = gson.toJson(req_date);
             URL url = new URL(reqUrl);
             log.info("=====[jsonValue Callback  : " + jsonValue + "]=====");
             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
             conn.setDoOutput(true);
             conn.setRequestMethod("POST");
             conn.setRequestProperty("Content-Type", "application/json");
             conn.setRequestProperty("Accept-Charset", "UTF-8");
             conn.setConnectTimeout(30000);
             conn.setReadTimeout(30000);

             OutputStream os = conn.getOutputStream();
             os.write(jsonValue.getBytes());
             os.flush();
             log.info("===== 여기 까지 잘 들어 왔나..? =====");
             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
             while ((inputLine = br.readLine()) != null) {
                 outResult.append(inputLine);
             }


             conn.disconnect();
             kakaoCallBack = gson.fromJson(outResult.toString(), KakaoCallBack.class);
             log.info("=====[데이터 종료 Callback : " + reqUrl + " ]=====");

         }catch (Exception e){
             log.info(e.toString());
             e.printStackTrace();

             HashMap<String, String> errorMap = new HashMap<String, String>();
             errorMap.put("rst", "F");
             errorMap.put("rstCd", "9999");
             errorMap.put("rstMsg", "API 요청 중 오류 발생");

             outResult = new StringBuffer();
             outResult.append(gson.toJson(errorMap));
         }

         return kakaoCallBack;
     }
}
