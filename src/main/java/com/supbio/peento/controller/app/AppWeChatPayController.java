package com.supbio.peento.controller.app;

import com.supbio.peento.controller.manage.TestController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangqiang
 * @date 2018/9/27 13:37
 */
@Api(value = "AppWeChatPayController", description = "微信支付-相关API")
@RestController
@RequestMapping("/api/peento/app/weChatPay")
public class AppWeChatPayController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    // 微信统一下单接口路径
    private static final String UNIFORMORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";



}
