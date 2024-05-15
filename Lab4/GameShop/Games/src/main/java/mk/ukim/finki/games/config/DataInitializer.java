package mk.ukim.finki.games.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.games.domain.models.Games;
import mk.ukim.finki.games.domain.repository.GamesRepository;
import mk.ukim.finki.sharedkernel.domain.finacial.Currency;
import mk.ukim.finki.sharedkernel.domain.finacial.Money;
import mk.ukim.finki.games.domain.valueobjects.Stock;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final GamesRepository gamesRepository;

    @PostConstruct
    public void initData(){
        if(gamesRepository.findAll().isEmpty()){
            gamesRepository.saveAndFlush(Games.build("Cyberpunk 2077","RPG", Money.valueOf(Currency.EUR,5000), "CD Projekt Red", Stock.valueOf(10), new Date(), 0));
            gamesRepository.saveAndFlush(Games.build("The Witcher 3","RPG", Money.valueOf(Currency.MKD,3000), "CD Projekt Red", Stock.valueOf(20), new Date(), 0));
            gamesRepository.saveAndFlush(Games.build("GTA V","Action", Money.valueOf(Currency.USD,4000), "Rockstar Games", Stock.valueOf(15), new Date(), 0));
            gamesRepository.saveAndFlush(Games.build("Red Dead Redemption 2","Action", Money.valueOf(Currency.EUR,4500), "Rockstar Games", Stock.valueOf(25), new Date(), 0));
            gamesRepository.saveAndFlush(Games.build("FIFA 21","Sport", Money.valueOf(Currency.MKD,2000), "EA Sports", Stock.valueOf(30), new Date(), 0));
            gamesRepository.saveAndFlush(Games.build("NBA 2K21","Sport", Money.valueOf(Currency.EUR,2500), "2K Sports", Stock.valueOf(35), new Date(), 0));
        }

    }
}
