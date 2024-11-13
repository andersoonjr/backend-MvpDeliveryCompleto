package mvp_delivery_system.repositories;

import mvp_delivery_system.entites.Order;
import mvp_delivery_system.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderstatus(OrderStatus orderStatus);
}