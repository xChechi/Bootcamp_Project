package chechi.nino.bootcamp.dto.reservation_table;

import chechi.nino.bootcamp.entity.restaurant.TableZone;
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
public class RestaurantTableReservationRequest {

    private Integer tableId;

    @Future
    @NotNull
    private LocalDate startDate;

    @Future
    @NotNull
    private LocalDate endDate;

    private TableZone tableZone;

    private boolean isSmoke;

    @NotNull
    private Integer guests;
}
