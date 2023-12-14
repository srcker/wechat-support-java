package com.srcker.wechat.support.entity;

import lombok.Data;

/**
 * 微信通用返回解析
 * @author Sinda
 * @project wechat
 * @class ComResult
 * @email sinda@srcker.com
 * @create 2023/11/10 09:54
 */

@Data
public class ComResult<T> {
    int errcode;
    String errmsg = "success";
    T data;

    public ComResult(T data){
        this.data = data;
    }

}
