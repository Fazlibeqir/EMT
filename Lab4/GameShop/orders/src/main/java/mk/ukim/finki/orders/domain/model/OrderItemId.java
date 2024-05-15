package mk.ukim.finki.orders.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class OrderItemId extends DomainObjectId {
    private OrderItemId() {
        super(OrderItemId.randomId(OrderItemId.class).getId());
    }
    public OrderItemId(String uuid) {
        super(uuid);
    }
}
