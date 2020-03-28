package com.maxingg.flutter_book_backend.controller;

import com.maxingg.flutter_book_backend.annotation.UserLoginToken;
import com.maxingg.flutter_book_backend.dao.entity.Book;
import com.maxingg.flutter_book_backend.dao.entity.Favourite;
import com.maxingg.flutter_book_backend.service.BookService;
import com.maxingg.flutter_book_backend.service.FavouriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "FavouriteController", description = "用户爱好管理")
@RestController
@RequestMapping("/api/fav")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @Autowired
    private BookService bookService;

    @ApiOperation("用户标记喜欢")
    @UserLoginToken
    @PostMapping("")
    public ResponseEntity<String> addFav(@RequestBody Favourite fav) throws Exception {
        if(favouriteService.isExisted(fav))
            return new ResponseEntity<>("记录已存在", HttpStatus.FORBIDDEN);
        boolean res = favouriteService.addFav(fav);
        if(res == false)
            return new ResponseEntity<>("添加失败", HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

    @ApiOperation("用户取消喜欢")
    @UserLoginToken
    @DeleteMapping("")
    public ResponseEntity<String> delFav(@RequestBody Favourite fav) throws NotFoundException {
        if(!favouriteService.isExisted(fav))
            return new ResponseEntity<>("记录不存在", HttpStatus.FORBIDDEN);
        boolean res = favouriteService.delFav(fav);
        if(res == false)
            return new ResponseEntity<>("取消失败", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("取消成功", HttpStatus.OK);
    }

    @ApiOperation("获取用户所有喜欢书籍, 实现云同步")
    @UserLoginToken
    @GetMapping("")
    public ResponseEntity<List<Book>> getAllFavBooks(@RequestParam("userId") int userId) throws NotFoundException {
        List<Book> res = new ArrayList<>();
        List<Integer> list = favouriteService.getAllFavById(userId);
        if(list != null && list.size() != 0) {
            res = bookService.getBooksByIds(list);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }

}
