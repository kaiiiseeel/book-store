package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.request.order.CreateOrderRequestDto;
import mate.academy.bookstore.dto.request.order.UpdateOrderRequestDto;
import mate.academy.bookstore.dto.response.order.OrderItemResponseDto;
import mate.academy.bookstore.dto.response.order.OrderResponseDto;
import mate.academy.bookstore.entity.User;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    List<OrderResponseDto> getAllOrders(Long userId, Pageable pageable);

    OrderResponseDto placeOrder(CreateOrderRequestDto requestDto, User user);

    List<OrderItemResponseDto> getOrderItems(Long userId, Long orderId, Pageable pageable);

    OrderItemResponseDto getOrderItem(Long userId, Long orderId, Long itemId);

    OrderResponseDto updateOrderStatus(Long orderId, UpdateOrderRequestDto requestDto);
}
