package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.reservation.BarReservation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarReservationRepository extends JpaRepository<BarReservation, Integer> {

    @EntityGraph(attributePaths = "barSeatList")
    Optional<BarReservation> findById(Integer id);

}
