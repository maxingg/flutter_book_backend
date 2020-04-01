package com.maxingg.flutter_book_backend.service.impl;

import com.maxingg.flutter_book_backend.dao.entity.Favourite;
import com.maxingg.flutter_book_backend.dao.mapper.FavouriteMapper;
import com.maxingg.flutter_book_backend.service.FavouriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteMapper favouriteMapper;
    @Override
    public boolean addFav(int userId, int bookId) {
        return favouriteMapper.addFav(userId, bookId);
    }

    @Override
    public boolean delFav(int userId, int bookId) {
        return favouriteMapper.delFav(userId, bookId);
    }

    @Override
    public List<Integer> getAllFavById(int userId) {
        return favouriteMapper.getAllFavByUserId(userId);
    }

    @Override
    public boolean isExisted(int userId, int bookId) {
        if(favouriteMapper.findFav(userId, bookId) != null)
            return true;
        return false;
    }
}
