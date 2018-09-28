package com.suyou.eurekaclient.utils;

import java.util.ArrayList;
import java.util.Date;

public class test {
    public static void main(String[] args) {
            ArrayList<Object> list = new ArrayList<>();
            Comment comment =  new Comment("name",88,new Date(),"comment");
            Comment comment2 =  new Comment("name",88,new Date(),"comment");
            list.add(comment);
            list.add(comment2);
            TestJson testJson = new TestJson();
            testJson.setList(list);
    }
}
