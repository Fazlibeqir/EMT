package mk.ukim.finki.games.domain.repository;

import mk.ukim.finki.games.domain.models.Games;
import mk.ukim.finki.games.domain.models.GamesID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games, GamesID>{
}
