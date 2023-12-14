package com.srcker.wechat.support.entity;

import lombok.Data;

/**
 * @author Sinda
 * @project wechat
 * @class Draft
 * @email sinda@srcker.com
 * @time 2023/11/10 10:39
 */

@Data
public class DraftList {

    int draftId;
    String userVersion;
    String userDesc;
    long createTime;

}
