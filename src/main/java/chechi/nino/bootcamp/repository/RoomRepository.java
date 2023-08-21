package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
