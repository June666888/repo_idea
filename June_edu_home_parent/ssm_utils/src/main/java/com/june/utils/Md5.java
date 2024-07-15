package com.june.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5 {

    public final static  String md5key = "june";
    /**
     * MD5方法
     * @param text 明文 如：123456
     * @param key 密钥，可以是传过来的也可以是这个类里指定的  如：lagou
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text+key);
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    /**
     * MD5验证方法，登录功能主要是调用这个方法
     * @param text 明文
     * @param key 密钥
     * @param md5 密文（查询出来的）
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if(md5Text.equalsIgnoreCase(md5))
        {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        // 注册  用户名：tom  密码：123456
        // 添加用户的时候，要将明文密码转换成密文密码
        String june = Md5.md5("123456", "june");
        System.out.println(june);//ae0ee2d742bc710b3497027480e8f308

        // 登录  用户名：tom  密码：123456
        // 1. 根据前台传递过来的用户名tom先在user表中查询出对应的密文密码
        //  select * from user where username = tom

        // 2.调用 verify 方法进行密码的校验
        boolean verify = Md5.verify("123456", "june", "ae0ee2d742bc710b3497027480e8f308");
        System.out.println(verify);

    }
}
