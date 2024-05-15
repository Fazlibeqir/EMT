package mk.ukim.finki.games.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.games.domain.models.Games;
import mk.ukim.finki.games.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@AllArgsConstructor
public class GameResource {
    private final GameService gameService;
    @GetMapping
    public List<Games> getGamesAll(){
        return gameService.listAllGames();
    }
}
