package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.reservation_room.RoomReservationPeriodUpdateRequest;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRequest;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRoomUpdateRequest;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;

import java.time.LocalDate;
import java.util.List;

public interface RoomReservationService {

    List<RoomReservationResponse> getAllReservations();

    RoomReservationResponse findReservationById (Integer id);

    RoomReservationResponse bookReservation (Integer userId, RoomReservationRequest request);

    RoomReservationResponse updateRoom (Integer id, RoomReservationRoomUpdateRequest request);

    RoomReservationResponse updatePeriod (Integer id, RoomReservationPeriodUpdateRequest request);

    void deleteReservationById (Integer id);

    List<RoomReservationResponse> searchByUser (Integer userId);

    List<RoomReservationResponse> searchByRoom (Integer roomId);

    List<RoomReservation> findReservationsWithinPeriod(LocalDate startDate, LocalDate endDate);
}
