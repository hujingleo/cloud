package com.suyou.eurekaclient.utils.qiniu;

import com.qiniu.util.Auth;
import com.suyou.eurekaclient.utils.StringTools;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QiNiuUtils {
    private final static String accessKey = "w95PS-ggv7PWbm-m5Ii2ODX63NnZdIi8pCHxGGnd";
    private final static String secretKey = "gZ7fm7gvGDLvK6Ox4uV3TpIiZ-GdmS5q2lDvQIqH";
    private final static String bucket = "xiaochengxu";

    public static String getToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        if (StringTools.isNullOrEmpty(upToken)){
            log.error("获取七牛上传token失败，开始重新获取");
            upToken = auth.uploadToken(bucket);
        }
        return upToken;
    }
}
