package test;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestofMD5 {
    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }
    public static String MD5(String source) {
        System.out.println(source);
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
    public static void main(String[] args) {
        String a = "123456";//EFG@AB
        String b = "9A952CD91000872A8D7D1F5EE0C87317";
        a = MD5(a);
        System.out.println(a);
        System.out.println(a.equals(b));
    }
}
