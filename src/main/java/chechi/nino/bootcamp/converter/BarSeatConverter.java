package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.bar.BarSeatResponse;
import chechi.nino.bootcamp.entity.bar.BarSeat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class BarSeatConverter {

    public BarSeatResponse toBarSeatResponse (BarSeat barSeat) {

        return BarSeatResponse.builder()
                .id(barSeat.getId())
                .seatNumber(barSeat.getSeatNumber())
                .zoneType(barSeat.getZoneType())
                .build();
    }

}
