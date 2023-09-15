package chechi.nino.bootcamp.dto.reservation_bar;

import chechi.nino.bootcamp.dto.event.ScreenEventResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.bar.BarSeat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BarReservationResponse {

    private Integer id;

    private UserResponse user;

    private ScreenEventResponse event;

    private List<BarSeat> barSeatList;

    @Future
    private LocalDateTime eventTime;

    @NotNull
    private Integer guests;

    @NotNull
    private Double totalCharge;
}
