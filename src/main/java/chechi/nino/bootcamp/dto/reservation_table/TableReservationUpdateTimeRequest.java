package chechi.nino.bootcamp.dto.reservation_table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableReservationUpdateTimeRequest {

    private LocalDate reservationDate;

    private LocalTime reservationTime;

}
