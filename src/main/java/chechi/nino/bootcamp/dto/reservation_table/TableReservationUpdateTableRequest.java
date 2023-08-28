package chechi.nino.bootcamp.dto.reservation_table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableReservationUpdateTableRequest {

    @NotNull
    private Integer tableId;

}
