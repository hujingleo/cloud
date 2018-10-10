package com.suyou.eurekaclient.utils.wechat;

import lombok.Data;

@Data
public class Keyword {
    private String value;
    public Keyword(String value){
        super();
        this.value=value;
    }
    public Keyword(){
    }
}
