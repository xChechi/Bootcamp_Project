package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.reservation.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableReservationRepository extends JpaRepository<TableReservation, Integer> {
}
