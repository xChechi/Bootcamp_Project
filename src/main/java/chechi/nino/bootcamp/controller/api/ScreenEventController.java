package chechi.nino.bootcamp.controller.api;

import chechi.nino.bootcamp.dto.event.ScreenEventRequest;
import chechi.nino.bootcamp.dto.event.ScreenEventResponse;
import chechi.nino.bootcamp.entity.bar.ScreenEvent;
import chechi.nino.bootcamp.service.ScreenEventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/screen-events")
public class ScreenEventController {

    private final ScreenEventService screenEventService;

    @GetMapping
    public ResponseEntity<List<ScreenEventResponse>> findAllEvents () {

        List<ScreenEventResponse> responses = screenEventService.getAllEvents();

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenEventResponse> findEventById (@PathVariable Integer id) {

        ScreenEventResponse response = screenEventService.getEventById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PostMapping
    public ResponseEntity<ScreenEventResponse> createEvent (@Valid @RequestBody ScreenEventRequest request) {

        ScreenEventResponse response = screenEventService.createEvent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScreenEventResponse> updateEvent (@PathVariable Integer id, @Valid @RequestBody ScreenEventRequest request) {

        ScreenEventResponse event = screenEventService.updateEvent(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById (@PathVariable Integer id) {

        screenEventService.deleteEventById(id);

        return ResponseEntity.noContent().build();
    }
}
