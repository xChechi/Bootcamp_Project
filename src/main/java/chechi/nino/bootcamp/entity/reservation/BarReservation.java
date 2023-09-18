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
import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private ScreenEvent screenEvent;

    @NotNull
    private Integer guests;

    @NotNull
    private Double totalCharge;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
/*
    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    private List<BarSeat> barSeatList = new ArrayList<>();
*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bar_reservation_seat",
            joinColumns = @JoinColumn(name = "bar_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<BarSeat> barSeatList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
