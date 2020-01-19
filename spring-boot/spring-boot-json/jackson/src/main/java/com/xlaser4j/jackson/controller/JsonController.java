package com.xlaser4j.jackson.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xlaser4j.jackson.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.jackson.controller
 * @author: Elijah.D
 * @time: 2020/1/19 15:23
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class JsonController {
    /**
     * list books
     *
     * @return list
     */
    @GetMapping("/a")
    public List<Book> listBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter1", 20.0, new Date()));
        books.add(new Book("Harry Potter2", 25.0, new Date()));
        books.add(new Book("Harry Potter3", 30.0, new Date()));

        return books;
    }
}
