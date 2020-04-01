package com.maxingg.flutter_book_backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.maxingg.flutter_book_backend.dao.entity.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token = "";
        Calendar nowTime = Calendar.getInstance();
        //有7天有效期
        nowTime.add(Calendar.DATE, 7);
        Date expiresDate = nowTime.getTime();

        token = JWT.create().withAudience(String.valueOf(user.getId())).withExpiresAt(expiresDate)  //将id保存到token里面
                    .sign(Algorithm.HMAC256(user.getPassWord()));   //password作为密钥secret
        return token;
    }
}
