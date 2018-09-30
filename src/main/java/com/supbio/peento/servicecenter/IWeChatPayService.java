package com.supbio.peento.servicecenter;

import com.supbio.peento.models.result.app.UnifiedOrderJson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by liangqiang on 2018/9/30.
 */
public interface IWeChatPayService {

    /**
     * 统一下单接口
     * @param totalFee 订单总金额，单位为分
     * @param clientIp 客户端IP
     * @return
     */
    public UnifiedOrderJson unifiedOrder(String totalFee, String clientIp);

    /**
     * 微信支付通知回调
     * @param request
     * @param response
     * @return
     */
    public String notifyCallback(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
