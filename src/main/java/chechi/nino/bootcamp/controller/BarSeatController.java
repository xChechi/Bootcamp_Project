package chechi.nino.bootcamp.controller;

import chechi.nino.bootcamp.dto.bar.BarSeatResponse;
import chechi.nino.bootcamp.service.BarSeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bar-seats")
public class BarSeatController {

    private final BarSeatService barSeatService;

    @GetMapping
    public ResponseEntity<List<BarSeatResponse>> findAllBarSeats () {

        List<BarSeatResponse> responses = barSeatService.getAllBarSeats();

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<BarSeatResponse> findBarSeatById (@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.FOUND).body(barSeatService.getBarSeatById(id));
    }

}
