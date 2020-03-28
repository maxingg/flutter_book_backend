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
    public boolean addFav(Favourite fav) {
        return favouriteMapper.addFav(fav);
    }

    @Override
    public boolean delFav(Favourite fav) {
        return favouriteMapper.delFav(fav);
    }

    @Override
    public List<Integer> getAllFavById(int userId) {
        return favouriteMapper.getAllFavByUserId(userId);
    }

    @Override
    public boolean isExisted(Favourite fav) {
        if(favouriteMapper.findFav(fav) != null)
            return true;
        return false;
    }
}
