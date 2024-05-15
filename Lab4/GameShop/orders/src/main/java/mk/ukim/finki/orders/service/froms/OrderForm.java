package mk.ukim.finki.orders.service.froms;

import lombok.Data;
import mk.ukim.finki.sharedkernel.domain.finacial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {
    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<OrderItemForm> itemFormList = new ArrayList<>();
}
