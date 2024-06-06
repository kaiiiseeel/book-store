package mate.academy.bookstore.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.request.cartitem.CartItemRequestDto;
import mate.academy.bookstore.dto.request.cartitem.UpdateCartItemRequestDto;
import mate.academy.bookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.entity.Book;
import mate.academy.bookstore.entity.CartItem;
import mate.academy.bookstore.entity.ShoppingCart;
import mate.academy.bookstore.entity.User;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.CartItemMapper;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.repository.BookRepository;
import mate.academy.bookstore.repository.CartItemRepository;
import mate.academy.bookstore.repository.ShoppingCartRepository;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto getShoppingCart(Long userId) {
        return shoppingCartMapper.toDto(
                shoppingCartRepository.findByUserId(userId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Can't find shopping cart for user with id:" + userId)
                        )
        );
    }

    @Override
    @Transactional
    public ShoppingCartResponseDto addItem(Long id, CartItemRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find shopping cart with id: " + id)
                );
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find book with id: " + requestDto.getBookId())
                );

        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setBook(book);
        cartItemRepository.save(cartItem);
        shoppingCart.getCartItems().add(cartItem);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCartResponseDto updateItem(
            Long cartId,
            Long itemId,
            UpdateCartItemRequestDto requestDto
    ) {
        CartItem cartItem = cartItemRepository.findCartItemByIdAndShoppingCartId(itemId, cartId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find item with id: " + itemId)
                );
        cartItem.setQuantity(requestDto.getQuantity());
        cartItemRepository.save(cartItem);
        ShoppingCart shoppingCart = cartItem.getShoppingCart();
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteItem(Long userId, Long id) {
        CartItem cartItem = cartItemRepository.findCartItemByIdAndShoppingCartId(id, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find item with id: " + id)
                );
        cartItemRepository.delete(cartItem);
    }
}
