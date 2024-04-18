package mate.academy.bookstore.repository;

import mate.academy.bookstore.entity.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
}
