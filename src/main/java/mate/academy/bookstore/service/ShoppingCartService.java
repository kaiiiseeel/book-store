package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.request.cartitem.CartItemRequestDto;
import mate.academy.bookstore.dto.request.cartitem.UpdateCartItemRequestDto;
import mate.academy.bookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.entity.ShoppingCart;
import mate.academy.bookstore.entity.User;

public interface ShoppingCartService {

    void createShoppingCart(User user);

    ShoppingCartResponseDto getShoppingCartDto(Long userId);

    ShoppingCart getShoppingCart(Long userId);

    ShoppingCartResponseDto addItem(Long id, CartItemRequestDto requestDto);

    ShoppingCartResponseDto updateItem(
            Long cartId,
            Long itemId,
            UpdateCartItemRequestDto requestDto
    );

    void deleteItem(Long userId, Long id);
}
