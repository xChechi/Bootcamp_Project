package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.user.UserRequest;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.user.Role;
import chechi.nino.bootcamp.entity.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserConverter {

    private final PasswordEncoder passwordEncoder;

    public User createUser (UserRequest request) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword())) // <-------- Don`t forget to encrypt when security is implemented
                .role(Role.USER)
                .build();
    }

    public UserResponse toUserResponse (User user) {
        /* In case reservations are not added automatically
        List<RoomReservationResponse> roomReservations = user.getRoomReservationList().stream()
                .map(roomReservationConverter::toRoomReservationResponse)
                .collect(Collectors.toList());

         */

        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }
}
