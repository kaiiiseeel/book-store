package mate.academy.bookstore.dto.request.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotBlank
    @Size(max = 255)
    private String shippingAddress;
}
