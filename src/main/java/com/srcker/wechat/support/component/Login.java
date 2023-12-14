package com.srcker.wechat.support.component;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.srcker.wechat.support.entity.CodeSession;
import com.srcker.wechat.support.entity.ComResult;
import com.srcker.wechat.support.entity.Company;

import java.util.HashMap;

public class Login {


    /**
     * 小程序登录
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @param component_appid 第三方平台 appid
     * @param appid 小程序的 AppID
     * @param code Wx.login 获取的 code
     * @return CodeSession
     * @create 2023/11/12 06:11:24
     */
    public CodeSession thirdpartyCode2Session(String access_token, String component_appid, String appid, String code){
        String url = "https://api.weixin.qq.com/wxa/verifybetaweapp";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("component_appid", component_appid);
        paramMap.put("appid", appid);
        paramMap.put("js_code", code);
        paramMap.put("access_token", access_token);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpUtil.get(url,paramMap);

        // 将响应体解析为AuthorizerList对象并返回
        return JSONObject.parseObject(body, CodeSession.class);

    }





}
