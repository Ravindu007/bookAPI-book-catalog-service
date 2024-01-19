package com.example.bookcatalogservice.controller;

import com.example.bookcatalogservice.dto.BookCatalogDto;
import com.example.bookcatalogservice.dto.ResponseDto;
import com.example.bookcatalogservice.dto.ServiceResponseDto;
import com.example.bookcatalogservice.entity.BookCatalog;
import com.example.bookcatalogservice.service.BookCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookCatalog")
public class BookCatalogController {

    @Autowired
    private BookCatalogService bookCatalogService;

    @Autowired
    private ResponseDto responseDto;

    @Autowired
    private ServiceResponseDto serviceResponseDto;

    //create a book catalog
    @PostMapping(value = "/createCatalog")
    public ResponseEntity<ResponseDto> createCatalog(@RequestBody BookCatalogDto catalog){
        try{
            ServiceResponseDto res =  bookCatalogService.createNewBookCatalog(catalog);
            responseDto.setMessage(res.getMessage());
            responseDto.setContent(res.getContent());
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch (Exception ex){
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllAvailableBookCatalogs")
    public ResponseEntity<ResponseDto> getAllAvailableBookCatalogs(){
        try{
            ServiceResponseDto res = bookCatalogService.getAllBookCatalogs();
            responseDto.setMessage(res.getMessage());
            responseDto.setContent(res.getContent());
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch (Exception ex){
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteCatalog/{catalogId}")
    public ResponseEntity<ResponseDto> deleteCatalog(@PathVariable Integer catalogId){
        try{
            ServiceResponseDto res = bookCatalogService.deleteCatalog(catalogId);
            responseDto.setMessage(res.getMessage());
            responseDto.setContent(res.getContent());
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch (Exception ex){
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/updateCatalog")
    public ResponseEntity<ResponseDto> updateCatalog(@RequestBody BookCatalogDto catalogDto){
        try{
           ServiceResponseDto res =  bookCatalogService.updateCatalog(catalogDto);
           responseDto.setMessage(res.getMessage());
           responseDto.setContent(res.getContent());
           return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }catch (Exception ex){
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }


    //needed by the inventory-service
    @GetMapping(value = "/getSingleCatalog/{catalogId}")
    public Boolean checkCatalogIdExists(@PathVariable Integer catalogId){
        return bookCatalogService.checkCatalogExists(catalogId);
    }

}
