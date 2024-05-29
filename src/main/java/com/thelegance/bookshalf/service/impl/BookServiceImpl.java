package com.thelegance.bookshalf.service.impl;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.cognitoidentity.model.Credentials;
import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.repository.BookRepository;
import com.thelegance.bookshalf.service.BookService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository repository;

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getById(Long id) {
        var book = repository.findById(id);
        return book.orElse(null);
    }

    @Override
    public void add(Book book, File image) {
        String accessKey = "YCAJEdITYar2HY6rLuBR7JKAj";
        String secretKey = "YCP7IrwNROM25DcGRI_PXIqlmYyASAfS3-IeedMD";
        String bucketName = "new-baket-book";
        String region = "ru-central1";

        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3 s3Client = new AmazonS3Client(credentials);
        s3Client.setEndpoint("storage.yandexcloud.net");

        String key = book.getCoverFileName(); // Имя файла в Object Storage
        File file = image;
        //File file = new File("C:\\Users\\adara\\IdeaProjects\\Yandex-Devops\\src\\main\\resources\\test.jpg"); // Путь к вашему изображению

        PutObjectRequest request = new PutObjectRequest(bucketName, key, file);
        s3Client.putObject(request);
        repository.save(book);
    }

    @Override
    public void update(Long id, Book book) {
        book.setId(id);
        repository.save(book);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
