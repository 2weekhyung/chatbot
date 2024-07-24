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

        for (int i = 1; i < 31; i++) {
            list.add(new Producet("오므라이스 키링"+i,"상품설명입니다."+i,7700,1500,"https://cdn.imweb.me/thumbnail/20240321/5be5d23c74bb9.png"));
            list.add(new Producet("춘식이 안경닦이"+i,"상품설명입니다."+i,5000,680,"https://st.kakaocdn.net/thumb/P750x750/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fshoppingstore%2Fproduct%2F20240621172756_874016ce6bcf468e96095a37a4177d0f.jpg"));
            list.add(new Producet("춘식이 소주 디스펜서"+i,"상품설명입니다."+i,60100,5550,"https://st.kakaocdn.net/thumb/R890x0/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fshoppingstore%2Feditor%2F20240625171656_ddfb9390758c46dda5630a79ff432b08.jpg"));

        }


        return list;
    }
}
