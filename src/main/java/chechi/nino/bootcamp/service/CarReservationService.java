package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.reservation_car.CarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationUpdateRequest;

import java.time.LocalDate;
import java.util.List;

public interface CarReservationService {

    List<CarReservationResponse> getAllCarReservations ();

    CarReservationResponse getCarReservationById (Integer id);

    CarReservationResponse bookCarReservation (Integer userId, CarReservationRequest request);

    CarReservationResponse updateReservation (Integer id, CarReservationUpdateRequest request);

    void deleteCarReservationById (Integer id);

    List<CarReservationResponse> searchByUser (Integer userId);

    List<CarReservationResponse> searchByCar (Integer carId);

    List<CarReservationResponse> searchByDate (LocalDate reservationDate);
}
