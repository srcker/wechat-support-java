package com.srcker.wechat.support.request;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@Data
public class WeChatRequest {

    @XmlElement(name="AppId")
    private String AppId;


    // 验证票据 第三方平台使用
    @XmlElement(name="ComponentVerifyTicket")
    private String ComponentVerifyTicket;

    @XmlElement(name="ToUserName")
    private String ToUserName;

    @XmlElement(name="FromUserName")
    private String FromUserName;

    @XmlElement(name="CreateTime")
    private String CreateTime;

    @XmlElement(name="MsgType")
    private String MsgType;

    @XmlElement(name="InfoType")
    private String InfoType;

    @XmlElement(name="Event")
    private String Event;

    // 加密数据包
    @XmlElement(name="Encrypt")
    private String Encrypt;

    // 消息签名
    @XmlElement(name="MsgSignature")
    private String MsgSignature;

    @XmlElement(name="EventKey")
    private String EventKey;

    @XmlElement(name="MsgId")
    private String MsgId;

    @XmlElement(name="Content")
    private String Content;

    @XmlElement(name="Location_X")
    private String Location_X;

    @XmlElement(name="Location_Y")
    private String Location_Y;

    @XmlElement(name="Scale")
    private String Scale;

    @XmlElement(name="Label")
    private String Label;

    @XmlElement(name="Title")
    private String Title;

    @XmlElement(name="Description")
    private String Description;

    @XmlElement(name="Url")
    private String Url;

    @XmlElement(name="PicUrl")
    private String PicUrl;

    @XmlElement(name="MediaId")
    private String MediaId;

    @XmlElement(name="Format")
    private String Format;

    @XmlElement(name="Status")
    private String Status;

    // 上报地理位置事件Latitude
    @XmlElement(name="Latitude")
    private String Latitude;

    //上报地理位置事件Longitude
    @XmlElement(name="Longitude")
    private String Longitude;

    // 上报地理位置事件Precision
    @XmlElement(name="Precision")
    private String Precision;

    // 扫描带参数二维码事件Ticket
    @XmlElement(name="Ticket")
    private String Ticket;

    // 视频消息 ThumbMediaId 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
    @XmlElement(name="ThumbMediaId")
    private String ThumbMediaId;

    @XmlElement(name="Recognition")
    private String Recognition;



}
