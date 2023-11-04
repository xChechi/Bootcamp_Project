package chechi.nino.bootcamp.dto.reservation_table;

import chechi.nino.bootcamp.dto.table.TableResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.restaurant.TableZone;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableReservationResponse {

    private Integer id;

    @NotNull
    private UserResponse user;

    @NotNull
    private TableResponse table;

    @NotNull
    private LocalDate reservationDate;

    @NotNull
    private LocalTime reservationTime;

    private TableZone tableZone;

    private boolean isSmoke;

    @NotNull
    private Integer guests;

    @NotNull
    private Double totalCharge;
}
