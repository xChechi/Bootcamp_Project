package chechi.nino.bootcamp.controller;

import chechi.nino.bootcamp.dto.reservation_table.TableReservationRequest;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationUpdateTableRequest;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationUpdateTimeRequest;
import chechi.nino.bootcamp.entity.reservation.TableReservation;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import chechi.nino.bootcamp.exception.TableNotFoundException;
import chechi.nino.bootcamp.exception.TableReservationNotFoundException;
import chechi.nino.bootcamp.service.TableReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/table-reservations")
public class TableReservationController {

    private final TableReservationService tableReservationService;

    @GetMapping("/available-tables")
    public ResponseEntity<List<RestaurantTable>> getAvailableTablesWithClosestCapacity(@RequestParam("numberOfGuests") int numberOfGuests) {
        List<RestaurantTable> availableTables = tableReservationService.findAvailableTablesWithClosestCapacity(numberOfGuests);

        if (!availableTables.isEmpty()) {
            return ResponseEntity.ok(availableTables);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TableReservationResponse>> getAllReservations() {

        List<TableReservationResponse> reservations = tableReservationService.getAllReservations();

        return ResponseEntity.status(HttpStatus.FOUND).body(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableReservationResponse> findReservationById(@PathVariable Integer id) {

        TableReservationResponse response = tableReservationService.findReservationById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TableReservationResponse> bookReservation(@PathVariable Integer userId, @Valid @RequestBody TableReservationRequest request) {

        TableReservationResponse response = tableReservationService.bookReservation(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/update-table")
    public ResponseEntity<TableReservationResponse> updateTable(@PathVariable Integer id, @Valid @RequestBody TableReservationUpdateTableRequest request) {

        TableReservationResponse response = tableReservationService.updateTable(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/update-time")
    public ResponseEntity<TableReservationResponse> updateTime(@PathVariable Integer id, @Valid @RequestBody TableReservationUpdateTimeRequest request) {

        TableReservationResponse response = tableReservationService.updateTime(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableReservation(@PathVariable Integer id) {

        tableReservationService.deleteTableReservation(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<TableReservationResponse>> searchByUser(@RequestParam("userId") Integer userId) {

        List<TableReservationResponse> responses = tableReservationService.searchByUser(userId);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }
    }

    @GetMapping(params = "tableId")
    public ResponseEntity<List<TableReservationResponse>> searchByTable(@RequestParam("tableId") Integer tableId) {

        List<TableReservationResponse> responses = tableReservationService.searchByTable(tableId);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }
    }

    @GetMapping(params = "reservationDate")
    public ResponseEntity<List<TableReservationResponse>> searchByDate(
            @RequestParam("reservationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate) {

        List<TableReservationResponse> responses = tableReservationService.searchByDate(reservationDate);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }
    }
}
