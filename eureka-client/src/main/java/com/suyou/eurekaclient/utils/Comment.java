package com.suyou.eurekaclient.utils;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    String name;
    long car_id;
    Date date;
    String comments;
    Comment(String name,long car,Date date,String comments){
        this.name=name;
        this.car_id=car_id;
        this.date=date;
        this.comments=comments;
    }


}
