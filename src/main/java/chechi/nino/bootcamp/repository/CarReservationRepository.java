package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.dto.reservation_car.CarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import chechi.nino.bootcamp.entity.reservation.CarReservation;
import chechi.nino.bootcamp.entity.reservation.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CarReservationRepository extends JpaRepository<CarReservation, Integer> {

    @Query("SELECT r FROM CarReservation r JOIN r.user u WHERE u.id = :userId")
    List<CarReservationResponse> searchByUser (Integer userId);

    @Query("SELECT r FROM CarReservation r JOIN r.car t WHERE t.id = :carId")
    List<CarReservationResponse> searchByCar (Integer carId);

    @Query("SELECT r FROM CarReservation r WHERE r.reservationDate = :reservationDate")
    List<CarReservation> searchByDate (LocalDate reservationDate);

}
