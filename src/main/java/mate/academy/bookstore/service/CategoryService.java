package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.request.category.CategoryRequestDto;
import mate.academy.bookstore.dto.response.category.CategoryResponseDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto);

    void delete(Long id);
}
