package mate.academy.bookstore.dto.response.shoppingcart;

import java.util.Set;
import lombok.Data;
import mate.academy.bookstore.dto.response.cartitem.CartItemResponseDto;

@Data
public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    private Set<CartItemResponseDto> cartItems;
}
