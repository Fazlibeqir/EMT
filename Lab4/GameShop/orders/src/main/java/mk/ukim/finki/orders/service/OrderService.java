package mk.ukim.finki.orders.service;

import mk.ukim.finki.orders.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.orders.domain.exceptions.OrderItemIdNonExistException;
import mk.ukim.finki.orders.domain.model.Order;
import mk.ukim.finki.orders.domain.model.OrderId;
import mk.ukim.finki.orders.domain.model.OrderItemId;
import mk.ukim.finki.orders.service.froms.OrderForm;
import mk.ukim.finki.orders.service.froms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderId placeOrder(OrderForm orderForm);
    List<Order> findAll();
    Optional<Order> findById(OrderId id);
    void addItem(OrderId orderId, OrderItemForm orderItemForm)throws OrderIdNotExistException;
    void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNonExistException;

}
