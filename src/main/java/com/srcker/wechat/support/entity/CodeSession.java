package com.srcker.wechat.support.entity;

public class CodeSession {

    // 会话密钥
    private String sessionKey;

    // 用户唯一标识的 openid
    private String openId;

    // 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
    private String unionId;
}
