package mk.ukim.finki.orders.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.finacial.Currency;
import mk.ukim.finki.sharedkernel.domain.finacial.Money;

@Getter
public class Games implements ValueObject {
    private final GameID id;
    private final String gameTitle;
    private Money price;
    private final int sales;

    private Games() {
        this.id = GameID.randomId(GameID.class);
        this.gameTitle= "";
        this.price = Money.valueOf(Currency.MKD, 0);
        this.sales = 0;
    }

    @JsonCreator
    public Games(@JsonProperty("id") GameID id,
                 @JsonProperty("gameTitle")String gameTitle,
                 @JsonProperty("price")Money price,
                 @JsonProperty("sales")int sales) {
        this.id = id;
        this.gameTitle = gameTitle;
        this.price = price;
        this.sales = sales;
    }
}
