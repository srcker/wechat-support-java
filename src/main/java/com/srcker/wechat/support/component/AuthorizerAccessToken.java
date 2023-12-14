package com.srcker.wechat.support.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author Sinda
 * @project wechat
 * @class AuthAccessToken
 * @email sinda@srcker.com
 * @time 2023/11/10 05:14
 */
public class AuthorizerAccessToken {

    private String authorizerAppid;
    private String componentAppid;
    private String authorizerRefreshToken;
    private String componentAccessToken;
    private com.srcker.wechat.support.entity.AuthAccessToken authAccessToken;

    public AuthorizerAccessToken(String componentAppid, String authorizerAppid, String authorizerRefreshToken, String componentAccessToken){
        this.componentAppid = componentAppid;
        this.authorizerAppid = authorizerAppid;
        this.componentAccessToken = componentAccessToken;
        this.authorizerRefreshToken = authorizerRefreshToken;
        this.request();
    }


    private void request(){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("authorizer_appid", authorizerAppid);
        paramMap.put("component_appid", componentAppid);
        paramMap.put("authorizer_refresh_token", authorizerRefreshToken);

        String url = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=%s";
        String body = HttpRequest.post(String.format(url,componentAccessToken))
                .body(JSONUtil.parseObj(paramMap).toString())
                .execute()
                .body();

        JSONObject result = JSONObject.parseObject(body);

        if (!StrUtil.isEmpty(result.getString("authorizer_access_token"))){
            authAccessToken.setExpiresIn(result.getInteger("expires_in"));
            authAccessToken.setAuthorizerAccessToken(result.getString("authorizer_access_token"));
            authAccessToken.setAuthorizerRefreshToken(result.getString("authorizer_refresh_token"));
        }else{
            System.out.println(result);
        }

    }

}
