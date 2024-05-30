package com.thelegance.bookshalf.service;

import com.thelegance.bookshalf.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Book getById(Long id);

    void add(Book book, MultipartFile image);

    void update(Long id, Book book);

    void delete(Long id);
}
