package chechi.nino.bootcamp.dto.table;

import chechi.nino.bootcamp.entity.restaurant.TableZone;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableResponse {

    private Integer id;

    @NotBlank
    private String tableNumber;

    private TableZone tableZone;

    private boolean isSmoke;

    @NotNull
    private int tableCapacity;
}
