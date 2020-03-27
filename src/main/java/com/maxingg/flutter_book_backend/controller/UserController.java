package com.maxingg.flutter_book_backend.controller;

import cn.hutool.json.JSONObject;
import com.maxingg.flutter_book_backend.annotation.UserLoginToken;
import com.maxingg.flutter_book_backend.dao.entity.User;
import com.maxingg.flutter_book_backend.service.TokenService;
import com.maxingg.flutter_book_backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "UserController", description = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    TokenService tokenService;

    @ApiOperation("获取所有用户列表")
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUserList() {
        return userService.queryAllUsers();
    }

    @ApiOperation("登录")
    @PostMapping("/user")
    public Object login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!bCryptPasswordEncoder.matches(user.getPassWord(), userForBase.getPassWord())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @ApiOperation("注册")
    @PostMapping("/newuser")
    public Object register(@RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        JSONObject jsonObject = new JSONObject();
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        if (userService.findByUsername(user) != null) {
            jsonObject.put("message", "账户已存在");
            return jsonObject;
        }
        userService.addUser(user);
        jsonObject.put("status", "201");
        return jsonObject;
    }

    @UserLoginToken
    @GetMapping("/Message")
    public String getMessage(){
        return "你已通过验证";
    }
}
