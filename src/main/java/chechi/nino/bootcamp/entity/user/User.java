package chechi.nino.bootcamp.entity.user;

import chechi.nino.bootcamp.annotation.PasswordValidation;
import chechi.nino.bootcamp.annotation.PhoneNumberValidation;
import chechi.nino.bootcamp.entity.reservation.*;
import chechi.nino.bootcamp.repository.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "first_name"),
        @UniqueConstraint(columnNames = "last_name"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NotBlank
    @Length(min = 2)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Length(min = 2)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @PhoneNumberValidation
    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @PasswordValidation
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RoomReservation> roomReservationList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TableReservation> tableReservationList;
/*
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<BarReservation> barReservationList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CarReservation> carReservationList;

     */
}
