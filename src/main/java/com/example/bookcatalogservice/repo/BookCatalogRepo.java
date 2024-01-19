package com.example.bookcatalogservice.repo;

import com.example.bookcatalogservice.entity.BookCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCatalogRepo extends JpaRepository<BookCatalog, Integer> {
    //give the id when we give the title
    @Query(value = "SELECT b.catalog_id FROM book_catalog b WHERE b.title = :title LIMIT 1", nativeQuery = true)
    Integer findByTitle(String title);

    @Query(value = "SELECT b.catalog_id FROM book_catalog b WHERE b.title = :title ORDER BY b.catalog_id DESC LIMIT 1", nativeQuery = true)
    Integer findLastRecordByTitle(String title);


    @Query(value = "SELECT * FROM book_catalog WHERE is_deleted = false", nativeQuery = true)
    List<BookCatalog> findAllExistingCatalogs();
}
