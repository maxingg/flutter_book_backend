package com.maxingg.flutter_book_backend.dao.mapper;

import com.maxingg.flutter_book_backend.dao.entity.Favourite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavouriteMapper {
    boolean addFav(@Param("userId") int userId, @Param("bookId") int bookId);

    boolean delFav(@Param("userId") int userId, @Param("bookId") int bookId);

    List<Integer> getAllFavByUserId(int userId);

    Favourite findFav(@Param("userId") int userId, @Param("bookId") int bookId);
}
