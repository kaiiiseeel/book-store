package mate.academy.bookstore.dto.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mate.academy.bookstore.entity.Order.Status;

@Data
public class UpdateOrderRequestDto {
    @NotNull
    private Status status;
}
