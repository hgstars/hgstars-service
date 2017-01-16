package com.hgstars.service.aaa.system;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yujindong on 17/1/16.
 */
@Data
@Configuration
public class WXConfig {
    private @Value("#{wxProperties.wx_token}") String token;
    private @Value("#{wxProperties.wx_aeskey}") String aesKey;
    private @Value("#{wxProperties.wx_appid}") String appId;
    private @Value("#{wxProperties.wx_appsecret}") String appSecret;
    private @Value("#{wxProperties.wx_access_token_create_url}") String accessTokenCreateUrl;
}
