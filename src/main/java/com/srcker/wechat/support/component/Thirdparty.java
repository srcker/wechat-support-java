package com.srcker.wechat.support.component;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srcker.wechat.support.entity.ComResult;
import com.srcker.wechat.support.entity.DomainConfirmFile;
import com.srcker.wechat.support.entity.DraftList;
import com.srcker.wechat.support.entity.ServerDomain;

import java.util.ArrayList;
import java.util.HashMap;


public class Thirdparty {

    /**
     * 设置第三方平台服务器域名
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @param action 操作类型
     * @param wxa_server_domain 最多可以添加1000个服务器域名
     * @param is_modify_published_together 是否同时修改“全网发布版本的值”
     * @return ComResult<ServerDomain>
     * @create 2023/11/11 10:37:24
     */
    public static ComResult<?> modifyThirdpartyServerDomain(String access_token, String action, String wxa_server_domain, boolean is_modify_published_together ){

        String url = "https://api.weixin.qq.com/cgi-bin/component/modify_wxa_server_domain?access_token=%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_modify_published_together", is_modify_published_together);
        paramMap.put("wxa_server_domain", wxa_server_domain);
        paramMap.put("action", action);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSONObject.toJSONString(paramMap))
                .execute()
                .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject result = JSONObject.parseObject(body);

        // 判断errcode字段是否为0，如果是则解析服务器域名信息并返回ComResult对象，否则直接解析返回ComResult对象
        if (result.getInteger("errcode") == 0){
            ServerDomain serverDomain = new ServerDomain();
            serverDomain.setPublished(result.getString("published_wxa_server_domain"));
            serverDomain.setInvalid(result.getString("invalid_wxa_server_domain"));
            serverDomain.setTesting(result.getString("testing_wxa_server_domain"));
            return new ComResult<>(serverDomain);
        }else{
            return JSONObject.parseObject(body, ComResult.class);
        }
    }


    /**
     * 获取第三方平台业务域名校验文件
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @return DomainConfirmFile
     * @create 2023/11/11 12:22:42
     */
    public static DomainConfirmFile getThirdpartyJumpDomainConfirmFile(String access_token){
        String url = "https://api.weixin.qq.com/cgi-bin/component/get_domain_confirmfile?access_token=%s";

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                    .execute()
                    .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject result = JSONObject.parseObject(body);

        DomainConfirmFile domainConfirmFile = new DomainConfirmFile();

        if (result.getInteger("errcode") == 0){
            domainConfirmFile.setFileContent(result.getString("file_content"));
            domainConfirmFile.setFileName(result.getString("file_name"));
            return domainConfirmFile;
        }else{
            return domainConfirmFile;
        }
    }


    /**
     * 设置第三方平台业务域名
     * @author SINDA
     * @email sinda@srcker.com
     * @param access_token 接口调用凭证
     * @param action 操作类型。可选值请看下文
     * @param wxa_jump_h5_domain 最多可以添加200个小程序业务域名
     * @param is_modify_published_together 是否同时修改“全网发布版本的值”
     * @return ComResult
     * @create 2023/11/11 12:26:26
     */
    public static ComResult<?> modifyThirdpartyJumpDomain(String access_token, String action, String wxa_jump_h5_domain, boolean is_modify_published_together ){

        String url = "https://api.weixin.qq.com/cgi-bin/component/modify_wxa_jump_domain?access_token=%s";

        // 构建请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_modify_published_together", is_modify_published_together);
        paramMap.put("wxa_jump_h5_domain", wxa_jump_h5_domain);
        paramMap.put("action", action);

        // 发送HTTP POST请求，并获取响应体
        String body = HttpRequest.post(String.format(url,access_token))
                .body(JSONObject.toJSONString(paramMap))
                .execute()
                .body();

        // 将返回的字符串解析为JSONObject对象
        JSONObject result = JSONObject.parseObject(body);

        // 判断errcode字段是否为0，如果是则解析服务器域名信息并返回ComResult对象，否则直接解析返回ComResult对象
        if (result.getInteger("errcode") == 0){
            ServerDomain serverDomain = new ServerDomain();
            serverDomain.setPublished(result.getString("published_wxa_jump_h5_domain"));
            serverDomain.setInvalid(result.getString("testing_wxa_jump_h5_domain"));
            serverDomain.setTesting(result.getString("invalid_wxa_jump_h5_domain"));
            return new ComResult<>(serverDomain);
        }else{
            return JSONObject.parseObject(body, ComResult.class);
        }
    }


}
