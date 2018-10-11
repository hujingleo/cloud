package com.suyou.cloud.entity;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiPing He on 2018/6/10 14:22.
 * Email: weiping_he@hansight.com
 */
@Data
public class SceneDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String sceneStr;
}
