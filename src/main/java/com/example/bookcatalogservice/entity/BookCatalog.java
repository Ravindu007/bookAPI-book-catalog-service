package com.example.bookcatalogservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catalogId;

    private String title;
    private String author;
    private String isbn;
    private double price;

    //auditing columns
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isDeleted = false;

}
