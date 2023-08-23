package chechi.nino.bootcamp.dto.reservation_room;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomReservationRoomUpdateRequest {

    @NotNull
    private Integer roomId;

}
