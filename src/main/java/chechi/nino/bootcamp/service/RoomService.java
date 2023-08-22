package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.entity.room.Room;

import java.util.List;

public interface RoomService {

    List<RoomResponse> getAllRoomResponses();

    RoomResponse getRoomResponseById(Integer id);
}
