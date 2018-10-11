package com.suyou.eurekaclient.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtils {
    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(url, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }
}
