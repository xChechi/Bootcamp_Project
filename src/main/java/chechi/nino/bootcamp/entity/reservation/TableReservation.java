package chechi.nino.bootcamp.entity.reservation;

import chechi.nino.bootcamp.entity.restaurant.TableZone;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
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
@Table(name = "table_reservations")
public class TableReservation implements Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_reservation_id")
    private int id;

    @Future
    @NotNull
    @Column(name = "check_in")
    private LocalDate startDate;

    @Future
    @NotNull
    @Column(name = "check_out")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private TableZone tableZone;

    private boolean isSmoke;

    @NotNull
    private Integer guests;

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
    @JoinColumn(name = "table_id")
    @JsonManagedReference
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private RestaurantTable table;

}
