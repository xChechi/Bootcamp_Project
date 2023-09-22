package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.reservation_bar.BarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.entity.reservation.BarReservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BarReservationService {

    List<BarReservationResponse> getAllBarReservations ();

    BarReservationResponse getBarReservationById (Integer id);

    BarReservationResponse bookBarReservation (Integer userId, BarReservationRequest request);

    void DeleteBarReservationById (Integer id);

    BarReservationResponse updateBarReservation (Integer id, BarReservationRequest request);

    List<BarReservationResponse> searchByUser (Integer userId);

    List<BarReservationResponse> searchBySeat (Integer seatId);

    List<BarReservationResponse> searchByDate (LocalDate date);
}
