package com.suyou.eurekaclient.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_remind")
public class RemindEntity {
    private long id;
    private int posterId;
    private String openId;
    private Date created_date;
}
