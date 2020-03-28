package com.maxingg.flutter_book_backend.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favourite {
    private int id;
    private int userId;
    private int bookId;
}
