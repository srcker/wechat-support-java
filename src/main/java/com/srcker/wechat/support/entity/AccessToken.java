package com.srcker.wechat.support.entity;

import lombok.Data;

/**
 * @author Sinda
 * @project wechat
 * @class AccessToken
 * @email sinda@srcker.com
 * @time 2023/11/9 11:45
 */

@Data
public class AccessToken {

    String accessToken;
    int expiresIn;

    public AccessToken(int expires_in,String access_token){
        this.expiresIn = expires_in;
        this.accessToken = access_token;
    }


}
