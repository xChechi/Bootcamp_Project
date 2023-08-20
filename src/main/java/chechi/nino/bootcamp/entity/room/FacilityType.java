package chechi.nino.bootcamp.entity.room;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FacilityType {

    TV("TV"),
    WIFI("WI-FI"),
    COFFEE_MACHINE("Coffee Machine"),
    MINIBAR("Minibar"),
    HAIR_DRYER("Hair dryer"),
    RAIN_SHOWER("Rain shower"),
    IRON("Ironing service"),
    TERRACE("Terrace"),
    JACUZZI("Jacuzzi");

    private final String displayName;
}
