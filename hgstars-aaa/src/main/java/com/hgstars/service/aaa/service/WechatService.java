package com.hgstars.service.aaa.service;

import com.google.common.collect.Maps;
import com.hgstars.service.aaa.system.WXConfig;
import com.hgstars.wechat.TokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by yujindong on 17/1/16.
 */
@Service
@Transactional
public class WechatService {

    private static Logger logger = LoggerFactory.getLogger(WechatService.class);
    @Autowired
    private WXConfig config;

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "weixin", key = "'weixin_token'")
    public TokenDto getToken() {
        Map params = Maps.newHashMap();
        params.put("appid", config.getAppId());
        params.put("appsecret", config.getAppSecret());
        params.put("grant_type", "client_credential");
        TokenDto result = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type={grant_type}&appid={appid}&secret={appsecret}",TokenDto.class,params);
        logger.info("获取或者更新token" + result.toString());
        return result;
    }
}
