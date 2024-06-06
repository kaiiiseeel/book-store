package mate.academy.bookstore.dto.request.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class BookRequestDto {
    @NotBlank(message = "Title of the book should not be empty")
    private String title;
    @NotBlank(message = "Author of the book should not be empty")
    private String author;
    @NotBlank(message = "ISBN of the book should not be empty")
    @ISBN(message = "ISBN can't be empty and must contain 13 digits")
    private String isbn;
    @Min(value = 0, message = "Price of the book should be more or equals 0")
    @NotNull(message = "Price of the book can't be null")
    private BigDecimal price;
    private String description;
    private String coverImage;
    @Size(min = 1)
    private Set<Long> categoryIds;
}
