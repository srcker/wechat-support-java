package com.srcker.wechat.support.entity;

import lombok.Data;

/**
 * @author Sinda
 * @project wechat
 * @class AuthorizerListItem
 * @email sinda@srcker.com
 * @time 2023/11/10 08:30
 */

@Data
public class AuthorizerListItem {

    private String authorizer_appid;
    private String refresh_token;
    private Long auth_time;

}
