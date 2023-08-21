package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.user.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers ();

    UserResponse findUserById (Integer id);

    boolean existByEmail(String email);


}
