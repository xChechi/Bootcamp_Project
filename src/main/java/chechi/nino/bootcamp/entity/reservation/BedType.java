package chechi.nino.bootcamp.entity.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BedType {

    SINGLE_BEDS("Two single beds"),
    DOUBLE_BED("One double bed");

    private final String displayName;
}
