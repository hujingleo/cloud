package com.suyou.eurekaclient.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_access_token")
public class AccessTokenEntity {
    private long id;
    private String appid;
    private String access_token;
    private Date created_date;
}
