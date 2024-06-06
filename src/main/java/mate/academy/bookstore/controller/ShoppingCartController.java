package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.request.cartitem.CartItemRequestDto;
import mate.academy.bookstore.dto.request.cartitem.UpdateCartItemRequestDto;
import mate.academy.bookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.entity.User;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
@Tag(name = "Shopping cart management", description = "Endpoints to manage user's shopping cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping("/api/cart")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get shopping cart with items",
            description = "Returns shopping cart of authenticated user with all items in it")
    public ShoppingCartResponseDto findShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getShoppingCart(user.getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cart")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Add book to shopping cart",
            description = "Add book to shopping cart")
    public ShoppingCartResponseDto addItem(
            @RequestBody @Valid CartItemRequestDto requestDto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addItem(user.getId(), requestDto);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Update item in shopping cart",
            description = "Update the quantity of items in shopping cart by item's id")
    public ShoppingCartResponseDto updateItemQuantity(
            Authentication authentication,
            @PathVariable Long cartItemId,
            @RequestBody @Valid UpdateCartItemRequestDto requestDto
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateItem(user.getId(), cartItemId, requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Delete item from shopping cart",
            description = "Delete item from shopping cart by item's id")
    public void deleteItem(Authentication auth, @PathVariable Long cartItemId) {
        User user = (User) auth.getPrincipal();
        shoppingCartService.deleteItem(user.getId(), cartItemId);
    }
}
