package com.srcker.wechat.support.component;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.srcker.wechat.support.entity.AccountInfo;
import com.srcker.wechat.support.entity.ComResult;

import java.util.HashMap;

public class Account {


    private static final SerializeConfig CONFIG = new SerializeConfig();

    static {
        CONFIG.propertyNamingStrategy = com.alibaba.fastjson.PropertyNamingStrategy.SnakeCase;
    }


    /**
     * 获取基本信息
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token ACCESS_TOKEN
     * @return AccountInfo
     * @create 2023/11/12 07:13:03
     */
    public ComResult<?> getAccountBasicInfo(String access_token){

        String url = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo?access_token=%s";

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .execute()
                .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject result = JSONObject.parseObject(body);

        // 判断errcode字段是否为0，如果是则解析服务器域名信息并返回ComResult对象，否则直接解析返回ComResult对象
        if (result.getInteger("errcode") == 0){
            return new ComResult<>(JSONObject.parseObject(body, AccountInfo.class));
        }else{
            return JSONObject.parseObject(body, ComResult.class);
        }

    }


    /**
     * 查询绑定的开放平台账号
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token
     * @return
     * @create 2023/11/12 07:30:01
     */
    public ComResult<?> getBindOpenAccount(String access_token){

        String url = "https://api.weixin.qq.com/cgi-bin/open/have?access_token=%s";

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.get(String.format(url,access_token))
                    .execute()
                    .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject json = JSONObject.parseObject(body);
        // 将返回的字符串解析为JSONObject对象
        ComResult<Boolean> result = JSONObject.parseObject(body, ComResult.class);
        result.setData(json.getBoolean("have_open"));
        return result;

    }





    public ComResult<?> checkNickName(String access_token,String nick_name){

        String url = "https://api.weixin.qq.com/cgi-bin/wxverify/checkwxverifynickname?access_token=%s";



        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                    .execute()
                    .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject json = JSONObject.parseObject(body);
        // 将返回的字符串解析为JSONObject对象
        ComResult<Boolean> result = JSONObject.parseObject(body, ComResult.class);
        result.setData(json.getBoolean("have_open"));
        return result;

    }






}
