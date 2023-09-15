package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.reservation.BarReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarReservationRepository extends JpaRepository<BarReservation, Integer> {
}
