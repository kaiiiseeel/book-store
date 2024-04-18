package mate.academy.bookstore.repository;

import java.util.List;
import mate.academy.bookstore.entity.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
