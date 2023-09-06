package chechi.nino.bootcamp.dto.reservation_car;

import chechi.nino.bootcamp.dto.car.CarResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.car.CarType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarReservationResponse {

    private Integer id;

    @NotNull
    private UserResponse user;

    @NotNull
    private CarResponse car;

    @NotNull
    private Integer passengers;

    private CarType carType;

    @NotBlank
    private String carModel;

    @Future
    private LocalDate reservationDate;

    @NotNull
    private Double dailyCharge;
}
