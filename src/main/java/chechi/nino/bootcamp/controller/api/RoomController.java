package chechi.nino.bootcamp.controller.api;

import chechi.nino.bootcamp.converter.RoomConverter;
import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.entity.room.Room;
import chechi.nino.bootcamp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity.status(HttpStatus.FOUND).body(roomService.getAllRoomResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(roomService.getRoomResponseById(id));
    }
}
