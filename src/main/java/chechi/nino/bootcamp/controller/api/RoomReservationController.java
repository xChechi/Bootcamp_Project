package chechi.nino.bootcamp.controller.api;

import chechi.nino.bootcamp.dto.reservation_room.RoomReservationPeriodUpdateRequest;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRequest;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRoomUpdateRequest;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import chechi.nino.bootcamp.service.RoomReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservations")
public class RoomReservationController {

    private final RoomReservationService roomReservationService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoomReservationResponse>> getAllReservations() {

        List<RoomReservationResponse> responses = roomReservationService.getAllReservations();

        return ResponseEntity.status(HttpStatus.FOUND).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomReservationResponse> findReservationById (@PathVariable Integer id) {

        RoomReservationResponse response = roomReservationService.findReservationById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<RoomReservationResponse> bookReservation (@PathVariable Integer userId, @Valid @RequestBody RoomReservationRequest request) {

        RoomReservationResponse response = roomReservationService.bookReservation(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/update-room")
    public ResponseEntity<RoomReservationResponse> updateRoom (@PathVariable Integer id, @Valid @RequestBody RoomReservationRoomUpdateRequest request) {

        RoomReservationResponse response = roomReservationService.updateRoom(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/update-period")
    public ResponseEntity<RoomReservationResponse> updatePeriod (@PathVariable Integer id, @Valid @RequestBody RoomReservationPeriodUpdateRequest request) {

        RoomReservationResponse response = roomReservationService.updatePeriod(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById (@PathVariable Integer id) {

        roomReservationService.deleteReservationById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(params = "userId")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoomReservationResponse>> searchByUser (@RequestParam("userId") Integer userId) {

        List<RoomReservationResponse> responseList = roomReservationService.searchByUser(userId);

        if (responseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responseList);
        }
    }

    @GetMapping(params = "roomId")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoomReservationResponse>> searchByRoom (@RequestParam("roomId") Integer roomId) {

        List<RoomReservationResponse> responseList = roomReservationService.searchByRoom(roomId);

        if (responseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responseList);
        }
    }

    @GetMapping("/period")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoomReservationResponse>> findReservationsWithinPeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate) {

        List<RoomReservationResponse> responseList = roomReservationService.findReservationsWithinPeriod(startDate, endDate);

        if (responseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responseList);
        }
    }

}
