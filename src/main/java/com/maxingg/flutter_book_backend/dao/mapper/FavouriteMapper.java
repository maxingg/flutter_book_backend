package com.maxingg.flutter_book_backend.dao.mapper;

import com.maxingg.flutter_book_backend.dao.entity.Favourite;

import java.util.List;

public interface FavouriteMapper {
    boolean addFav(Favourite fav);

    boolean delFav(Favourite fav);

    List<Integer> getAllFavByUserId(int userId);

    Favourite findFav(Favourite fav);
}
