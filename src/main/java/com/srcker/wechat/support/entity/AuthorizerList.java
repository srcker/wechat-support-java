package com.srcker.wechat.support.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Sinda
 * @project wechat
 * @class AuthorizerList
 * @email sinda@srcker.com
 * @time 2023/11/10 08:30
 */

@Data
public class AuthorizerList {

    int totalCount;

    List<AuthorizerListItem> list;
}
