package com.srcker.wechat.support.config;

import lombok.Data;

/**
 * @author Sinda
 * @project wechat
 * @class WeChatConfig
 * @email sinda@srcker.com
 * @time 2023/11/10 08:11
 */

@Data
public class WeChatConfig {
    private String appid;

    private String secret;

    private String key;

    private String token;

}
