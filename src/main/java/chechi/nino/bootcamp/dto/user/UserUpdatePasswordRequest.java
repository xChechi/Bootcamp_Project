package chechi.nino.bootcamp.dto.user;

import chechi.nino.bootcamp.annotation.PasswordValidation;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdatePasswordRequest {

    @PasswordValidation
    private String password;
}
