package chechi.nino.bootcamp.controller;

import chechi.nino.bootcamp.dto.reservation_car.CarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationUpdateRequest;
import chechi.nino.bootcamp.entity.reservation.CarReservation;
import chechi.nino.bootcamp.service.CarReservationService;
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
@RequestMapping("/api/v1/car-reservations")
public class CarReservationController {

    private final CarReservationService carReservationService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CarReservationResponse>> findAllCarReservations () {

        List<CarReservationResponse> responses = carReservationService.getAllCarReservations();

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarReservationResponse> findCarReservationById (@PathVariable Integer id) {

        CarReservationResponse response = carReservationService.getCarReservationById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> bookCarReservation (@PathVariable Integer userId, @Valid @RequestBody CarReservationRequest request) {

        try {
            CarReservationResponse response = carReservationService.bookCarReservation(userId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Number of passengers exceeds car capacity");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarReservation (@PathVariable Integer id, @Valid @RequestBody CarReservationUpdateRequest request) {

        try {
            CarReservationResponse response = carReservationService.updateReservation(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Number of passengers exceeds car capacity");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarReservationById (@PathVariable Integer id) {

        carReservationService.deleteCarReservationById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<CarReservationResponse>> searchByUser (@RequestParam("userId") Integer userId) {

        List<CarReservationResponse> responses = carReservationService.searchByUser(userId);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }
    }

    @GetMapping(params = "carId")
    public ResponseEntity<List<CarReservationResponse>> searchByCar (@RequestParam("carId") Integer carId) {

        List<CarReservationResponse> responses = carReservationService.searchByCar(carId);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }
    }

    @GetMapping(params = "reservationDate")
    public ResponseEntity<List<CarReservationResponse>> searchByDate (@RequestParam("reservationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<CarReservationResponse> responses = carReservationService.searchByDate(date);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }
    }

}
