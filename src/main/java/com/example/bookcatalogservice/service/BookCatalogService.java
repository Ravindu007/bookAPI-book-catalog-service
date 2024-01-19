package com.example.bookcatalogservice.service;

import com.example.bookcatalogservice.dto.BookCatalogDto;
import com.example.bookcatalogservice.dto.ResponseDto;
import com.example.bookcatalogservice.dto.ServiceResponseDto;
import com.example.bookcatalogservice.entity.BookCatalog;
import com.example.bookcatalogservice.repo.BookCatalogRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookCatalogService {

    @Autowired
    private BookCatalogRepo bookCatalogRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServiceResponseDto serviceResponseDto;



    //check whether a book exists with the same title
    public Boolean checkABookExistsByTitle(String title){
        Integer bookCatalogId = bookCatalogRepo.findByTitle(title);
        if(bookCatalogId == null){
            return false; //a book is not existing
        }else {
            return true; // a book with same title existing
        }
    }

    //create new book
    public ServiceResponseDto createNewBookCatalog(BookCatalogDto catalog) {

        if(checkABookExistsByTitle(catalog.getTitle())){ // check a book is existing with same title
            //get that book with same title and show it is existing
            serviceResponseDto.setMessage("A Book is Existing with Same title");

            Integer bookCatalogId = bookCatalogRepo.findByTitle(catalog.getTitle());
            BookCatalog existingBook = bookCatalogRepo.findById(bookCatalogId).get();
            serviceResponseDto.setContent(existingBook);
            return serviceResponseDto;

        }else{
            if(bookCatalogRepo.existsById(catalog.getCatalogId())){//check for existing id
                //get that book with same id and show it is existing
                BookCatalog bookCatalog = bookCatalogRepo.findById(catalog.getCatalogId()).get();
                serviceResponseDto.setMessage("A Book is Existing with Same ID");
                serviceResponseDto.setContent(bookCatalog);
                return serviceResponseDto;
            }else{
                bookCatalogRepo.save(modelMapper.map(catalog, BookCatalog.class));//save book
                BookCatalog bookCatalog = bookCatalogRepo.findById(catalog.getCatalogId()).get();
                serviceResponseDto.setMessage("A New Book is Created");
                serviceResponseDto.setContent(bookCatalog);
                return serviceResponseDto;
            }
        }
    }

    public ServiceResponseDto getAllBookCatalogs() {
        List<BookCatalog> availableBookCatalogList = bookCatalogRepo.findAll();
        if(availableBookCatalogList.isEmpty()){
            serviceResponseDto.setContent(availableBookCatalogList);
            serviceResponseDto.setMessage("There are No Book Catalogs Available");
            return serviceResponseDto;
        }else{
            serviceResponseDto.setContent(availableBookCatalogList);
            serviceResponseDto.setMessage("These are are the available Book Catalogs");
            return serviceResponseDto;
        }
    }


    public ServiceResponseDto deleteCatalog(Integer catalogId){
        if(bookCatalogRepo.existsById(catalogId)){
            bookCatalogRepo.deleteById(catalogId);
            serviceResponseDto.setMessage("Catalog Deleted Successfully");
            serviceResponseDto.setContent(null);
            return serviceResponseDto;
        }else{
            serviceResponseDto.setMessage("Catalog with that ID does not exists");
            serviceResponseDto.setContent(null);
            return serviceResponseDto;
        }
    }

    public ServiceResponseDto updateCatalog(BookCatalogDto catalogDto) {
        if(bookCatalogRepo.existsById(catalogDto.getCatalogId())){
            bookCatalogRepo.save(modelMapper.map(catalogDto, BookCatalog.class));

            //give the updated value with response
            BookCatalog bookCatalog = bookCatalogRepo.findById(catalogDto.getCatalogId()).get();
            serviceResponseDto.setMessage("Catalog Updated");
            serviceResponseDto.setContent(bookCatalog);
            return serviceResponseDto;
        }else{
           serviceResponseDto.setMessage("Catalog with that ID does not exists");
           serviceResponseDto.setContent(null);
           return serviceResponseDto;
        }
    }


    //service for inventory-service
    public Boolean checkCatalogExists(Integer catalogId) {
        return bookCatalogRepo.existsById(catalogId);
    }
}
