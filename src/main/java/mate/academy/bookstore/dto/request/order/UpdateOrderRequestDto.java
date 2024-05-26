package mate.academy.bookstore.dto.request.order;

import lombok.Data;
import mate.academy.bookstore.entity.Order.Status;
import mate.academy.bookstore.validation.EnumValidator;

@Data
public class UpdateOrderRequestDto {
    @EnumValidator(anyOf = {Status.PENDING, Status.DELIVERED, Status.COMPLETED})
    private Status status;
}
