package mk.ukim.finki.games.domain.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import mk.ukim.finki.games.domain.valueobjects.Stock;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.finacial.Money;

import java.util.Date;

@Entity
@Table(name = "games")
@Getter
public class Games extends AbstractEntity<GamesID> {
    private String gameTitle;
    private String genre;
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @jakarta.persistence.Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @jakarta.persistence.Column(name = "price_currency"))
    })
    private Money price;
    private int sales=0;
    private String developer;
    private Stock stock;
    private Date releaseDate;
    private Games(){
        super(GamesID.randomId(GamesID.class));
    }
    public static Games build(String gameTitle, String genre, Money price, String developer, Stock stock, Date releaseDate,int sales){
        Games game = new Games();
        game.gameTitle = gameTitle;
        game.genre = genre;
        game.price = price;
        game.developer = developer;
        game.stock = stock;
        game.releaseDate = releaseDate;
        game.sales = sales;
        return game;
    }
    public void addSales(int quantity){
        this.sales= this.sales-quantity;
    }
    public void removeSales(int quantity){
        this.sales+= quantity;
    }

}
