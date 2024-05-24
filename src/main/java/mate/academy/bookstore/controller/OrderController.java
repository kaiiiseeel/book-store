package mate.academy.bookstore.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.request.order.CreateOrderRequestDto;
import mate.academy.bookstore.dto.request.order.UpdateOrderRequestDto;
import mate.academy.bookstore.dto.response.order.OrderItemResponseDto;
import mate.academy.bookstore.dto.response.order.OrderResponseDto;
import mate.academy.bookstore.entity.User;
import mate.academy.bookstore.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<OrderResponseDto> getOrders(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return orderService.getAllOrders(user.getId());
    }

    @GetMapping("/{orderId}/items")
    @PreAuthorize("hasRole('USER')")
    public List<OrderItemResponseDto> getOrderItems(@PathVariable Long orderId) {
        return orderService.getOrderItems(orderId);
    }

    @GetMapping("/{orderId}/items/{id}")
    @PreAuthorize("hasRole('USER')")
    public OrderItemResponseDto getOrderItemFromOrder(
            @PathVariable Long orderId,
            @PathVariable Long id
    ) {
        return orderService.getOrderItem(orderId, id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public OrderResponseDto createOrder(
            Authentication auth,
            @RequestBody @Valid CreateOrderRequestDto requestDto
    ) {
        User user = (User) auth.getPrincipal();
        return orderService.placeOrder(requestDto, user);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponseDto updateOrderStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateOrderRequestDto requestDto
    ) {
        return orderService.updateOrderStatus(id, requestDto);
    }

}
