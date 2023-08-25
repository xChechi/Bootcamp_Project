package chechi.nino.bootcamp.entity.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TableZone {

    TERRACE(true, 4),
    BAR(false, 1),
    SALOON_4(false, 4),
    SALOON_2(false, 2),
    SALOON_7(false, 7),
    SALOON_8(false, 8);

    private final boolean isSmoke;
    private final int tableCapacity;
}
