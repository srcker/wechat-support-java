package com.srcker.wechat.support.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author Sinda
 * @project wechat
 * @class PreAuthCode
 * @email sinda@srcker.com
 * @time 2023/11/10 05:09
 */
public class PreAuthCode {

    private String accessToken;
    private String componentAppid;
    private com.srcker.wechat.support.entity.PreAuthCode preAuthCode;

    private String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?access_token=%s";

    public PreAuthCode(String accessToken,String componentAppid){
        this.accessToken = accessToken;
        this.componentAppid = componentAppid;
        this.request();
    }


    private void request(){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("component_appid", componentAppid);

        String body = HttpRequest.post(String.format(url,accessToken))
                .body(JSONUtil.parseObj(paramMap).toString())
                .execute()
                .body();

        JSONObject result = JSONObject.parseObject(body);

        if (!StrUtil.isEmpty(result.getString("pre_auth_code"))){
            this.preAuthCode = new com.srcker.wechat.support.entity.PreAuthCode(result.getInteger("expires_in"),result.getString("pre_auth_code"));
        }else{
            System.out.println(result);
        }

    }



}
