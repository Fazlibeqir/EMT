package mk.ukim.finki.games.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.games.domain.models.GamesID;
import mk.ukim.finki.games.service.GameService;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.events.orders.OrderItemCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GamesEventListener {
    private final GameService gameService;

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "games")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            OrderItemCreated event= DomainEvent.fromJson(jsonMessage, OrderItemCreated.class);
            gameService.orderItemCreated(GamesID.of(event.getGameId()), event.getQuantity());
        }catch (Exception e){
        }
    }
    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_DELETED, groupId = "games")
    public void consumeOrderItemDeletedEvent(String jsonMessage) {
        try {
            OrderItemCreated event= DomainEvent.fromJson(jsonMessage, OrderItemCreated.class);
            gameService.orderItemDeleted(GamesID.of(event.getGameId()), event.getQuantity());
        }catch (Exception e){
        }
    }
}
