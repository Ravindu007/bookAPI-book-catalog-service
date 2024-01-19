package com.example.bookcatalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookCatalogDto {
    private Integer catalogId;
    private String title;
    private String author;
    private String isbn;
    private double price;
}
