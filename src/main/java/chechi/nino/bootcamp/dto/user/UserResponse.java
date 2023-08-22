package chechi.nino.bootcamp.dto.user;

import lombok.*;

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
