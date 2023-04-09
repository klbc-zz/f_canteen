package com.klbc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mysql.jdbc.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    // 盐值
    private static final String SALT = "13770389674";

    private static final String JWT_KEY_PHONE = "phone";

    /**
     * 根据手机号生成token，一天过期
     * 如何生成，这里可以自己定义。
     * 使用JWT自身的过期时间设置那就不需要Redis了
     */
    public static String generateToken(String phone) {
        Map<String, String> map = new HashMap<String, String>() {{
            put(JWT_KEY_PHONE, phone);
            // 这里还可以再加东西
        }};

        // 过期时间自己定义，可以在全局定义一个常量，然后加上现在的时间得到过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        // 填充数据负载
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);

        return builder.withExpiresAt(date).sign(Algorithm.HMAC256(SALT));
    }

    /**
     * 解析token，通过token拿到里面的数据比如说用户名、手机号之类的数据
     */
    public static String parseToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
        String phone = jwt.getClaim(JWT_KEY_PHONE).asString();
        return phone;
    }

    public static void main(String[] args) {
        String phone = "13770366963";
        String s = generateToken(phone);
        System.out.println(s);
        String sphone = parseToken(s);
        System.out.println("手机号：" + phone);
        int a = 123;
        String b = String.valueOf(a);

        b.substring(1,2);
    }

}
