package mate.academy.bookstore.mapper;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.request.book.BookRequestDto;
import mate.academy.bookstore.dto.response.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.response.book.BookResponseDto;
import mate.academy.bookstore.entity.Book;
import mate.academy.bookstore.entity.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookResponseDto toDto(Book book);

    @Mapping(target = "categories", ignore = true)
    Book toModel(BookRequestDto requestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategoryIds(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookResponseDto responseDto, Book book) {
        if (book.getCategories() != null) {
            Set<Long> categoryIds = book.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            responseDto.setCategoryIds(categoryIds);
        } else {
            responseDto.setCategoryIds(Collections.emptySet());
        }
    }
}
