package mk.ukim.finki.games.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

@Embeddable
@Getter
public class Stock implements ValueObject {
    private final int stock;
    protected Stock(){
        this.stock = 0;
    }
    public Stock(int stock) {
        this.stock = stock;
    }
    public static Stock valueOf( int stock) {

            return new Stock(stock);

    }
}
