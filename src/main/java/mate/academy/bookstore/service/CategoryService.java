package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.CategoryRequestDto;
import mate.academy.bookstore.dto.CategoryResponseDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto);

    void delete(Long id);
}
