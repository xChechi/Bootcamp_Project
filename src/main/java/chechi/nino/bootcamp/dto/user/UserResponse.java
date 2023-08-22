package chechi.nino.bootcamp.dto.user;

import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;



}
