package com.srcker.wechat.support.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Sinda
 * @project wechat
 * @class TemplateList
 * @email sinda@srcker.com
 * @time 2023/11/10 11:37
 */

@Data
public class Template {

    // 创建时间
    private long createTime;
    // 用户版本
    private String userVersion;
    // 用户描述
    private String userDesc;
    // 模板ID
    private int templateId;
    // 源小程序 appid
    private String sourceMiniprogramAppid;
    // 源小程序
    private String sourceMiniprogram;
    // 开发者
    private String developer;
    // 模板类型
    private int templateType;
    // 类别列表
    private List<Category> categoryList;
    // 审核场景
    private int auditScene;
    // 审核状态
    private int auditStatus;
    // 原因
    private String reason;

}
