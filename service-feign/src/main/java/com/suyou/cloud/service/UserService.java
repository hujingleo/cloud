package com.suyou.cloud.service;

import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.WechatLoginForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-user")
public interface UserService {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
    @RequestMapping(value = "/sysUser/login",method = RequestMethod.GET)
    BaseResp login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);
    @RequestMapping(value = "/sysUser/list",method = RequestMethod.GET)
    BaseResp list();
    @RequestMapping(value = "/page/list",method = RequestMethod.GET)
    BaseResp pageList();
    @RequestMapping(value = "/poster/list",method = RequestMethod.GET)
    BaseResp posterList();
    @RequestMapping(value = "/wechat/login",method = RequestMethod.POST)
    BaseResp wechatLogin(@RequestBody WechatLoginForm wechatLoginForm);
    @RequestMapping(value = "/wechat/saveUserOfficialAccountsOpenId",method = RequestMethod.POST)
    BaseResp saveUserOfficialAccountsOpenId( @RequestParam(value = "code")String code,@RequestParam(value = "openId") String openId);
    @RequestMapping(value = "/wechat/getUserOfficialAccountsOpenId",method = RequestMethod.POST)
    BaseResp getUserOfficialAccountsOpenId(@RequestParam(value = "openId") String openId);
}
