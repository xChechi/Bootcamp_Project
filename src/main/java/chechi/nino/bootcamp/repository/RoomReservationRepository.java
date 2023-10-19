package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {

    @Query("SELECT r FROM RoomReservation r JOIN r.user u WHERE u.id = :userId")
    List<RoomReservationResponse> searchByUser (Integer userId);

    @Query("SELECT r FROM RoomReservation r JOIN r.room c WHERE c.id = :roomId")
    List<RoomReservationResponse> searchByRoom (Integer roomId);

    @Query("SELECT r FROM RoomReservation r WHERE r.startDate <= :endDate AND r.endDate >= :startDate")
    List<RoomReservation> findReservationsWithinPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
