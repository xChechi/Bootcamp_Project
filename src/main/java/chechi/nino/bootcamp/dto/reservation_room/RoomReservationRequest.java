package chechi.nino.bootcamp.dto.reservation_room;

import chechi.nino.bootcamp.entity.reservation.BedType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoomReservationRequest {

    private Integer roomId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Integer guests;

    //@NotNull
    //private Double totalCharge;

    private BedType bedType;

}
