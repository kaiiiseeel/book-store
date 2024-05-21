package mate.academy.bookstore.mapper;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.request.cartitem.CartItemRequestDto;
import mate.academy.bookstore.dto.response.cartitem.CartItemResponseDto;
import mate.academy.bookstore.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    CartItem toModel(CartItemRequestDto requestDto);

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    CartItemResponseDto toDto(CartItem cartItem);
}
