package chechi.nino.bootcamp.dto.reservation_room;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomReservationPeriodUpdateRequest {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
