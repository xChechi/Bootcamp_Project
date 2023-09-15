package chechi.nino.bootcamp.entity.reservation;

import chechi.nino.bootcamp.entity.bar.BarSeat;
import chechi.nino.bootcamp.entity.bar.ScreenEvent;
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

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "bar_reservations")
public class BarReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Future
    @Column(name = "event_time")
    private LocalDateTime eventTime;

    @OneToOne(mappedBy = "barReservation", cascade = CascadeType.ALL)
    private ScreenEvent screenEvent;

    @NotNull
    private Integer guests;

    @NotNull
    private Double totalCharge;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<BarSeat> barSeatList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
