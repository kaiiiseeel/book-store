package mate.academy.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.CategoryRequestDto;
import mate.academy.bookstore.dto.CategoryResponseDto;
import mate.academy.bookstore.entity.Category;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.CategoryMapper;
import mate.academy.bookstore.repository.CategoryRepository;
import mate.academy.bookstore.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryMapper.toDto(
                categoryRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Can't find category with id: " + id))
        );
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toModel(categoryRequestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't update category with id: " + id);
        }
        Category category = categoryMapper.toModel(categoryRequestDto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't delete category with id: " + id);
        }
        categoryRepository.deleteById(id);

    }
}


