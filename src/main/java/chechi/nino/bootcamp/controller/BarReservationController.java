package chechi.nino.bootcamp.controller;

import chechi.nino.bootcamp.dto.reservation_bar.BarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.service.BarReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
