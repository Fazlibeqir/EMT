package mk.ukim.finki.orders.service.froms;

import lombok.Data;
import mk.ukim.finki.orders.domain.valueobjects.Games;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemForm {
    @NotNull
    private Games games;

    @Min(1)
    private int quantity=1;
}
