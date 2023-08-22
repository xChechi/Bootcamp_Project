package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {
}
