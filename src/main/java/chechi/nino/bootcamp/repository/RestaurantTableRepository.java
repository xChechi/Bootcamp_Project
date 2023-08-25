package chechi.nino.bootcamp.repository;


import chechi.nino.bootcamp.entity.room.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Integer> {
}
