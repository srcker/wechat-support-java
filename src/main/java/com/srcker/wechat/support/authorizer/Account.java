package com.srcker.wechat.support.authorizer;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.srcker.wechat.support.entity.AuthorizerList;
import com.srcker.wechat.support.entity.AuthorizerOptionInfo;
import com.srcker.wechat.support.entity.ComResult;

import java.util.HashMap;

/**
 * @author Sinda
 * @project wechat
 * @class Account
 * @email sinda@srcker.com
 * @time 2023/11/10 07:58
 */
public class Account {


    /**
     * 获取已授权应用信息
     * @param appid
     * @param offset
     * @param count
     * @param access_token
     * @return
     */
    public AuthorizerList getAuthorizerList(String access_token, String appid, int offset, int count){

        String url = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_list?access_token=%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("component_appid", appid);
        paramMap.put("offset", offset);
        paramMap.put("count", count);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSONObject.toJSONString(paramMap))
                .execute()
                .body();

        // 将响应体解析为AuthorizerList对象并返回
        return JSONObject.parseObject(body, AuthorizerList.class);

    }

    /**
     * 获取授权账号详情
     *
     * @param access_token     接口调用凭证
     * @param component_appid  第三方平台 appid
     * @param authorizer_appid 授权的公众号或者小程序的appid
     * @return AuthorizerList
     */
    public String getAuthorizerInfo(String access_token, String component_appid, String authorizer_appid){

        String url = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?access_token=%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("component_appid", component_appid);
        paramMap.put("authorizer_appid", authorizer_appid);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSONObject.toJSONString(paramMap))
                .execute()
                .body();

        // 将响应体解析为AuthorizerList对象并返回
        return body;

    }


    /**
     * 设置授权方选项信息
     * @param access_token 接口调用凭证
     * @param info AuthorizerOptionInfo选项
     * @return ComResult
     */
    public ComResult setAuthorizerOptionInfo(String access_token, AuthorizerOptionInfo info){

        String url = "https://api.weixin.qq.com/cgi-bin/component/set_authorizer_option?access_token=%s";

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSONObject.toJSONString(info))
                .execute()
                .body();

        // 将响应体解析为ComResult对象并返回
        return JSONObject.parseObject(body, ComResult.class);
    }

    public ComResult getAuthorizerOptionInfo(String access_token, String option_name){

        String url = "https://api.weixin.qq.com/cgi-bin/component/get_authorizer_option?access_token=%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("option_name", option_name);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSONObject.toJSONString(paramMap))
                .execute()
                .body();

        // 将响应体解析为ComResult对象并返回
        return JSONObject.parseObject(body, ComResult.class);
    }


}
