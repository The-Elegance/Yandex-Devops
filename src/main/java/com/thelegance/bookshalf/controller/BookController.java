package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestPart Book book, @RequestPart File image) {
        bookService.add(book, image);
    }

    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.update(id, book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/books/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }
}
