package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
