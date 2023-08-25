package chechi.nino.bootcamp.dto.reservation_table;

import chechi.nino.bootcamp.dto.table.TableResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.restaurant.TableZone;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantTableReservationResponse {

    private Integer id;

    @NotNull
    private UserResponse userResponse;

    @NotNull
    private TableResponse tableResponse;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private TableZone tableZone;

    private boolean isSmoke;

    @NotNull
    private Integer guests;
}
