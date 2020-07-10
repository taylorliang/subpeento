package com.supbio.peento.common;

import org.springframework.beans.factory.annotation.Value;

public class SendMessageConstant {

    //阿里云短信API产品名称（短信产品名固定，无需修改）
    public static final String PRODUCT = "Dysmsapi";

    //阿里云短信API产品域名（接口地址固定，无需修改）
    public static final String DOMAIN = "dysmsapi.aliyuncs.com";

    //阿里云个人的accessKeyId
    public static final String ACCESS_KEY_ID = "xxxxxxx";

    //阿里云个人的accessKeySecret
    public static final String ACCESS_KEY_SECRET = "xxxxxxxxxxxx";

    //阿里云短信签名
    public static final String MESSAGE_SIGNATURES = "蟠桃医生";

    //阿里云短信模板
    public static final String MESSAGE_TEMPLATE = "SMS_146800470";

}
