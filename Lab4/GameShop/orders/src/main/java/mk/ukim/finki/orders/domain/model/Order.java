package mk.ukim.finki.orders.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.orders.domain.valueobjects.Games;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.finacial.Currency;
import mk.ukim.finki.sharedkernel.domain.finacial.Money;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId>{
   @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private Date orderDate;

    @Column(name = "order_currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems=new ArrayList<>();

    private Order() {
        super(OrderId.randomId(OrderId.class));
    }
    public Order(Date orderDate,@NonNull Currency currency){
        super(OrderId.randomId(OrderId.class));
        this.orderDate = orderDate;
        this.currency = currency;
        this.status = OrderStatus.PROCESSING;
    }
    public Money total() {
        Currency orderCurrency = currency;
        List<Money> totalWithSameCurrency = orderItems.stream()
                .map(OrderItem::subtotal)
                .filter(money -> money.getCurrency().equals(orderCurrency))
                .toList();
        if (totalWithSameCurrency.isEmpty()) {
            return Money.zero(orderCurrency);
        }
        return totalWithSameCurrency.stream()
                .reduce(Money::add)
                .orElse(Money.zero(orderCurrency));
    }
    public OrderItem addItem(@NonNull Games games, int quantity) {
        Objects.requireNonNull(games, "games must not be null");
        var item = new OrderItem(games.getId(),games.getPrice(), quantity);
        orderItems.add(item);
        return item;
    }
    public void removeItem(@NonNull OrderItemId orderItemId) {
        Objects.requireNonNull(orderItemId, "orderItemId must not be null");
        var item = orderItems.stream()
                .filter(orderItem -> orderItem.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        orderItems.remove(item);
    }
}
