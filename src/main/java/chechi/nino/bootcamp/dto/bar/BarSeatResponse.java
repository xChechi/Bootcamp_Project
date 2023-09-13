package chechi.nino.bootcamp.dto.bar;

import chechi.nino.bootcamp.entity.bar.ZoneType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BarSeatResponse {

    private Integer id;

    @NotNull
    private Integer seatNumber;

    private ZoneType zoneType;
}
