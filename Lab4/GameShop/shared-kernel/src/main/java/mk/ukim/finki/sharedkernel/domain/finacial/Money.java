package mk.ukim.finki.sharedkernel.domain.finacial;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

import java.util.Objects;


@Embeddable
@Getter
public class Money implements ValueObject {
    @Enumerated(value = EnumType.STRING)
    private final Currency currency;
    private final double amount;

    protected Money() {
        this.currency = null;
        this.amount = 0.0;
    }
    public Money(@NonNull Currency currency, @NonNull double amount) {
        this.currency = currency;
        this.amount = amount;
    }
    public static Money zero(Currency currency) {
        return new Money(currency, 0);
    }
    public static Money valueOf(Currency currency, double amount) {
        return new Money(currency, amount);
    }
    public Money add(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency, amount + money.amount);
    }
    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot subtract two Money objects with different currencies");
        }
        return new Money(currency, amount - money.amount);
    }
    public Money multiply(int m) {
        return new Money(currency, amount * m);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Money money = (Money) obj;
        return Double.compare(money.amount, amount) == 0 && currency == money.currency;
    }
}
