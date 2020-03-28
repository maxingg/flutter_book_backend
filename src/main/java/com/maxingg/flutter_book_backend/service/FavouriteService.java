package com.maxingg.flutter_book_backend.service;

import com.maxingg.flutter_book_backend.dao.entity.Favourite;

import java.util.List;

public interface FavouriteService {
    boolean addFav(Favourite fav);

    boolean delFav(Favourite fav);

    List<Integer> getAllFavById(int userId);

    boolean isExisted(Favourite fav);
}
