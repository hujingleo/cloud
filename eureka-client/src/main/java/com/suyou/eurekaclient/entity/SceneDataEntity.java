package com.suyou.eurekaclient.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiPing He on 2018/6/10 14:22.
 * Email: weiping_he@hansight.com
 */
@Data
@TableName("tb_scene_data")
public class SceneDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    private String scene_str;
}
