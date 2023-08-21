package chechi.nino.bootcamp.entity.room;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum RoomType {

    STANDARD(2, "24m²", Arrays.asList(
            FacilityType.HAIR_DRYER,
            FacilityType.TV,
            FacilityType.WIFI,
            FacilityType.MINIBAR)),
    STUDIO(3, "34m²", Arrays.asList(
            FacilityType.HAIR_DRYER,
            FacilityType.COFFEE_MACHINE,
            FacilityType.TV,
            FacilityType.WIFI,
            FacilityType.MINIBAR,
            FacilityType.IRON
    )),
    APARTMENT(4, "56m²", Arrays.asList(
            FacilityType.HAIR_DRYER,
            FacilityType.COFFEE_MACHINE,
            FacilityType.TV,
            FacilityType.WIFI,
            FacilityType.MINIBAR,
            FacilityType.IRON,
            FacilityType.TERRACE,
            FacilityType.JACUZZI
    ));

    private final int roomCapacity;
    private final String roomSize;
    private final List<FacilityType> facilities;
}
