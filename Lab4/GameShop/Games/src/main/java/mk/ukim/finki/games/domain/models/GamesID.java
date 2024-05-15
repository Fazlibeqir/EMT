package mk.ukim.finki.games.domain.models;


import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class GamesID extends DomainObjectId {
    private GamesID() {
        super(GamesID.randomId(GamesID.class).getId());
    }

    public GamesID(@NonNull String uuid) {
        super(uuid);
    }
    public static GamesID of(String uuid) {
        GamesID g = new GamesID(uuid);
        return g;
    }

}
