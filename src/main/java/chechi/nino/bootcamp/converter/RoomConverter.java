package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.entity.room.Room;
import chechi.nino.bootcamp.entity.room.RoomType;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter {

    public RoomResponse toRoomResponse (Room room) {
        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setRoomNumber(room.getRoomNumber());
        response.setRoomType(room.getRoomType());
        response.setRoomView(room.getRoomView());
        response.setRoomPrice(room.getRoomPrice());

        RoomType roomType = room.getRoomType();
        response.setRoomSize(roomType.getRoomSize());
        response.setRoomCapacity(roomType.getRoomCapacity());
        response.setFacilities(roomType.getFacilities());

        return response;
    }
}
