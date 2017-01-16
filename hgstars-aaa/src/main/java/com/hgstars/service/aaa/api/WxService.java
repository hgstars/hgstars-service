package com.hgstars.service.aaa.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Maps;
import com.hgstars.api.wechat.IWxService;
import com.hgstars.service.aaa.service.WechatService;
import com.hgstars.service.aaa.system.WXConfig;
import com.hgstars.wechat.TokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by yujindong on 17/1/16.
 */
@Service
public class WxService implements IWxService{

    private static Logger logger = LoggerFactory.getLogger(WxService.class);

    @Autowired
    private WechatService wechatService;

    @Override
    public TokenDto getToken() {
        logger.info("dubbo wxService getToken");
        return wechatService.getToken();
    }
}
