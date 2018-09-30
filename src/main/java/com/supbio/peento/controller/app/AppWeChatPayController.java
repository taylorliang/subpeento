package com.supbio.peento.controller.app;

import com.supbio.peento.common.response.AppResultObj;
import com.supbio.peento.models.params.app.PayNowParam;
import com.supbio.peento.models.result.app.UnifiedOrderJson;
import com.supbio.peento.servicecenter.IWeChatPayService;
import com.supbio.peento.utils.JacksonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liangqiang on 2018/9/27.
 */
@Api(value = "AppWeChatPayController", description = "微信支付-相关API")
@RestController
@RequestMapping("/api/app/weChatPay")
public class AppWeChatPayController extends BaseAppController {

    @Autowired
    private IWeChatPayService weChatPayService;

    @ApiOperation(value = "立即支付接口", notes = "立即支付接口", response = UnifiedOrderJson.class)
    @RequestMapping(value = "/payNow", method = RequestMethod.POST)
    public AppResultObj<UnifiedOrderJson> payNow(@RequestBody PayNowParam param){
        logger.info("/api/app/weChatPay/payNow: {}", JacksonUtil.toJSON(param));
        UnifiedOrderJson json = weChatPayService.unifiedOrder(param.getTotalFee(), getClientIp());
        if (json != null){
            return AppResultObj.success(json);
        }
        return null;
    }

    @ApiOperation(value = "微信支付通知回调接口", notes = "微信支付通知回调接口")
    @RequestMapping(value = "/notifyCallback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResultObj<String> notifyCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("/api/app/weChatPay/notifyCallback: {}", "微信支付通知回调");
        String notifyCallback = weChatPayService.notifyCallback(request, response);
        logger.info("notifyCallback--:"+notifyCallback);
        return AppResultObj.success(notifyCallback);
    }

//    /**
//     * 微信支付回调函数
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(value = "/callback")
//    public void callBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        InputStream is = request.getInputStream();
//        HashMap<String, String> map = new HashMap<String, String>();
//        SAXReader reader = new SAXReader();
//        Document document = null;
//        try {
//            document = reader.read(is);
//        } catch (DocumentException e1) {
//            e1.printStackTrace();
//        }
//        String out_trade_no = "";//订单ID
//        String total_fee = "";   //订单金额
//        Element root = document.getRootElement();
//        List<Element> list = root.elements();
//        // 获取微信返回值信息
//        for (Element e : list) {
//            map.put(e.getName().trim(), e.getText().trim());
//            if (e.getName().trim().equals("out_trade_no")) {
//                out_trade_no = e.getText().trim();
//            } else if (e.getName().trim().equals("cash_fee")) {
//                total_fee = e.getText().trim();
//            }
//        }
//        is.close();
//        // 克隆传入的信息并进行验签，建议一定要验证签名，防止返回值被篡改
//        HashMap<String, String> signMap = (HashMap<String, String>) map.clone();
//        signMap.remove("sign");
//        // 这里的wx_key 是用户自定义的支付key
////        String key= PropertiesUtil.getValue("wechat.properties","wx_key");
////        String sign = SignatureUtils.signature(signMap,key);
////        if (!sign.equals(map.get("sign"))) {
////            return;
////        }
//        // 信息处理
//        String result_code = map.get("result_code");
//        try {
//            if ("SUCCESS".equals(result_code)) {
//                //由于微信后台会同时回调多次，所以需要做防止重复提交操作的判断
//                // 此处放防止重复提交操作
//            } else if ("FAIL".equals(result_code)) {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        //这里是验证返回值没问题了，可以写具体的支付成功的逻辑
//        // 返回信息，防止微信重复发送报文
//        String result = "<xml>"
//                + "<return_code><![CDATA[SUCCESS]]></return_code>"
//                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
//        PrintWriter out = new PrintWriter(response.getOutputStream());
//        out.print(result);
//        out.flush();
//        out.close();
//    }


}
