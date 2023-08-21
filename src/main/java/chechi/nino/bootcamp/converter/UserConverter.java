package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.user.UserRequest;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.user.Role;
import chechi.nino.bootcamp.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User createUser (UserRequest request) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .role(Role.USER)
                .build();
    }

    public UserResponse toUserResponse (User user) {

        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }
}
