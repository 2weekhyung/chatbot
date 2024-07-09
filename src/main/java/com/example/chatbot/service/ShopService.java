package com.example.chatbot.service;

import com.example.chatbot.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    private ShopMapper ShopMapper;

    /**
     * 데이터 개수 세기
     */
    /*public int count(BoardListReqDto param){
        return boardMapper.count(param);
    }


    *//**
     * 게시물 전체 출력
     * @param param
     * @return
     *//*
    public List<BoardReqDto> list(BoardListReqDto param){
        return boardMapper.list(param);
    }

    *//**
     * 게시물 입력
     * @param param
     * @return
     *//*
    public int insert(BoardReqDto param){
        return boardMapper.insert(param);
    }


    *//**
     * 게시물 상세 보기
     * @param param
     * @return
     *//*
    public BoardReqDto detail(int param){
        return boardMapper.detail(param);
    }

    *//**
     * 게시물 입력 페이지 이동
     *//*
    public String add(){
        return "view/board/add";
    }

    *//**
     * 게시뭃 업데이트
     * @param param
     * @return
     *//*
    public int update(BoardReqDto param){
        return boardMapper.update(param);
    }

    *//**
     * 게시물 삭제
     * @param param
     * @return
     *//*
    public int delete(int param){
        return boardMapper.delete(param);
    }*/
}
