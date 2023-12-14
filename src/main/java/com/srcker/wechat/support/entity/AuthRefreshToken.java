package com.srcker.wechat.support.entity;

import lombok.Data;

/**
 * @author Sinda
 * @project wechat
 * @class AuthRefreshToken
 * @email sinda@srcker.com
 * @time 2023/11/10 05:18
 */

@Data
public class AuthRefreshToken {


    private String authorizerAppid;
    private String authorizerAccessToken;
    private String authorizerRefreshToken;
    private int expiresIn;

    public AuthRefreshToken(int expiresIn,String authorizerAppid,String authorizerAccessToken,String authorizerRefreshToken){
        this.expiresIn = expiresIn;
        this.authorizerAppid = authorizerAppid;
        this.authorizerAccessToken = authorizerAccessToken;
        this.authorizerRefreshToken = authorizerRefreshToken;
    }



}
