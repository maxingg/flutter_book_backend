package com.maxingg.flutter_book_backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.maxingg.flutter_book_backend.dao.entity.User;
import org.springframework.stereotype.Service;

@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getId()))  //将id保存到token里面
                    .sign(Algorithm.HMAC256(user.getPassWord()));   //password作为密钥secret
        return token;
    }
}
