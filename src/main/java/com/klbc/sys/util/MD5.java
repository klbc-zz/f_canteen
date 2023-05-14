package com.klbc.sys.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    //MD5密码加密算法
    public static String convertMD5(String source){

        //char[] a = inStr.toCharArray();
        //for (int i = 0; i < a.length; i++){
        //    a[i] = (char) (a[i] ^ 't');
        //}
        //String s = new String(a);
        //return s;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte c : b) {
                int val = ((int) c) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
