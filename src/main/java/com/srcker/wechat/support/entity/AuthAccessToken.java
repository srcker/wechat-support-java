package com.srcker.wechat.support.entity;

import lombok.Data;

/**
 * @author Sinda
 * @project wechat
 * @class AuthAccessToken
 * @email sinda@srcker.com
 * @time 2023/11/10 05:16
 */

@Data
public class AuthAccessToken {

    private String authorizerAccessToken;
    private String authorizerRefreshToken;
    private int expiresIn;

    public AuthAccessToken(int expiresIn,String authorizerAccessToken,String authorizerRefreshToken){
        this.expiresIn = expiresIn;
        this.authorizerAccessToken = authorizerAccessToken;
        this.authorizerRefreshToken = authorizerRefreshToken;
    }



}
