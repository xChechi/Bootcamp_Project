package chechi.nino.bootcamp.entity.contact;

import chechi.nino.bootcamp.annotation.PhoneNumberValidation;
import chechi.nino.bootcamp.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "contact_us")
public class ContactUsForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id")
    private Integer id;

    @NotBlank
    @Size(min = 2)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2)
    @Column(name = "last_name")
    private String lastName;

    @Email
    private String email;

    @PhoneNumberValidation
    private String phoneNumber;

    @NotBlank
    @Size(max = 650)
    private String message;

}
