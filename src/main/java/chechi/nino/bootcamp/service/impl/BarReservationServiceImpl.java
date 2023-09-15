package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.BarReservationConverter;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.entity.reservation.BarReservation;
import chechi.nino.bootcamp.exception.ReservationNotFoundException;
import chechi.nino.bootcamp.repository.BarReservationRepository;
import chechi.nino.bootcamp.repository.BarSeatRepository;
import chechi.nino.bootcamp.service.BarReservationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BarReservationServiceImpl implements BarReservationService {

    private final BarReservationRepository barReservationRepository;
    private final BarReservationConverter barReservationConverter;

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
}
