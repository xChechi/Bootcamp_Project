package chechi.nino.bootcamp.entity.car;

import chechi.nino.bootcamp.entity.reservation.CarReservation;
import chechi.nino.bootcamp.entity.reservation.RoomReservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer id;

    @NotBlank
    private String model;

    @NotBlank
    @Size(min = 4, max = 4)
    private String year;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CarReservation> carReservationList;

}
