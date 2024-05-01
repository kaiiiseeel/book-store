package mate.academy.bookstore.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class BookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    @ISBN
    private String isbn;
    @Min(value = 0)
    @NotNull
    private BigDecimal price;
    private String description;
    private String coverImage;
}
