package chechi.nino.bootcamp.dto.security;

import chechi.nino.bootcamp.annotation.PasswordValidation;
import chechi.nino.bootcamp.annotation.PhoneNumberValidation;
import chechi.nino.bootcamp.entity.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @PhoneNumberValidation
    private String phoneNumber;

    @PasswordValidation
    private String password;
}
