package com.supbio.peento.servicecenter.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.supbio.peento.common.SendMessageConstant;

import java.util.Random;

/**
 * @author liangqiang
 * @date 2018/9/28 11:48
 */
public class AliSendMessage {

    /**
     * 调用阿里云SDK发送短信验证码
     * @param phone
     * @param code
     * @return
     * @throws ClientException
     */
    public static String sendMessage(String phone, String code) throws ClientException {

        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SendMessageConstant.ACCESS_KEY_ID,
                SendMessageConstant.ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SendMessageConstant.PRODUCT,
                    SendMessageConstant.DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //待发送手机号
        request.setPhoneNumbers(phone);
        //短信签名
        request.setSignName(SendMessageConstant.MESSAGE_SIGNATURES);
        //短信模板
        request.setTemplateCode(SendMessageConstant.MESSAGE_TEMPLATE);
        //模板内容：您的验证码为：${code}，该验证码5分钟内有效，请勿泄漏于他人，参数值
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            return "短信发送成功！";
        }
        return "短信发送失败！" + sendSmsResponse.getCode();
    }

    /**
     * 生成6位随机验证码
     * @return
     */
    public static String createCode(){
        Random random = new Random();
        String result = "";
        for (int i = 0 ; i < 6 ; i++){
            result += random.nextInt(10);
        }
        return result;
    }

}
