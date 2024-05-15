package mk.ukim.finki.orders.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.orders.domain.valueobjects.GameID;
import mk.ukim.finki.orders.domain.valueobjects.Games;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.finacial.Money;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {
   @Column(name = "quantity", nullable = false)
    private int quantity;
    private Money itemPrice;
    @AttributeOverride(name = "id", column = @Column(name = "game_id", nullable = false))
    private GameID gameId;


    private OrderItem() {
        super(OrderItemId.randomId(OrderItemId.class));
    }
    public OrderItem(@NonNull GameID gameId,@NonNull Money itemPrice,  int quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.gameId = gameId;
    }

    public Money subtotal() {
        return itemPrice.multiply(quantity);
    }

}
