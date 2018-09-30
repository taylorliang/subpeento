package com.supbio.peento.models.params.app;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/9/30.
 */
public class PayNowParam {

    @ApiModelProperty(value = "订单总金额，单位为分", required = true)
    private String totalFee;

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

}
