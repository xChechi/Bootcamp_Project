package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.car.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
