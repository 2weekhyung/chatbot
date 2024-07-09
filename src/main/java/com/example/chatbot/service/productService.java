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

        list.add(new Producet("테스트",1,1500,"상품설명","https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
        list.add(new Producet("테스트",2,16000,"상품설명","https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
        list.add(new Producet("테스트",3,17200,"상품설명","https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
        list.add(new Producet("테스트",4,1734500000,"상품설명","https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
        return list;
    }
}
