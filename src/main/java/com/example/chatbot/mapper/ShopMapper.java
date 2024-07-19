package com.example.chatbot.mapper;

import com.example.chatbot.model.Paging;
import com.example.chatbot.model.Producet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {
    /**
     * 게시판 입력
     * @param param
     * @return
     */
    int insertOrder(Producet param);

    List<Producet> selectOrder(Paging param);

    int updateOrder(Producet param);

    int countOrder(String userId );
}
