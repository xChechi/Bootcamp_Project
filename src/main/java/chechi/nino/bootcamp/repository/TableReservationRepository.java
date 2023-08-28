package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import chechi.nino.bootcamp.entity.reservation.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TableReservationRepository extends JpaRepository<TableReservation, Integer> {

    //@Query("SELECT t FROM RestaurantTable t WHERE t.tableCapacity >= :numberOfGuests")
    //List<RestaurantTable> findAvailableTablesWithCapacity(@Param("numberOfGuests") int numberOfGuests);

    @Query("SELECT r FROM TableReservation r JOIN r.user u WHERE u.id = :userId")
    List<TableReservationResponse> searchByUser (Integer userId);

    @Query("SELECT r FROM TableReservation r JOIN r.table t WHERE t.id = :tableId")
    List<TableReservationResponse> searchByTable (Integer tableId);

    @Query("SELECT r FROM TableReservation r WHERE r.reservationDate = :reservationDate")
    List<TableReservation> searchByDate (LocalDate reservationDate);
}
