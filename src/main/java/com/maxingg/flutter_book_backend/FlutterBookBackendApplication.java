package com.maxingg.flutter_book_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.maxingg.flutter_book_backend.dao.mapper")
public class FlutterBookBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlutterBookBackendApplication.class, args);
    }

}
