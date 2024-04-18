package mate.academy.bookstore.repository.impl;

import mate.academy.bookstore.entity.Book;
import mate.academy.bookstore.repository.BookRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }
}
