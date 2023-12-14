package com.srcker.wechat.support.entity;


import lombok.Data;

@Data
public class Company {

    // 企业名（需与工商部门登记信息一致）
    private String enterpriseName;

    // 企业名（需与工商部门登记信息一致）
    private String name;

    // 企业代码
    private String code;

    // 企业代码类型
    private int codeType;

    // 法人微信号
    private String legalPersonaWechat;

    // 法人姓名（绑定银行卡）
    private String legalPersonaName;

    // 法人法人身份证号
    private String legalPersonaIdcard;

    // 第三方联系电话
    private String componentPhone;


}
