package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.reservation.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarReservationRepository extends JpaRepository<CarReservation, Integer> {
}
