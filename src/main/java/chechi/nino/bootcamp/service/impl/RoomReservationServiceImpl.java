package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.RoomReservationConverter;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationPeriodUpdateRequest;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRequest;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRoomUpdateRequest;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import chechi.nino.bootcamp.entity.room.Room;
import chechi.nino.bootcamp.exception.ReservationNotFoundException;
import chechi.nino.bootcamp.exception.RoomNotFoundException;
import chechi.nino.bootcamp.repository.RoomRepository;
import chechi.nino.bootcamp.repository.RoomReservationRepository;
import chechi.nino.bootcamp.service.RoomReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomReservationServiceImpl implements RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;
    private final RoomReservationConverter roomReservationConverter;
    private final RoomRepository roomRepository;

    @Override
    public List<RoomReservationResponse> getAllReservations() {

        List<RoomReservation> reservations = roomReservationRepository.findAll();
        List<RoomReservationResponse> reservationResponses = new ArrayList<>();

        for (RoomReservation r : reservations) {
            RoomReservationResponse response = roomReservationConverter.toRoomReservationResponse(r);
            reservationResponses.add(response);
        }

        return reservationResponses;
    }

    @Override
    public RoomReservationResponse findReservationById(Integer id) {

        RoomReservation roomReservation = roomReservationRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));

        return roomReservationConverter.toRoomReservationResponse(roomReservation);
    }

    @Override
    public RoomReservationResponse bookReservation(Integer userId, RoomReservationRequest request) {

        RoomReservation roomReservation = roomReservationConverter.bookReservation(userId, request);
        RoomReservation savedReservation = roomReservationRepository.save(roomReservation);

        return roomReservationConverter.toRoomReservationResponse(savedReservation);
    }

    @Override
    public RoomReservationResponse updateRoom(Integer id, RoomReservationRoomUpdateRequest request) {

        RoomReservation roomReservation = roomReservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new RoomNotFoundException("Room not found"));

        roomReservation.setRoom(room);

        Double newTotalCharge = roomReservationConverter.calculateTotalCharge(roomReservation.getStartDate(), roomReservation.getEndDate(), room.getRoomPrice());
        roomReservation.setTotalCharge(newTotalCharge);

        RoomReservation savedReservation = roomReservationRepository.save(roomReservation);

        return roomReservationConverter.toRoomReservationResponse(savedReservation);
    }

    @Override
    public RoomReservationResponse updatePeriod(Integer id, RoomReservationPeriodUpdateRequest request) {

        RoomReservation roomReservation = roomReservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        Room room = roomRepository.findById(roomReservation.getRoom().getId()).orElseThrow(() -> new RoomNotFoundException("Room not found"));

        Double newTotalCharge = roomReservationConverter.calculateTotalCharge(request.getStartDate(), request.getEndDate(), room.getRoomPrice());
        roomReservation.setTotalCharge(newTotalCharge);

        roomReservation.setStartDate(request.getStartDate());
        roomReservation.setEndDate(request.getEndDate());

        RoomReservation savedReservation = roomReservationRepository.save(roomReservation);

        return roomReservationConverter.toRoomReservationResponse(savedReservation);
    }

    @Override
    public void deleteReservationById(Integer id) {
        roomReservationRepository.deleteById(id);
    }

    @Override
    public List<RoomReservationResponse> searchByUser(Integer userId) {

        List<RoomReservation> reservations = roomReservationRepository.findAll();

        return reservations.stream()
                .filter(reservation -> {
                    Integer reservationUserId = reservation.getUser().getId();
                    return  reservationUserId != null && reservationUserId.equals(userId);
                })
                .map(roomReservationConverter::toRoomReservationResponse)
                .toList();
    }

    @Override
    public List<RoomReservationResponse> searchByRoom(Integer roomId) {

        List<RoomReservation> reservations = roomReservationRepository.findAll();

        return reservations.stream()
                .filter(reservation -> {
                    Integer reservationRoomId = reservation.getRoom().getId();
                    return  reservationRoomId != null && reservationRoomId.equals(roomId);
                })
                .map(roomReservationConverter::toRoomReservationResponse)
                .toList();
    }

    @Override
    public List<RoomReservation> findReservationsWithinPeriod(LocalDate startDate, LocalDate endDate) {

        return roomReservationRepository.findReservationsWithinPeriod(startDate, endDate);
    }
}
