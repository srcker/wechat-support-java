package com.srcker.wechat.support.component;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srcker.wechat.support.entity.AuthorizerList;
import com.srcker.wechat.support.entity.ComResult;
import com.srcker.wechat.support.entity.DraftList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Template {


    public static List<DraftList> getTemplatedRaftList(String access_token){

        String url = "https://api.weixin.qq.com/wxa/gettemplatedraftlist?access_token=%s";

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.get(String.format(url,access_token))
                        .execute()
                        .body();

        JSONObject result = JSON.parseObject(body);

        if (result.getInteger("errcode") == 0){
            return JSONArray.parseArray(result.getJSONArray("draft_list").toJSONString(),DraftList.class);
        }else{
            return new ArrayList<DraftList>();
        }
    }


    /**
     * 将草稿添加到模板库
     * @param access_token 接口调用凭证
     * @param draft_id 草稿 ID
     * @param template_type 默认值是0，对应普通模板；可选1，对应标准模板库
     * @return ComResult
     */
    public static ComResult<?> addToTemplate(String access_token,int draft_id,int template_type){

        String url = "https://api.weixin.qq.com/wxa/addtotemplate?access_token=%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("draft_id", draft_id);
        paramMap.put("template_type", template_type);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSONObject.toJSONString(paramMap))
                .execute()
                .body();

        // 将响应体解析为AuthorizerList对象并返回
        return JSONObject.parseObject(body, ComResult.class);
    }



    /**
     * 获取模板列表
     * @param access_token 接口调用凭证
     * @param template_type 默认值是0，对应普通模板；可选1，对应标准模板库
     * @return ComResult
     */
    public static List<Template> getTemplateList(String access_token, int template_type){

        String url = "https://api.weixin.qq.com/wxa/gettemplatelist?access_token=%s&template_type=%d";

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.get(String.format(url,access_token,template_type))
                        .execute()
                        .body();

        JSONObject result = JSON.parseObject(body);

        if (result.getInteger("errcode") == 0){
            return JSONArray.parseArray(result.getJSONArray("template_list").toJSONString(),Template.class);
        }else{
            return new ArrayList<Template>();
        }
    }


    /**
     * 删除代码模板
     * @param access_token 接口调用凭证
     * @param template_id 要删除的模板 ID
     * @return ComResult
     */
    public static ComResult<?> deleteTemplate(String access_token, int template_id){

        // 定义url变量并赋值为指定的微信小程序模板删除接口地址
        String url = "https://api.weixin.qq.com/wxa/deletetemplate?access_token=%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("template_id", template_id);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSONObject.toJSONString(paramMap))
                .execute()
                .body();

        // 将响应体转换为ComResult对象并返回
        return JSONObject.parseObject(body, ComResult.class);
    }






}
