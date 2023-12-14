package com.srcker.wechat.support.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Sinda
 * @project wechat
 * @class TemplateList
 * @email sinda@srcker.com
 * @time 2023/11/10 11:52
 */
@Data
public class TemplateList {

    private int errcode;  // 错误代码
    private String errmsg;  // 错误信息
    private List<Template> templateList;  // 模板列表

}
