package chechi.nino.bootcamp.entity.user;

import chechi.nino.bootcamp.entity.reservation.BarReservation;
import chechi.nino.bootcamp.entity.reservation.CarReservation;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import chechi.nino.bootcamp.entity.reservation.TableReservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    private int id;

    @NotBlank
    @Length(min = 2)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 2)
    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Length(max = 15)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RoomReservation> roomReservationList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<TableReservation> tableReservationList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<BarReservation> barReservationList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CarReservation> carReservationList;
}
