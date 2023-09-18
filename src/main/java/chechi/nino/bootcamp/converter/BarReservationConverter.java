package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.bar.BarSeatResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        ScreenEvent event = screenEventRepository.findById(request.getEventId()).orElseThrow(() -> new EventNotFoundException("Event not found"));

        List<BarSeat> barSeats = fetchBarSeats(request.getBarSeatList());
        //List<BarSeat> barSeats = new ArrayList<>(request.getBarSeatList());
/*
        List<BarSeat> barSeats = new ArrayList<>();

        for (BarSeat barSeat : request.getBarSeatList()) {
            BarSeat fetchedBarSeat = barSeatRepository.findById(barSeat.getId())
                    .orElseThrow(() -> new BarSeatNotFoundException("Bar seat not found: " + barSeat.getId()));
            barSeats.add(fetchedBarSeat);
        }
        System.out.println(barSeats);
*/
        return BarReservation.builder()
                .user(user)
                .barSeatList(barSeats)
                .screenEvent(event)
                .eventTime(request.getEventTime())
                .guests(request.getGuests())
                .totalCharge(calculateBarCharge(request.getGuests()))
                .build();


    }

    private List<BarSeat> fetchBarSeats(List<BarSeat> barSeatList) {
        List<BarSeat> barSeats = new ArrayList<>();

        for (BarSeat barSeat : barSeatList) {
            BarSeat fetchedBarSeat = barSeatRepository.findById(barSeat.getId())
                    .orElseThrow(() -> new BarSeatNotFoundException("Bar seat not found: " + barSeat.getId()));
            barSeats.add(fetchedBarSeat);
        }

        return barSeats;
    }

    public Double calculateBarCharge(Integer guests) {
        return guests * 5.00;
    }

    public BarReservationResponse toBarReservationResponse (BarReservation res) {

        List<BarSeatResponse> seats = res.getBarSeatList()
                .stream()
                .map(barSeatConverter::toBarSeatResponse)
                .collect(Collectors.toList());

        return BarReservationResponse.builder()
                .id(res.getId())
                .user(userConverter.toUserResponse(res.getUser()))
                .event(screenEventConverter.toEventResponse(res.getScreenEvent()))
                .seats(seats)
                .eventTime(res.getEventTime())
                .guests(res.getGuests())
                .totalCharge(calculateBarCharge(res.getGuests()))
                .build();
    }
}
