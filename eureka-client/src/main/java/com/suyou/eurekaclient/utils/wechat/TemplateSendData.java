package com.suyou.eurekaclient.utils.wechat;

import lombok.Data;

@Data
public class TemplateSendData {
    //
    private Keyword first;
    //名称
    private Keyword keyword1;
    //时间
    private Keyword keyword2;
    //地点
    private Keyword keyword3;
    //
    private Keyword remark;
}
