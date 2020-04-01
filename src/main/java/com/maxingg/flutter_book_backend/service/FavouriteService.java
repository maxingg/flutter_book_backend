package com.maxingg.flutter_book_backend.service;

import com.maxingg.flutter_book_backend.dao.entity.Favourite;

import java.util.List;

public interface FavouriteService {
    boolean addFav(int userId, int bookId);

    boolean delFav(int userId, int bookId);

    List<Integer> getAllFavById(int userId);

    boolean isExisted(int userId, int bookId);
}
