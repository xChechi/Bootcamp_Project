package chechi.nino.bootcamp.dto.user;

import chechi.nino.bootcamp.annotation.PasswordValidation;
import chechi.nino.bootcamp.annotation.PhoneNumberValidation;
import chechi.nino.bootcamp.annotation.ValidReservationList;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import chechi.nino.bootcamp.entity.reservation.TableReservation;
import chechi.nino.bootcamp.entity.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @PhoneNumberValidation
    private String phoneNumber;

    private Role role;

    @PasswordValidation
    private String password;

    //private List<RoomReservation> roomReservationList;

    //@ValidReservationList
    //private List<TableReservation> tableReservationList;
}
