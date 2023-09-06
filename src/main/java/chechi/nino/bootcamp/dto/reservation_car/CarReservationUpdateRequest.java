package chechi.nino.bootcamp.dto.reservation_car;

import chechi.nino.bootcamp.entity.car.CarType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarReservationUpdateRequest {

    private Integer carId;

    @NotNull
    private Integer passengers;

    private CarType carType;

    @NotBlank
    private String carModel;

    @Future
    private LocalDate reservationDate;

}
