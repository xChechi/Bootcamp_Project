package chechi.nino.bootcamp.dto.reservation_bar;

import chechi.nino.bootcamp.entity.bar.BarSeat;
import chechi.nino.bootcamp.entity.bar.ScreenEvent;
import chechi.nino.bootcamp.entity.reservation.PaymentStatus;
import chechi.nino.bootcamp.entity.reservation.PaymentType;
import jakarta.persistence.*;
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
public class BarReservationRequest {

    private List<BarSeat> barSeatList;

    private Integer eventId;

    @Future
    private LocalDateTime eventTime;

    @NotNull
    private Integer guests;

}
