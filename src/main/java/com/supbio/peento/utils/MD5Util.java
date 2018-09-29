package com.supbio.peento.utils;

import java.security.MessageDigest;

public class MD5Util {

    /**
     * 16进制下数字到字符的映射数组
     */
    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 对字符串进行MD5编码
     * @param origin
     * @param charsetName
     * @return
     */
    public static String MD5Encode(String origin, String charsetName) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetName == null || "".equals(charsetName))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    /**
     * MD5加密，把字节数组转换为十六进制字符串
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));
        return resultSb.toString();
    }

    /**
     * 将字节转化成十六进制的字符串
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    //将inputStr加密
    public static String createPasswordByMD5(String inputStr){
        return MD5Encode(inputStr, null);
    }

    //验证密码是否正确
    public static boolean authenticatePassword(String pass , String inputStr){
        if(pass.equals((MD5Encode(inputStr, null)))){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args){
        String password = MD5Util.createPasswordByMD5("8888");
        System.out.println("对8888用MD5加密后："+password);
        String inputstr = "1234";
        System.out.println("1234与密码相同？"+ MD5Util.authenticatePassword(password, inputstr));
        inputstr = "123456";
        System.out.println("123456与密码相同？"+ MD5Util.authenticatePassword(password, inputstr));
    }

}
