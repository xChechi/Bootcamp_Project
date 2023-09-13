package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.bar.ScreenEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenEventRepository extends JpaRepository<ScreenEvent, Integer> {
}
