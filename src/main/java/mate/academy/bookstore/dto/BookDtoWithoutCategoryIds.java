package mate.academy.bookstore.dto;

import java.math.BigDecimal;

public record BookDtoWithoutCategoryIds(
         String title,
         String author,
         String isbn,
         BigDecimal price,
         String description,
         String coverImage
) {
}
