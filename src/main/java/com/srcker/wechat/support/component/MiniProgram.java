package com.srcker.wechat.support.component;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.srcker.wechat.support.entity.ComResult;
import com.srcker.wechat.support.entity.Company;
import com.srcker.wechat.support.entity.PersonalMp;

import java.util.HashMap;


public class MiniProgram {

    private static final SerializeConfig CONFIG = new SerializeConfig();

    static {
        CONFIG.propertyNamingStrategy = com.alibaba.fastjson.PropertyNamingStrategy.SnakeCase;
    }

    /**
     * 快速注册企业小程序
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 第三方平台接口调用凭证
     * @param action 查询创建任务状态
     * @param company 公司信息
     * @return ComResult
     * @create 2023/11/11 19:33:39
     */
    public static ComResult<?> registerMiniprogram(String access_token,String action, Company company){
        String url = "https://api.weixin.qq.com/cgi-bin/component/fastregisterweapp?action=%s&component_access_token=%s";


        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,action,access_token))
                .body(JSON.toJSONString(company, CONFIG, SerializerFeature.NotWriteDefaultValue))
                .execute()
                .body();

        // 将响应体解析为AuthorizerList对象并返回
        return JSONObject.parseObject(body, ComResult.class);

    }


    /**
     * 复用公众号主体快速注册小程序
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @param ticket 公众号扫码授权的凭证
     * @return ComResult
     * @create 2023/11/11 19:44:26
     */
    public static ComResult<?> registerMiniprogramByOffiaccount(String access_token,String ticket){
        String url = "https://api.weixin.qq.com/cgi-bin/account/fastregister?access_token==%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("ticket", ticket);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSON.toJSONString(paramMap))
                .execute()
                .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject result = JSONObject.parseObject(body);

        // 判断errcode字段是否为0，如果是则解析服务器域名信息并返回ComResult对象，否则直接解析返回ComResult对象
        if (result.getInteger("errcode") == 0){
            // 构建请求参数
            HashMap<String, Object> retMap = new HashMap<>();
            paramMap.put("appid", result.getString("appid"));
            paramMap.put("authorization_code", result.getString("authorization_code"));
            paramMap.put("is_wx_verify_succ", result.getBooleanValue("is_wx_verify_succ"));
            paramMap.put("is_link_succ", result.getBooleanValue("is_link_succ"));
            return new ComResult<>(retMap);
        }else{
            return JSONObject.parseObject(body, ComResult.class);
        }
    }


    /**
     * 快速注册个人小程序
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @param action 请求类型
     * @param personal 个人信息
     * @return ComResult
     * @create 2023/11/11 20:02:30
     */
    public static ComResult<?> fastRegisterPersonalMp(String access_token,String action, PersonalMp personal){
        String url = "https://api.weixin.qq.com/wxa/component/fastregisterpersonalweapp?action=%s&component_access_token=%s";


        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,action,access_token))
                .body(JSON.toJSONString(personal, CONFIG, SerializerFeature.NotWriteDefaultValue))
                .execute()
                .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject result = JSONObject.parseObject(body);

        // 判断errcode字段是否为0，如果是则解析服务器域名信息并返回ComResult对象，否则直接解析返回ComResult对象
        if (result.getInteger("errcode") == 0){
            // 构建请求参数
            personal.setTaskid(result.getString("taskid"));
            personal.setAuthorizeUrl(result.getString("authorize_url"));
            personal.setStatus(result.getInteger("status"));
            return new ComResult<>(personal);
        }else{
            return JSONObject.parseObject(body, ComResult.class);
        }

    }


    /**
     * 注册适用小程序
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @param name 小程序名称，昵称半自动设定，强制后缀“的体验小程序”
     * @param openid 微信用户的openid
     * @return ComResult
     * @create 2023/11/12 05:39:30
     */
    public static ComResult<?> registerBetaMiniprogram(String access_token,String name, String openid){
        String url = "https://api.weixin.qq.com/wxa/component/fastregisterbetaweapp?access_token=%s";


        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("openid", openid);


        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSON.toJSONString(paramMap))
                .execute()
                .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject result = JSONObject.parseObject(body);

        // 判断errcode字段是否为0，如果是则解析服务器域名信息并返回ComResult对象，否则直接解析返回ComResult对象
        if (result.getInteger("errcode") == 0){

            HashMap<String, Object> resMap = new HashMap<>();
            resMap.put("name", result.getString("unique_id"));
            resMap.put("openid",  result.getString("authorize_url"));

            return new ComResult<>(resMap);
        }else{
            return JSONObject.parseObject(body, ComResult.class);
        }

    }

    /**
     * 修改试用小程序名称
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @param name 小程序名称，昵称半自动设定，强制后缀“的体验小程序”
     * @return ComResult
     * @create 2023/11/12 05:39:30
     */
    public static ComResult<?> modifyBetaMiniprogramNickName(String access_token,String name){
        String url = "https://api.weixin.qq.com/wxa/setbetaweappnickname?access_token=%s";


        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);


        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSON.toJSONString(paramMap))
                .execute()
                .body();

        // 将返回的字符串解析为ComResult对象
        return JSONObject.parseObject(body, ComResult.class);

    }


    /**
     * 试用小程序快速认证
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @param company 企业法人认证需要的信息
     * @return ComResult
     * @create 2023/11/12 05:44:26
     */
    public static ComResult<?> verfifyBetaMiniprogram(String access_token, Company company){
        String url = "https://api.weixin.qq.com/wxa/verifybetaweapp?access_token=%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("verify_info", JSON.toJSONString(company, CONFIG, SerializerFeature.NotWriteDefaultValue));


        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSON.toJSONString(paramMap, CONFIG, SerializerFeature.NotWriteDefaultValue))
                .execute()
                .body();

        // 将响应体解析为AuthorizerList对象并返回
        return JSONObject.parseObject(body, ComResult.class);

    }



}
