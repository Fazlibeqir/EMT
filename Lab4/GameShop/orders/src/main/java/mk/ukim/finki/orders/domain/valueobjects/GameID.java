package mk.ukim.finki.orders.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class GameID extends DomainObjectId {
    private GameID() {
        super(GameID.randomId(GameID.class).getId());
    }
    public GameID(String uuid) {
        super(uuid);
    }
}
