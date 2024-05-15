package mk.ukim.finki.games.service;

import mk.ukim.finki.games.domain.models.Games;
import mk.ukim.finki.games.domain.models.GamesID;
import mk.ukim.finki.games.service.forms.GamesForm;

import java.util.List;

public interface GameService {
    Games findById(GamesID id);
    Games createGame(GamesForm gamesForm);
    Games orderItemCreated(GamesID gamesID, int quantity);
    Games orderItemDeleted(GamesID gamesID, int quantity);
    List<Games> listAllGames();

}
