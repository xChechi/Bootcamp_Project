package chechi.nino.bootcamp.controller;

import chechi.nino.bootcamp.entity.room.RestaurantTable;
import chechi.nino.bootcamp.service.TableReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
