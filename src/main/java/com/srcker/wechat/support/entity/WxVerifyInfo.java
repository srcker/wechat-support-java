package com.srcker.wechat.support.entity;

import lombok.Data;

@Data
public class WxVerifyInfo {

    private boolean qualificationVerify;
    private boolean namingVerify;
    private boolean annualReview;
    private long annualReviewBeginTime;
    private long annualReviewEndTime;

}
