package com.supbio.peento.servicecenter.pay;

import com.supbio.peento.utils.HttpClientUtil;
import com.supbio.peento.utils.WeiMd5Util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class WeChatPay {

    //商户秘钥
    public static final String  AppSercret = "qx2016well03com14wuyou2016xueche";

    //微信统一下单接口路径
    private static final String UNIFORMORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static void weChatPay(String totalFee){

        Map<String, String> paraMap = new HashMap<String, String>();
        //应用ID appid
        paraMap.put("appid", "appid");
        //商户号 mch_id
        paraMap.put("mch_id", "mch_id");
        //随机字符串，不长于32位 nonce_str
        paraMap.put("nonce_str", getRandomString());
        //商品描述(APP名字-实际商品名称)
        paraMap.put("body", "蟠桃医生-购买服务");
        //商户订单号 out_trade_no
        paraMap.put("out_trade_no", "out_trade_no");
        //订单总金额，单位为分
        paraMap.put("total_fee", totalFee);
        //用户端实际ip spbill_create_ip
        paraMap.put("spbill_create_ip", getHostIp());
        //接收微信支付异步通知回调地址
        paraMap.put("notify_url", "http://m.ebiaotong.com/WXPay/notify");
        //支付类型
        paraMap.put("trade_type", "APP");
        //签名 sign
        paraMap.put("sign", getSign(paraMap));

        System.out.println("====mapToXml====" + mapToXml(paraMap));

    }

    /**
     * 得到随机字符串
     * @return
     */
    public static String getRandomString(){
        int length = 32;
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
        reqStr.append("key").append("=").append(WeChatPay.AppSercret);
        return WeiMd5Util.encode(reqStr.toString()).toUpperCase();
    }

    /**
     * 得到本地机器的IP
     * @return
     */
    private static String getHostIp(){
        String ip = "";
        try{
            ip = InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 将map转换成xml字符串
     * @param map
     * @return
     */
    public static String mapToXml(Map<String, String> map){
        String xmlResult = "";
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<xml>");
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
            sb.append("<" + key + ">" + map.get(key) + "</" + key + ">");
//            System.out.println();
        }
        sb.append("</xml>");
        xmlResult = sb.toString();
        return xmlResult;
    }

    public static Map<String, String> getInfoByXml(String xmlStr){
        try{
            Map<String, String> m = new HashMap<String, String>();
            Document d = DocumentHelper.parseText(xmlStr);
            Element root = d.getRootElement();
            for ( Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                String name = element.getName();
                if(!element.isTextOnly()){
                    //不是字符串 跳过。确定了微信放回的xml只有根目录
                    continue;
                }else{
                    m.put(name, element.getTextTrim());
                }
            }
            //对返回结果做校验.去除sign 字段再去加密
            String retSign = m.get("sign");
            m.remove("sign");
            String rightSing = getSign(m);
            if(rightSing.equals(retSign)){
                return m;
            }
        }catch(DocumentException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
//        System.out.println(getRandomString());
//        System.out.println("得到本地机器的IP: " + getHostIp());
        weChatPay("1");
    }


}
