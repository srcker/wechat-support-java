package com.srcker.wechat.support.entity;

import lombok.Data;

@Data
public class NicknameInfo {
    private String nickname;
    private int modifyUsedCount;
    private int modifyQuota;
}
