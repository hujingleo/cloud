package com.suyou.eurekaclient.utils.wechat;

import lombok.Data;

@Data
public class Keyword {
    private String value;
    private String color;
    public Keyword(String value,String color){
        super();
        this.value=value;
        this.color=color;
    }
    public Keyword(){
    }
}
