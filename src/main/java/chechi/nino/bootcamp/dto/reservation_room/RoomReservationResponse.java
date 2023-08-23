package chechi.nino.bootcamp.dto.reservation_room;

import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.reservation.BedType;
import chechi.nino.bootcamp.entity.room.Room;
import chechi.nino.bootcamp.entity.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoomReservationResponse {

    private Integer id;

    @NotNull
    private UserResponse user;

    @NotNull
    private RoomResponse room;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Integer guests;

    @NotNull
    private Double totalCharge;

    private BedType bedType;
}
