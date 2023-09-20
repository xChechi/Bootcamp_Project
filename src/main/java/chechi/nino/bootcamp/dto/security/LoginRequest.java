package chechi.nino.bootcamp.dto.security;

import chechi.nino.bootcamp.annotation.PasswordValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {

    @NotBlank
    @Email
    private String email;

    @PasswordValidation
    private String password;

}
