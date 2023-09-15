package chechi.nino.bootcamp.entity.bar;

import chechi.nino.bootcamp.entity.reservation.BarReservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "bar_seats")
public class BarSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer id;

    @NotNull
    @Column(name = "seat_number")
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    private ZoneType zoneType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bar_reservation_id")
    //@JsonIgnore
    @JsonBackReference
    private BarReservation reservation;
}
