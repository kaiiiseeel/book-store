package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.request.category.CategoryRequestDto;
import mate.academy.bookstore.dto.response.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.response.category.CategoryResponseDto;
import mate.academy.bookstore.service.BookService;
import mate.academy.bookstore.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book's categories", description = "Endpoints for managing categories")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all categories",
            description = "Returns the list of all categories with pagination")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CategoryResponseDto> getAllCategories(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id",
            description = "Returns a specific category by its id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CategoryResponseDto getCategory(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Get books of a specific category",
            description = "Returns a list of books of a specific category by category id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<BookDtoWithoutCategoryIds> getBooksByCategory(
            @PathVariable Long id, Pageable pageable
    ) {
        return bookService.findAllByCategoryId(id, pageable);
    }

    @PostMapping
    @Operation(summary = "Create new category",
            description = "Saves new category to the database, returns saved category")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponseDto createCategory(
            @RequestBody @Valid CategoryRequestDto categoryRequestDto
    ) {
        return categoryService.save(categoryRequestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category",
            description = "Updates a specific category by its id,"
                    + " returns the old version of category")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponseDto updateCategory(
            @PathVariable Long id, @RequestBody @Valid CategoryRequestDto categoryRequestDto
    ) {
        return categoryService.update(id, categoryRequestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category",
            description = "Delete specific category by its id")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}

