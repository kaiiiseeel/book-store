package mate.academy.bookstore.repository;

import java.util.List;
import mate.academy.bookstore.entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId, Pageable pageable);
}
