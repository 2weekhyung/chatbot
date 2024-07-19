package com.example.chatbot.service;

import com.example.chatbot.mapper.ShopMapper;
import com.example.chatbot.model.Paging;
import com.example.chatbot.model.Producet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopMapper ShopMapper;


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

}
