package mate.academy.bookstore.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.entity.Book;
import mate.academy.bookstore.exception.DataProcessingException;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.repository.BookRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityManager entityManager;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add book to DB " + book, e);
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get books from DB", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(entityManager.find(Book.class, id));
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't find entity with such id: " + id, e);
        }
    }
}
