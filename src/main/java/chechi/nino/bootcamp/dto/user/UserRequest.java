package chechi.nino.bootcamp.dto.user;

import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import chechi.nino.bootcamp.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Role role;

    private String password;

    private List<RoomReservation> roomReservationList;
}
