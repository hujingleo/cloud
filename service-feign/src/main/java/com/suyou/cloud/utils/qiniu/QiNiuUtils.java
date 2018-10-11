package com.suyou.cloud.utils.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.suyou.cloud.utils.StringTools;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

@Slf4j
public class QiNiuUtils {
    private final static String accessKey = "w95PS-ggv7PWbm-m5Ii2ODX63NnZdIi8pCHxGGnd";
    private final static String secretKey = "gZ7fm7gvGDLvK6Ox4uV3TpIiZ-GdmS5q2lDvQIqH";
    private final static String bucket = "xiaochengxu";
    private final  static String key = null;
    public static String getToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        if (StringTools.isNullOrEmpty(upToken)){
            log.error("获取七牛上传token失败，开始重新获取");
            upToken = auth.uploadToken(bucket);
        }
        return upToken;
    }
    public static String upload(byte[] uploadBytes ){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

        return null;
    }
}
