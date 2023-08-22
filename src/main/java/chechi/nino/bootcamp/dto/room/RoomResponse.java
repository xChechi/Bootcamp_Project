package chechi.nino.bootcamp.dto.room;

import chechi.nino.bootcamp.entity.room.FacilityType;
import chechi.nino.bootcamp.entity.room.RoomType;
import chechi.nino.bootcamp.entity.room.RoomView;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoomResponse {

    private Integer id;

    private String roomNumber;

    private RoomType roomType;

    private RoomView roomView;

    private Double roomPrice;

    private String roomSize;

    private Integer roomCapacity;

    private List<FacilityType> facilities;
}
