package com.srcker.wechat.support;

import cn.hutool.core.util.XmlUtil;
import com.srcker.wechat.support.response.WeChatResponse;
import com.srcker.wechat.support.request.WeChatRequest;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class WeChatSupport {

    private final String request;

    @Getter
    @Setter
    protected WeChatRequest wechatRequest;

    @Getter
    @Setter
    protected WeChatResponse wechatResponse;


    /**
     * 构建微信处理
     * @param request   微信服务发过来的http请求
     */
    public WeChatSupport(String request){
        this.request = request;
        this.wechatRequest = new WeChatRequest();
        this.wechatResponse = new WeChatResponse();
        this.setData();
    }



    private void setData() {
        // 获取文档
        Document document = XmlUtil.parseXml(this.request);
        // 获取根节点
        Element elementG = XmlUtil.getRootElement(document);
        // 使用根节点下的直接 转成对象
        this.wechatRequest = XmlUtil.xmlToBean(elementG, WeChatRequest.class);
        // 开始解密数据
        this.decrypt();
        // 分发数据
        this.dispatch();
    }


    protected void dispatch(){

    }


    private void decrypt(){

        byte[] aesKey = Base64.decodeBase64("BphNgvEUk0UQ1XVZmKU7RyFmtWf8UEXNArjHFxS9T4h=");

        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

            // 使用BASE64对密文进行解码
            byte[] encrypted = Base64.decodeBase64(wechatRequest.getEncrypt());

            byte[] original = cipher.doFinal(encrypted);
            byte[] bytes = decode(original);

            // 分离16位随机字符串,网络字节序和AppId
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);
            int xmlLength = recoverNetworkBytesOrder(networkOrder);

            String xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), StandardCharsets.UTF_8);

            // 获取文档
            Document document = XmlUtil.parseXml(xmlContent);
            // 获取根节点
            Element elementG = XmlUtil.getRootElement(document);
            // 使用根节点下的直接 转成对象
            this.wechatRequest = XmlUtil.xmlToBean(elementG, WeChatRequest.class);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }




    static byte[] decode(byte[] decrypted) {
        int pad = decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32) {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }


    int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }



}
