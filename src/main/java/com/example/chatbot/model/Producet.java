package com.example.chatbot.model;

import lombok.Data;

@Data
public class Producet {

    private String name;
    private int no;
    private int price;
    private String description;
    private String imageUrl;


    public Producet(String name, int no, int price, String description, String imageUrl)
    {
        this.name = name;
        this.no = no;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;

    }


}
