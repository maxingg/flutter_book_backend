package com.maxingg.flutter_book_backend.controller;

import com.maxingg.flutter_book_backend.annotation.UserLoginToken;
import com.maxingg.flutter_book_backend.dao.entity.User;
import com.maxingg.flutter_book_backend.service.TokenService;
import com.maxingg.flutter_book_backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<User>> getUserList() throws IOException {
        List<User> users = userService.queryAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation("登录")
    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            map.put("message","登录失败,用户不存在");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }else {
            if (!bCryptPasswordEncoder.matches(user.getPassWord(), userForBase.getPassWord())){
                map.put("message","登录失败,密码错误");
                return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
            }else {
                String token = tokenService.getToken(userForBase);
                map.put("token",token);
                map.put("user", userForBase);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        }
    }

    @ApiOperation("注册")
    @PostMapping("/newuser")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        if (userService.findByUsername(user) != null) {
            map.put("message", "账户已存在");
            return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
        }
        userService.addUser(user);
        map.put("status", "201");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @UserLoginToken
    @GetMapping("/Message")
    public String getMessage(){
        return "你已通过验证";
    }
}
