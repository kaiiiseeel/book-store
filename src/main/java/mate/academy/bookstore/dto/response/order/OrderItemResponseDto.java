package mate.academy.bookstore.dto.response.order;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long orderId;
    private Long bookId;
    private int quantity;
}
