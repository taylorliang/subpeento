package com.supbio.peento.models.result.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/9/30.
 */
@ApiModel
public class UnifiedOrderJson {

    @ApiModelProperty(value = "返回状态码：SUCCESS，FAIL")
    private String returnCode;

    @ApiModelProperty(value = "返回信息")
    private String returnMsg;

    @ApiModelProperty(value = "业务结果：SUCCESS，FAIL")
    private String resultCode;

    @ApiModelProperty(value = "应用APPID")
    private String appId;

    @ApiModelProperty(value = "商户号")
    private String partnerId;

    @ApiModelProperty(value = "预支付交易会话ID")
    private String prepayId;

    @ApiModelProperty(value = "随机字符串")
    private String nonceStr;

    @ApiModelProperty(value = "签名")
    private String sign;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
