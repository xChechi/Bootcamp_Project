package chechi.nino.bootcamp.dto.user;

import chechi.nino.bootcamp.annotation.PasswordValidation;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import chechi.nino.bootcamp.entity.user.Role;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Role role;

    @PasswordValidation
    private String password;

    private List<RoomReservation> roomReservationList;
}
