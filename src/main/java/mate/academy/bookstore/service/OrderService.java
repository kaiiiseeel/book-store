package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.request.order.CreateOrderRequestDto;
import mate.academy.bookstore.dto.request.order.UpdateOrderRequestDto;
import mate.academy.bookstore.dto.response.order.OrderItemResponseDto;
import mate.academy.bookstore.dto.response.order.OrderResponseDto;
import mate.academy.bookstore.entity.User;

public interface OrderService {
    List<OrderResponseDto> getAllOrders(Long userId);

    OrderResponseDto placeOrder(CreateOrderRequestDto requestDto, User user);

    List<OrderItemResponseDto> getOrderItems(Long orderId);

    OrderItemResponseDto getOrderItem(Long orderId, Long itemId);

    OrderResponseDto updateOrderStatus(Long orderId, UpdateOrderRequestDto requestDto);
}
