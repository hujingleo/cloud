package com.suyou.eurekaclient.utils.wechat;

import lombok.Data;

@Data
public class TemplateSendData {
    //订单编号
    private Keyword keyword1;
    //支付时间
    private Keyword keyword2;
    //商品名称
    private Keyword keyword3;
    //支付金额
    private Keyword keyword4;
    //温馨提示
    private Keyword keyword5;
}
