package chechi.nino.bootcamp.entity.bar;

import chechi.nino.bootcamp.entity.reservation.BarReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "screen_events")
public class ScreenEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @NotBlank
    @Size(min = 10)
    @Column(name = "event_name")
    private String eventName;

    @Future
    @Column(name = "event_time")
    private LocalDateTime eventTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "zone_type")
    private ZoneType zoneType;

    @OneToOne
    @JoinColumn(name = "bar_reservation_id", referencedColumnName = "id")
    private BarReservation barReservation;

}
