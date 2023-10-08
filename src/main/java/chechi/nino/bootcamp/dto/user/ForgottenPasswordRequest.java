package chechi.nino.bootcamp.dto.user;

import jakarta.validation.constraints.Email;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ForgottenPasswordRequest {

    @Email
    private String email;
}
