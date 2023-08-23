package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRequest;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.entity.reservation.BedType;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import chechi.nino.bootcamp.entity.room.Room;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.exception.RoomNotFoundException;
import chechi.nino.bootcamp.exception.UserNotFoundException;
import chechi.nino.bootcamp.repository.RoomRepository;
import chechi.nino.bootcamp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
@AllArgsConstructor
public class RoomReservationConverter {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserConverter userConverter;
    private final RoomConverter roomConverter;

    public RoomReservation bookReservation (Integer userId, RoomReservationRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new RoomNotFoundException("Room not found"));

        int requestedGuests = request.getGuests();
        int roomCapacity = room.getRoomType().getRoomCapacity();

        if (requestedGuests > roomCapacity) {
            throw new IllegalArgumentException("Number of guests exceeds room capacity");
        }

        return RoomReservation.builder()
                .user(user)
                .room(room)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .guests(request.getGuests())
                .bedType(request.getBedType())
                .totalCharge(calculateTotalCharge(request.getStartDate(), request.getEndDate(), room.getRoomPrice()))
                .build();
    }

    private Double calculateTotalCharge(LocalDate startDate, LocalDate endDate, Double roomPrice) {
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        return roomPrice * numOfDays;
    }

    public RoomReservationResponse toRoomReservationResponse (RoomReservation roomReservation) {

        return RoomReservationResponse.builder()
                .id(roomReservation.getId())
                .user(userConverter.toUserResponse(roomReservation.getUser()))
                .room(roomConverter.toRoomResponse(roomReservation.getRoom()))
                .startDate(roomReservation.getStartDate())
                .endDate(roomReservation.getEndDate())
                .guests(roomReservation.getGuests())
                .bedType(roomReservation.getBedType())
                .totalCharge(roomReservation.getTotalCharge())
                .build();
    }
}
