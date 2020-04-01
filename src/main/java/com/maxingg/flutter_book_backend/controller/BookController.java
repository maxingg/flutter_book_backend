package com.maxingg.flutter_book_backend.controller;

import com.maxingg.flutter_book_backend.annotation.UserLoginToken;
import com.maxingg.flutter_book_backend.dao.entity.Book;
import com.maxingg.flutter_book_backend.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "BookController", description = "图书管理")
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation("获取某一分类中的精选书籍")
    @UserLoginToken
    @GetMapping("/category/{cat}")
    @ResponseBody
    public List<Book> getBooksByCategory(@PathVariable("cat") String cat) throws NotFoundException{
        return bookService.getBooksByCat(cat);
    }

    @UserLoginToken
    @ApiOperation("搜索特定书籍")
    @GetMapping("/books/{title}")
    @ResponseBody
    public List<Book> getBookByTitle(@PathVariable("title") String title) throws NotFoundException {
        return bookService.getBooksByTitle(title);
    }
}
