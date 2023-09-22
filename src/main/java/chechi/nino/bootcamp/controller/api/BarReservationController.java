package chechi.nino.bootcamp.controller.api;

import chechi.nino.bootcamp.dto.reservation_bar.BarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.service.BarReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bar-reservations")
public class BarReservationController {

    private final BarReservationService barReservationService;

    @GetMapping
    public ResponseEntity<List<BarReservationResponse>> findAllBarReservations () {

        return ResponseEntity.status(HttpStatus.FOUND).body(barReservationService.getAllBarReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarReservationResponse> findBarReservationById (@PathVariable Integer id) {

        CacheControl cacheControl = CacheControl.noStore();
        CacheControl cacheControl2 = CacheControl.maxAge(1, TimeUnit.HOURS).mustRevalidate();
        CacheControl cacheControl3 = CacheControl.noCache().mustRevalidate();

        return ResponseEntity.status(HttpStatus.FOUND).cacheControl(cacheControl3).body(barReservationService.getBarReservationById(id));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<BarReservationResponse> bookBarReservation (@PathVariable Integer userId, @Valid @RequestBody BarReservationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(barReservationService.bookBarReservation(userId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarReservationById (@PathVariable Integer id) {

        barReservationService.DeleteBarReservationById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarReservationResponse> updateBarReservation (@PathVariable Integer id, @Valid @RequestBody BarReservationRequest request) {

        BarReservationResponse response = barReservationService.updateBarReservation(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<BarReservationResponse>> searchBarReservationByUser (@RequestParam("userId") Integer userId) {

        List<BarReservationResponse> responses = barReservationService.searchByUser(userId);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else return ResponseEntity.status(HttpStatus.FOUND).body(responses);
    }

    @GetMapping(params = "seatId")
    public ResponseEntity<List<BarReservationResponse>> searchBarReservationBySeat (@RequestParam("seatId") Integer seatId) {

        List<BarReservationResponse> responses = barReservationService.searchBySeat(seatId);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else return ResponseEntity.status(HttpStatus.FOUND).body(responses);
    }

    @GetMapping(params = "date")
    public ResponseEntity<List<BarReservationResponse>> searchBarReservationByDate (
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<BarReservationResponse> responses = barReservationService.searchByDate(date);

        if (responses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.status(HttpStatus.FOUND).body(responses);
    }
}
