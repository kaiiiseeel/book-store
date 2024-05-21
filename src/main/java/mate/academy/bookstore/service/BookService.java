package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.request.book.BookRequestDto;
import mate.academy.bookstore.dto.response.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.response.book.BookResponseDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(BookRequestDto requestDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto getById(Long id);

    BookResponseDto update(Long id, BookRequestDto requestDto);

    void delete(Long id);

    List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId, Pageable pageable);
}
