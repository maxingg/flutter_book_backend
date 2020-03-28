package com.maxingg.flutter_book_backend.service;


import com.maxingg.flutter_book_backend.dao.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooksByCat(String cat);

    List<Book> getBooksByTitle(String title);

    List<Book> getBooksByIds(List<Integer> list);
}
