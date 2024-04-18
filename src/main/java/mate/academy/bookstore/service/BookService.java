package mate.academy.bookstore.service;

import mate.academy.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
}
