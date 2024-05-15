package mk.ukim.finki.games.service.forms;

import lombok.Data;
import mk.ukim.finki.games.domain.valueobjects.Stock;
import mk.ukim.finki.sharedkernel.domain.finacial.Money;

import java.util.Date;

@Data
public class GamesForm {


    private String gameTitle;
    private Money price;
    private int sales;
    private String developer;
    private String genre;
    private Date releaseDate;
    private Stock stock;

}
