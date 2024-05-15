package mk.ukim.finki.orders.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.orders.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.orders.domain.exceptions.OrderItemIdNonExistException;
import mk.ukim.finki.orders.domain.model.Order;
import mk.ukim.finki.orders.domain.model.OrderId;
import mk.ukim.finki.orders.domain.model.OrderItemId;
import mk.ukim.finki.orders.domain.repository.OrderRepository;
import mk.ukim.finki.orders.service.OrderService;
import mk.ukim.finki.orders.service.froms.OrderForm;
import mk.ukim.finki.orders.service.froms.OrderItemForm;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final Validator validator;


    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "order form must not be null");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        return newOrder.getId();
    }
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderItemForm.getGames(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);

    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNonExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }
    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Date.from(Instant.now()),orderForm.getCurrency());
        orderForm.getItemFormList().forEach(itemForm -> order.addItem(itemForm.getGames(), itemForm.getQuantity()));
        return order;
    }
}
