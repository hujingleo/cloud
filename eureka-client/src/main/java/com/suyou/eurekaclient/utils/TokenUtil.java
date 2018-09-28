package com.suyou.eurekaclient.utils;//package com.leo.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//import java.security.Key;
//import java.util.Date;
//
///**
// * @Copyist: John
// * @Decsription: 生成Token的工具类
// * @Create: 2018-5-7 22:05:08
// */
//@Component
//public class TokenUtil {
//
//    @Autowired
//    private Environment environment;
//
//    @Autowired
//    private static Environment env;
//
//    @PostConstruct
//    public void init(){
//        env = environment;
//    }
//    private TokenUtil(){}
//
//
//    /**
//     * 一个JWT实际上就是一个字符串，它由三部分组成，头部(Header)、载荷(Payload)与签名(Signature)
//     * @param id 当前用户ID
//     * @param issuer 该JWT的签发者，是否使用是可选的
//     * @param subject 该JWT所面向的用户，是否使用是可选的
//     * @param ttlMillis 什么时候过期，这里是一个Unix时间戳，是否使用是可选的
//     * @param audience 接收该JWT的一方，是否使用是可选的
//     * @return
//     */
//    public static String createJWT(String id,String issuer,String subject,long ttlMillis,String audience){
//
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        //私钥
//        String APP_KEY = env.getProperty("app.key");
//
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(APP_KEY);
//
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
//
//        JwtBuilder jwtBuilder = Jwts.builder()
//                .setId(id)//一般用作userId
//                .setSubject(subject)//面向的用户
//                .setIssuedAt(now)//签发时间
//                .setIssuer(issuer)//JWT的签发者
//                .setAudience(audience)//接受jwt一方
//                .signWith(signatureAlgorithm,signingKey);
//        //设置Token过期时间
//        if (ttlMillis >= 0){
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            jwtBuilder.setExpiration(exp);
//        }
//        return jwtBuilder.compact();
//    }
//
//    //私钥解密token信息
//    public static Claims getClaims(String jwt) {
//        //私钥
//        String APP_KEY = env.getProperty("app.key");
//        return Jwts.parser()
//                .setSigningKey(DatatypeConverter.parseBase64Binary(APP_KEY))
//                .parseClaimsJws(jwt).getBody();
//    }
//
//}
