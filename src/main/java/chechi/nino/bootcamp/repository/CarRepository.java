package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
