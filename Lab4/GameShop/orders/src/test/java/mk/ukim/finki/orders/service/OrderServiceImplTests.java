package mk.ukim.finki.orders.service;

import mk.ukim.finki.orders.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.orders.domain.model.Order;
import mk.ukim.finki.orders.domain.model.OrderId;
import mk.ukim.finki.orders.domain.valueobjects.GameID;
import mk.ukim.finki.orders.domain.valueobjects.Games;
import mk.ukim.finki.orders.service.froms.OrderForm;
import mk.ukim.finki.orders.service.froms.OrderItemForm;
import mk.ukim.finki.orders.xport.client.GameClient;
import mk.ukim.finki.sharedkernel.domain.finacial.Currency;
import mk.ukim.finki.sharedkernel.domain.finacial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class OrderServiceImplTests {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GameClient gameClient;

    private static Games newGame(String name, Money price) {
        return new Games(GameID.randomId(GameID.class), name, price, 0);
    }

    @Test
    public void testPlaceOrder() {
        OrderItemForm orderItemForm1 = new OrderItemForm();
        orderItemForm1.setGames(newGame("SUperrrMan", Money.valueOf(Currency.MKD, 1000)));
        orderItemForm1.setQuantity(1);
        OrderItemForm orderItemForm2 = new OrderItemForm();
        orderItemForm2.setGames(newGame("IronMan", Money.valueOf(Currency.MKD, 500)));
        orderItemForm2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItemFormList(List.of(orderItemForm1, orderItemForm2));

        OrderId orderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.total().getAmount(), Money.valueOf(Currency.MKD, 2000).getAmount());

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Games> gamesList = gameClient.findAll();
        Games p1 = gamesList.get(0);
        Games p2 = gamesList.get(1);

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setGames(p1);
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setGames(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItemFormList(Arrays.asList(oi1, oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Currency currency = newOrder.getCurrency();

        List<Money> gamesWithSameCurrency = newOrder.getOrderItems().stream()
                .filter(item -> gamesList.stream()
                        .anyMatch(game->game.getId().equals(item.getGameId()) && game.getPrice().getCurrency().name().equals(currency.name())))
                .map(item -> item.getItemPrice().multiply(item.getQuantity()))
                .collect(Collectors.toList());

        Money totalWithSameCurrency = Money.zero(currency);

        for(Money money : gamesWithSameCurrency) {
            totalWithSameCurrency = totalWithSameCurrency.add(money);
        }


        Assertions.assertEquals(totalWithSameCurrency.getCurrency(),newOrder.total().getCurrency());
        Assertions.assertEquals(totalWithSameCurrency.getAmount(),newOrder.total().getAmount());
    }


}
