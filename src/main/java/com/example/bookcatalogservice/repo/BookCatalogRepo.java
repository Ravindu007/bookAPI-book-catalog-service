package com.example.bookcatalogservice.repo;

import com.example.bookcatalogservice.entity.BookCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookCatalogRepo extends JpaRepository<BookCatalog, Integer> {
    //give the id when we give the title
    @Query(value = "SELECT b.catalog_id FROM book_catalog b WHERE b.title = :title LIMIT 1", nativeQuery = true)
    Integer findByTitle(String title);
}
