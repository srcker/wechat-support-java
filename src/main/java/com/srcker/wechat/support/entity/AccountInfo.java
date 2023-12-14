package com.srcker.wechat.support.entity;

import lombok.Data;

@Data
public class AccountInfo {

    // 帐号 appid
    private String appId;

    // 帐号类型 (1:订阅号, 2:服务号, 3:小程序)
    private int accountType;

    // 主体类型
    private int principalType;

    // 主体名称
    private String principalName;

    // 实名验证状态
    private int realNameStatus;

    // 微信认证信息
    private WxVerifyInfo wxVerifyInfo;

    // 功能介绍信息
    private SignatureInfo signatureInfo;

    // 头像信息
    private HeadImageInfo headImageInfo;

    // 小程序名称
    private String nickname;

    // 注册国家
    private int registeredCountry;

    // 名称信息
    private NicknameInfo nicknameInfo;

    // 非个人主体时返回的是企业或者政府或其他组织的代号
    private String credential;

    // 认证类型
    private int customerType;

}
