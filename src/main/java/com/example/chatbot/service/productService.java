package com.example.chatbot.service;

import com.example.chatbot.model.Producet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class productService {

    public ArrayList<Producet> getList() {
        ArrayList<Producet> list = new ArrayList<>();

        list.add(new Producet(1,"테스트1","상품설명",100,"https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
        list.add(new Producet(2,"테스트2","상품설명",1000,"https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
        list.add(new Producet(3,"테스트3","상품설명",10000,"https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
        list.add(new Producet(4,"테스트4","상품설명",100000,"https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
        return list;
    }
}
