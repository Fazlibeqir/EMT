package mk.ukim.finki.orders.domain.repository;

import mk.ukim.finki.orders.domain.model.Order;
import mk.ukim.finki.orders.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {

}
