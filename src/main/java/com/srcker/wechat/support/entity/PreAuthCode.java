package com.srcker.wechat.support.entity;

import lombok.Data;

/**
 * @author Sinda
 * @project wechat
 * @class PreAuthCode
 * @email sinda@srcker.com
 * @time 2023/11/10 05:10
 */

@Data
public class PreAuthCode {

    private String preAuthCode;
    private int expiresIn;

    public PreAuthCode(int expiresIn,String preAuthCode){
        this.expiresIn = expiresIn;
        this.preAuthCode = preAuthCode;
    }

}
