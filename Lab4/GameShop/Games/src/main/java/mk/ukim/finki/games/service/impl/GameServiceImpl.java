package mk.ukim.finki.games.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.games.domain.models.Games;
import mk.ukim.finki.games.domain.models.GamesID;
import mk.ukim.finki.games.domain.repository.GamesRepository;
import mk.ukim.finki.games.service.GameService;
import mk.ukim.finki.games.service.forms.GamesForm;
import org.springframework.stereotype.Service;
import mk.ukim.finki.games.domain.exceptions.GameNotFoundException;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private final GamesRepository gamesRepository;
    @Override
    public Games findById(GamesID id) {
        return gamesRepository.findById(id).orElseThrow(GameNotFoundException::new);
    }

    @Override
    public Games createGame(GamesForm gamesForm) {
        Games game = Games.build(gamesForm.getGameTitle(),gamesForm.getGenre(), gamesForm.getPrice(),gamesForm.getDeveloper(),gamesForm.getStock(),gamesForm.getReleaseDate(), gamesForm.getSales());
        gamesRepository.save(game);
        return game;
    }

    @Override
    public Games orderItemCreated(GamesID gamesID, int quantity) {
        Games game = findById(gamesID);
        game.addSales(quantity);
        gamesRepository.saveAndFlush(game);
        return game;
    }

    @Override
    public Games orderItemDeleted(GamesID gamesID, int quantity) {
        Games game = findById(gamesID);
        game.removeSales(quantity);
        gamesRepository.saveAndFlush(game);
        return game;
    }

    @Override
    public List<Games> listAllGames() {
        return gamesRepository.findAll();
    }
}
