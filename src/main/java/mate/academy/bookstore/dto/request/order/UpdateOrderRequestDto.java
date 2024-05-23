package mate.academy.bookstore.dto.request.order;

import lombok.Data;
import mate.academy.bookstore.entity.Order.Status;

@Data
public class UpdateOrderRequestDto {
    private Status status;
}
