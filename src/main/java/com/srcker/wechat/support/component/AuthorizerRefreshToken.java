package com.srcker.wechat.support.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author Sinda
 * @project wechat
 * @class AuthorizerRefreshToken
 * @email sinda@srcker.com
 * @time 2023/11/10 05:29
 */
public class AuthorizerRefreshToken {

    private final String componentAppid;
    private final String authorizationCode;
    private final String componentAccessToken;
    private com.srcker.wechat.support.entity.AuthRefreshToken authRefreshToken;

    public AuthorizerRefreshToken(String componentAppid, String componentAccessToken, String authorizationCode){
        this.componentAppid = componentAppid;
        this.componentAccessToken = componentAccessToken;
        this.authorizationCode = authorizationCode;
        this.request();
    }



    private void request(){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("component_appid", componentAppid);
        paramMap.put("authorization_code", authorizationCode);

        String url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?access_token=%s";
        String body = HttpRequest.post(String.format(url,componentAccessToken))
                .body(JSONUtil.parseObj(paramMap).toString())
                .execute()
                .body();

        JSONObject result = JSONObject.parseObject(body);

        JSONObject info = result.getJSONObject("authorization_info");

        if (!StrUtil.isEmpty(info.getString("authorization_info"))){
            authRefreshToken.setExpiresIn(result.getInteger("expires_in"));
            authRefreshToken.setAuthorizerAppid(result.getString("authorizer_appid"));
            authRefreshToken.setAuthorizerAccessToken(result.getString("authorizer_access_token"));
            authRefreshToken.setAuthorizerRefreshToken(result.getString("authorizer_refresh_token"));
        }else{
            System.out.println(result);
        }

    }

}
