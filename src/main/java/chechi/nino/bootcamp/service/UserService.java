package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.user.UserRequest;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.dto.user.UserUpdatePasswordRequest;
import chechi.nino.bootcamp.dto.user.UserUpdateRequest;
import chechi.nino.bootcamp.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers ();

    UserResponse findUserById (Integer id);

    boolean existByEmail(String email);

    void deleteUserById (Integer id);

    UserResponse createUser (UserRequest request);

    UserResponse updateUser (Integer id, UserUpdateRequest request);

    UserResponse updateUserPassword (Integer id, UserUpdatePasswordRequest request);

    void resetPasswordByEmail(String email);

    //UserResponse findUserByEmail (String email);

   // UserResponse findByEmail (String email);

}
