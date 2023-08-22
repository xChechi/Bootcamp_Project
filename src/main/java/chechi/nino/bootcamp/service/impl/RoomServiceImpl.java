package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.RoomConverter;
import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.entity.room.Room;
import chechi.nino.bootcamp.exception.RoomNotFoundException;
import chechi.nino.bootcamp.repository.RoomRepository;
import chechi.nino.bootcamp.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomConverter roomConverter;

    @Override
    public List<RoomResponse> getAllRoomResponses() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(roomConverter::toRoomResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponse getRoomResponseById(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        return roomConverter.toRoomResponse(room);
    }

}
