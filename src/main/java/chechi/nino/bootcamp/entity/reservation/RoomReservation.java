package chechi.nino.bootcamp.entity.reservation;

import chechi.nino.bootcamp.entity.room.Room;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.repository.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "room_reservations")
public class RoomReservation implements Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_reservation_id")
    private Integer id;

    @Future
    @NotNull
    @Column(name = "check_in")
    private LocalDate startDate;

    @Future
    @NotNull
    @Column(name = "check_out")
    private LocalDate endDate;

    @NotNull
    private Integer guests;

    @NotNull
    @Column(name = "total_charge")
    private Double totalCharge;

    @Enumerated(EnumType.STRING)
    private BedType bedType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    //@JsonManagedReference <---------- Broke the server
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    //@JsonManagedReference
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Room room;

}
