package mate.academy.bookstore.mapper;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.request.category.CategoryRequestDto;
import mate.academy.bookstore.dto.response.category.CategoryResponseDto;
import mate.academy.bookstore.entity.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    Category toModel(CategoryRequestDto categoryRequestDto);
}
