package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.reservation_bar.BarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.entity.bar.BarSeat;
import chechi.nino.bootcamp.entity.bar.ScreenEvent;
import chechi.nino.bootcamp.entity.reservation.BarReservation;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.exception.BarSeatNotFoundException;
import chechi.nino.bootcamp.exception.EventNotFoundException;
import chechi.nino.bootcamp.exception.UserNotFoundException;
import chechi.nino.bootcamp.repository.BarSeatRepository;
import chechi.nino.bootcamp.repository.ScreenEventRepository;
import chechi.nino.bootcamp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BarReservationConverter {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BarSeatRepository barSeatRepository;
    private final BarSeatConverter barSeatConverter;
    private final ScreenEventRepository screenEventRepository;
    private final ScreenEventConverter screenEventConverter;

    public BarReservation bookBarReservation (Integer userId, BarReservationRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        //BarSeat seat = barSeatRepository.findById(request.getSeatId()).orElseThrow(() -> new BarSeatNotFoundException("Seat not found"));
        ScreenEvent event = screenEventRepository.findById(request.getEventId()).orElseThrow(() -> new EventNotFoundException("Event not found"));

        return BarReservation.builder()
                .user(user)
                .barSeatList(request.getBarSeatList())
                .screenEvent(event)
                .eventTime(request.getEventTime())
                .guests(request.getGuests())
                .totalCharge(calculateBarCharge(request.getGuests()))
                .build();
    }

    public Double calculateBarCharge(Integer guests) {
        return guests * 5.00;
    }

    public BarReservationResponse toBarReservationResponse (BarReservation res) {

        return BarReservationResponse.builder()
                .id(res.getId())
                .user(userConverter.toUserResponse(res.getUser()))
                .event(screenEventConverter.toEventResponse(res.getScreenEvent()))
                .barSeatList(res.getBarSeatList())
                .eventTime(res.getEventTime())
                .guests(res.getGuests())
                .totalCharge(calculateBarCharge(res.getGuests()))
                .build();
    }
}
