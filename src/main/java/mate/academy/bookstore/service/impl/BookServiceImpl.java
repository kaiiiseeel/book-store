package mate.academy.bookstore.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.BookRequestDto;
import mate.academy.bookstore.dto.BookResponseDto;
import mate.academy.bookstore.entity.Book;
import mate.academy.bookstore.entity.Category;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.repository.BookRepository;
import mate.academy.bookstore.repository.CategoryRepository;
import mate.academy.bookstore.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public BookResponseDto save(BookRequestDto requestDto) {
        Set<Long> categoryIds = requestDto.getCategoryIds();
        List<Category> categories = validateCategories(categoryIds);
        Book book = bookMapper.toModel(requestDto);
        book.setCategories(new HashSet<>(categories));
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookResponseDto getById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(
                    () -> new EntityNotFoundException("Can't find book with such id: " + id)));
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto requestDto) {
        if (!isBookExists(id)) {
            throw new EntityNotFoundException("Can't update book with such id: " + id);
        }
        Book bookModel = bookMapper.toModel(requestDto);
        bookModel.setId(id);
        return bookMapper.toDto(bookRepository.save(bookModel));
    }

    @Override
    public void delete(Long id) {
        if (!isBookExists(id)) {
            throw new EntityNotFoundException("Can't delete book with such id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findAllByCategoryId(categoryId, pageable).stream()
                .map(bookMapper::toDtoWithoutCategoryIds)
                .toList();
    }

    private boolean isBookExists(Long id) {
        return bookRepository.existsById(id);
    }

    private List<Category> validateCategories(Set<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);

        if (categoryIds.size() != categories.size()) {
            Set<Long> availableCategories = categories.stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            categoryIds.removeAll(availableCategories);
            throw new EntityNotFoundException("Category id not found: " + categoryIds);
        }
        return categories;
    }
}
