package chechi.nino.bootcamp.entity.reservation;

import chechi.nino.bootcamp.entity.room.Room;
import chechi.nino.bootcamp.entity.user.User;
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
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_reservation_id")
    private int id;

    @Future
    @NotNull
    @Column(name = "check_in")
    private LocalDate checkIn;

    @Future
    @NotNull
    @Column(name = "check_out")
    private LocalDate checkOut;

    @NotNull
    private int guests;

    @NotNull
    @Column(name = "total_charge")
    private double totalCharge;

    @Enumerated(EnumType.STRING)
    private BedType bedType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Room room;
}
