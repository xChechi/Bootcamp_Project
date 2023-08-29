package chechi.nino.bootcamp.entity.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CarType {

    SPORT(2, 1000),
    SEDAN(5, 800),
    VAN(8, 600);

    private final int seats;
    private final double dailyCharge;
}
