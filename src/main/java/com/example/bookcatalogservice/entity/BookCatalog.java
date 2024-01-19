package com.example.bookcatalogservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookCatalog {

    @Id
    private Integer catalogId;
    private String title;
    private String author;
    private String isbn;
    private double price;
}
