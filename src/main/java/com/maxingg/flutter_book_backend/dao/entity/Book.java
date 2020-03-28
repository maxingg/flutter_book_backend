package com.maxingg.flutter_book_backend.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private String imageUrl;
    private String title;
    private String author;
    private String category;
    private String description;
    private String link;
}
