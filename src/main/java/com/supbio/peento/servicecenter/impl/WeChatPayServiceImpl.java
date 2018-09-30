package com.supbio.peento.servicecenter.impl;

import com.supbio.peento.models.result.app.UnifiedOrderJson;
import com.supbio.peento.servicecenter.IWeChatPayService;
import com.supbio.peento.utils.DateUtil;
import com.supbio.peento.utils.HttpClientUtil;
import com.supbio.peento.utils.WeChatPayXmlUtil;
import com.supbio.peento.utils.WeChatMd5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

/**
 * Created by liangqiang on 2018/9/30.
 */
@Service
public class WeChatPayServiceImpl implements IWeChatPayService {

    private Logger logger = LoggerFactory.getLogger(WeChatPayServiceImpl.class);

    @Value("${pay.weChat.appSecret}")
    private String appSecret;
    @Value("${pay.weChat.appId}")
    private String appId;
    @Value("${pay.weChat.mchId}")
    private String mchId;
    @Value("${pay.weChat.body}")
    private String body;
    @Value("${pay.weChat.notifyUrl}")
    private String notifyUrl;
    @Value("${pay.weChat.tradeType}")
    private String tradeType;
    @Value("${pay.weChat.unifiedOrder}")
    private String unifiedOrder;

    @Override
    public UnifiedOrderJson unifiedOrder(String totalFee, String clientIp) {

        Map<String, String> paraMap = new HashMap<String, String>();
        //应用ID
        paraMap.put("appid", appId);
        //商户号
        paraMap.put("mch_id", mchId);
        //随机字符串，不长于32位
        paraMap.put("nonce_str", getRandomString(32));
        //商品描述(APP名字-实际商品名称)
        paraMap.put("body", body);
        //商户订单号，32位(yyyyMMddHHmmssSSS+字符串)
        paraMap.put("out_trade_no", getOutTradeNo());
        //订单总金额，单位为分
        paraMap.put("total_fee", totalFee);
        //用户端实际ip
        paraMap.put("spbill_create_ip", clientIp);
        //接收微信支付异步通知回调地址
        paraMap.put("notify_url", notifyUrl);
        //支付类型
        paraMap.put("trade_type", tradeType);
        //签名
        paraMap.put("sign", getSign(paraMap));

        try {
            //将Map转换为XML格式的字符串
            String mapToXmlStr = mapToXml(paraMap);
            logger.debug("Sending to weChat for unifiedOrder mapToXmlStr: " + mapToXmlStr);
            //发到微信支付系统，并接收XML响应
            String httpsRequest = HttpClientUtil.httpsRequest(unifiedOrder, "POST", mapToXmlStr);
            logger.debug("httpsRequest: " + httpsRequest);
            //将响应的XML转换为Map
            Map<String, String> xmlToMap = xmlToMap(httpsRequest);
            UnifiedOrderJson json = new UnifiedOrderJson();
            json.setReturnCode(xmlToMap.get("return_code"));
            json.setReturnMsg(xmlToMap.get("return_msg"));
            json.setResultCode(xmlToMap.get("result_code"));
            json.setAppId(xmlToMap.get("appid"));
            json.setPartnerId(xmlToMap.get("mch_id"));
            json.setPrepayId(xmlToMap.get("prepay_id"));
            json.setNonceStr(xmlToMap.get("nonce_str"));
            json.setSign(xmlToMap.get("sign"));
            logger.debug("Response from WeChat UnifiedOrderJson: {}", json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String notifyCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        String resultXml = new String(outSteam.toByteArray(), "utf-8");
        if (StringUtils.isBlank(resultXml)){
            return null;
        }
        try {
            Map<String, String> xmlToMap = xmlToMap(resultXml);
            outSteam.close();
            inStream.close();

            //还要判断签名

            String returnCode = xmlToMap.get("return_code");
            String resultCode = xmlToMap.get("result_code");
            Map<String, String> returnMap = new HashMap<String, String>();
            if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)){
                //还需做判断，防止微信重复回调接口返回

                //返回支付成功页面
                returnMap.put("return_code", "SUCCESS");
                returnMap.put("return_msg", "OK");
                return mapToXml(returnMap);
            }
            //返回支付失败
            returnMap.put("return_code", "FAIL");
            returnMap.put("return_msg", "return_code不正确");
            return mapToXml(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; ++i){
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 得到商户订单号
     * @return
     */
    public static String getOutTradeNo(){
        StringBuffer orderSNBuffer = new StringBuffer();
        String date2Str = DateUtil.date2Str(new Date(), DateUtil.DF_yyyyMMddHHmmssSSS);
        orderSNBuffer.append(date2Str);
        orderSNBuffer.append(getRandomString(15));
        return orderSNBuffer.toString();
    }

    /**
     * 得到加密值
     * @param map
     * @return
     */
    public static String getSign(Map<String, String> map){
        String[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuffer reqStr = new StringBuffer();
        for(String key : keys){
            String v = map.get(key);
            if(v != null && !v.equals("")){
                reqStr.append(key).append("=").append(v).append("&");
            }
        }
        reqStr.append("key").append("=").append(new WeChatPayServiceImpl().appSecret);
        return WeChatMd5Util.encode(reqStr.toString()).toUpperCase();
    }

    /**
     * 将Map转换为XML格式的字符串
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        org.w3c.dom.Document document = WeChatPayXmlUtil.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key: data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString();
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return output;
    }

    /**
     * XML格式字符串转换为Map
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilder documentBuilder = WeChatPayXmlUtil.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            throw ex;
        }

    }

}
