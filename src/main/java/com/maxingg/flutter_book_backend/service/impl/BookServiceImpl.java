package com.maxingg.flutter_book_backend.service.impl;

import com.maxingg.flutter_book_backend.dao.entity.Book;
import com.maxingg.flutter_book_backend.dao.mapper.BookMapper;
import com.maxingg.flutter_book_backend.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getBooksByCat(String cat) {
        return bookMapper.getBooksByCat(cat);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookMapper.getBooksByTitle(title);
    }

    @Override
    public List<Book> getBooksByIds(List<Integer> list) {
        return bookMapper.getBooksByIds(list);
    }

}
