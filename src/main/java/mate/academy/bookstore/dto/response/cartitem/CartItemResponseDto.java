package mate.academy.bookstore.dto.response.cartitem;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private int quantity;
}
