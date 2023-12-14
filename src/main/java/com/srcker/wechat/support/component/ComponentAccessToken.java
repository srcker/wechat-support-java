package com.srcker.wechat.support.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;

/**
 * @author Sinda
 * @project wechat
 * @class AccessToken
 * @email sinda@srcker.com
 * @time 2023/11/9 11:25
 */
@Data
public class ComponentAccessToken {



    private String appId;
    private String secret;
    private String ticket;

    private com.srcker.wechat.support.entity.AccessToken accessToken;

    private String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

    public ComponentAccessToken(String appId, String secret, String ticket){
        this.appId = appId;
        this.secret = secret;
        this.ticket = ticket;
        this.request();
    }


    private void request(){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("component_appid", this.appId);
        paramMap.put("component_appsecret", this.secret);
        paramMap.put("component_verify_ticket", this.ticket);

        String body = HttpRequest.post(url)
                        .body(JSONUtil.parseObj(paramMap).toString())
                        .execute()
                        .body();

        JSONObject result = JSONObject.parseObject(body);

        if (!StrUtil.isEmpty(result.getString("component_access_token"))){
            this.accessToken = new com.srcker.wechat.support.entity.AccessToken(result.getInteger("expires_in"),result.getString("component_access_token"));
        }else{
            System.out.println(result);
        }

    }


}
