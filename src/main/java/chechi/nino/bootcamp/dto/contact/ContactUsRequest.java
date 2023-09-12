package chechi.nino.bootcamp.dto.contact;

import chechi.nino.bootcamp.annotation.PhoneNumberValidation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContactUsRequest {

    @NotBlank
    @Size(min = 2)
    private String firstName;

    @NotBlank
    @Size(min = 2)
    private String lastName;

    @Email
    private String email;

    @PhoneNumberValidation
    private String phoneNumber;

    @NotBlank
    @Size(max = 650)
    private String message;
}
