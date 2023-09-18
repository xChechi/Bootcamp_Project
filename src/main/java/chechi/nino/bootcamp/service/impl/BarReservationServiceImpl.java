package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.BarReservationConverter;
import chechi.nino.bootcamp.converter.BarSeatConverter;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.entity.bar.ScreenEvent;
import chechi.nino.bootcamp.entity.reservation.BarReservation;
import chechi.nino.bootcamp.exception.EventNotFoundException;
import chechi.nino.bootcamp.exception.ReservationNotFoundException;
import chechi.nino.bootcamp.repository.BarReservationRepository;
import chechi.nino.bootcamp.repository.BarSeatRepository;
import chechi.nino.bootcamp.repository.ScreenEventRepository;
import chechi.nino.bootcamp.service.BarReservationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BarReservationServiceImpl implements BarReservationService {

    private final BarReservationRepository barReservationRepository;
    private final BarReservationConverter barReservationConverter;
    private final ScreenEventRepository screenEventRepository;
    private final BarSeatConverter barSeatConverter;

    @Override
    public List<BarReservationResponse> getAllBarReservations() {

        List<BarReservation> reservations = barReservationRepository.findAll();
        List<BarReservationResponse> responses = new ArrayList<>();

        for (BarReservation r : reservations) {
            BarReservationResponse response = barReservationConverter.toBarReservationResponse(r);
            responses.add(response);
        }

        return responses;
    }

    @Override
    public BarReservationResponse getBarReservationById(Integer id) {

        BarReservation barReservation = barReservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        return barReservationConverter.toBarReservationResponse(barReservation);
    }

    @Override
    //@Transactional
    public BarReservationResponse bookBarReservation(Integer userId, BarReservationRequest request) {

        BarReservation barReservation = barReservationConverter.bookBarReservation(userId, request);
        BarReservation savedReservation = barReservationRepository.save(barReservation);

        return barReservationConverter.toBarReservationResponse(savedReservation);
    }

    @Override
    public void DeleteBarReservationById(Integer id) {
        barReservationRepository.deleteById(id);
    }

    @Override
    public BarReservationResponse updateBarReservation(Integer id, BarReservationRequest request) {

        BarReservation barReservation = barReservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        ScreenEvent event = screenEventRepository.findById(request.getEventId()).orElseThrow(() -> new EventNotFoundException("Event not found"));

        barReservation.setBarSeatList(request.getBarSeatList());
        barReservation.setScreenEvent(event);
        barReservation.setEventTime(request.getEventTime());
        barReservation.setGuests(request.getGuests());

        //Validation logic here... too lazy to do it now

        BarReservation savedReservation = barReservationRepository.save(barReservation);

        return barReservationConverter.toBarReservationResponse(savedReservation);
    }

    @Override
    public List<BarReservationResponse> searchByUser(Integer userId) {

        List<BarReservation> reservations = barReservationRepository.findAll();

        return reservations.stream()
                .filter(res -> {
                    Integer reservationUserId = res.getUser().getId();
                    return reservationUserId !=null && reservationUserId.equals(userId);
                })
                .map(barReservationConverter::toBarReservationResponse)
                .toList();
    }

    @Override
    public List<BarReservationResponse> searchBySeat(Integer seatId) {

        List<BarReservation> reservations = barReservationRepository.findAll();

        return reservations.stream()
                .filter(res -> res.getBarSeatList().stream()
                        .anyMatch(seat -> seat.getId() != null && seat.getId().equals(seatId)))
                .map(barReservationConverter::toBarReservationResponse).toList();
    }

    @Override
    public List<BarReservationResponse> searchByDate(LocalDateTime date) {

        List<BarReservation> reservations = barReservationRepository.findAll();

        return reservations.stream()
                .filter(res -> res.getEventTime().isEqual(date))
                .map(barReservationConverter::toBarReservationResponse)
                .toList();
    }
}
