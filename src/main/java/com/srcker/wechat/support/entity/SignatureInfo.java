package com.srcker.wechat.support.entity;

import lombok.Data;

@Data
public class SignatureInfo {

    private String signature;
    private int modifyUsedCount;
    private int modifyQuota;
}
