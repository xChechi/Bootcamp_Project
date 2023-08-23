package chechi.nino.bootcamp.dto.room;

import chechi.nino.bootcamp.entity.room.FacilityType;
import chechi.nino.bootcamp.entity.room.RoomType;
import chechi.nino.bootcamp.entity.room.RoomView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoomResponse {

    @NotNull
    private Integer id;

    @NotBlank
    private String roomNumber;


    private RoomType roomType;

    private RoomView roomView;

    @NotNull
    private Double roomPrice;

    @NotBlank
    private String roomSize;

    @NotNull
    private Integer roomCapacity;

    @NotNull
    private List<FacilityType> facilities;
}
