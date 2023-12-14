package com.srcker.wechat.support.entity;

import lombok.Data;

@Data
public class PersonalMp {

    // 个人用户名字
    private String idname;

    // 个人用户微信号
    private String wxuser;

    // 第三方联系电话
    private String componentPhone;

    // 任务id，后面query查询需要用到
    private String taskid;

    // 给用户扫码认证的验证url
    private String authorizeUrl;

    // 任务的状态
    private int status;

}
