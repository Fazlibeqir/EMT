package mk.ukim.finki.sharedkernel.domain.events.orders;

import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

public class OrderItemDeleted extends DomainEvent {
    String gameId;
    int quantity;
    public OrderItemDeleted(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_DELETED);
    }
    public OrderItemDeleted(String topic, String gameId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_DELETED);
        this.gameId = gameId;
        this.quantity = quantity;
    }
}
