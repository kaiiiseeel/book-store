package mate.academy.bookstore.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.request.order.CreateOrderRequestDto;
import mate.academy.bookstore.dto.request.order.UpdateOrderRequestDto;
import mate.academy.bookstore.dto.response.order.OrderItemResponseDto;
import mate.academy.bookstore.dto.response.order.OrderResponseDto;
import mate.academy.bookstore.entity.Order;
import mate.academy.bookstore.entity.OrderItem;
import mate.academy.bookstore.entity.ShoppingCart;
import mate.academy.bookstore.entity.User;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.OrderItemMapper;
import mate.academy.bookstore.mapper.OrderMapper;
import mate.academy.bookstore.repository.CartItemRepository;
import mate.academy.bookstore.repository.OrderItemRepository;
import mate.academy.bookstore.repository.OrderRepository;
import mate.academy.bookstore.service.OrderService;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<OrderResponseDto> getAllOrders(Long userId, Pageable pageable) {
        return orderRepository.findByUserId(userId, pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto placeOrder(CreateOrderRequestDto requestDto, User user) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user.getId());

        Order order = createOrder(user, requestDto);
        Set<OrderItem> orderItems = getOrderItemsFromShoppingCart(shoppingCart, order);

        order.setOrderItems(orderItems);
        order.setTotal(calculateTotal(orderItems));
        cartItemRepository.deleteAll(shoppingCart.getCartItems());

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderItemResponseDto> getOrderItems(Long userId, Long orderId, Pageable pageable) {
        isOrderExists(userId, orderId);

        return orderItemRepository.findByOrderId(orderId, pageable).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getOrderItem(Long userId, Long orderId, Long itemId) {
        Order order = getOrder(userId, orderId);

        OrderItem orderItem = order.getOrderItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find order item with id: " + itemId
                                + " in order: " + orderId)
                );
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, UpdateOrderRequestDto requestDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order with id: " + orderId)
        );

        order.setStatus(requestDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    private Order getOrder(Long userId, Long orderId) {
        return orderRepository.findByUserIdAndId(userId, orderId).orElseThrow(
                () -> new EntityNotFoundException(
                        "Can't find order with id: " + orderId + " for user: " + userId)
        );
    }

    private void isOrderExists(Long userId, Long orderId) {
        Order order = getOrder(userId, orderId);
        if (order.getUser().getId().equals(userId)) {
            throw new EntityNotFoundException(
                    "Can't find order with id: " + orderId + " for user: " + userId);
        }
    }

    private Set<OrderItem> getOrderItemsFromShoppingCart(ShoppingCart shoppingCart, Order order) {
        return shoppingCart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setBook(cartItem.getBook());
                    orderItem.setPrice(cartItem.getBook().getPrice());
                    return orderItem;
                })
                .collect(Collectors.toSet());
    }

    private Order createOrder(User user, CreateOrderRequestDto requestDto) {
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(requestDto.getShippingAddress());
        return order;
    }

    private BigDecimal calculateTotal(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
