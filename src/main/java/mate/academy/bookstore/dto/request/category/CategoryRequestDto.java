package mate.academy.bookstore.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank
    @Size(max = 70)
    private String name;
    @Size(max = 1000)
    private String description;
}
