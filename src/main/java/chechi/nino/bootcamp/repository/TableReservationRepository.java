package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.reservation.TableReservation;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableReservationRepository extends JpaRepository<TableReservation, Integer> {

    //@Query("SELECT t FROM RestaurantTable t WHERE t.tableCapacity >= :numberOfGuests")
    //List<RestaurantTable> findAvailableTablesWithCapacity(@Param("numberOfGuests") int numberOfGuests);
}
