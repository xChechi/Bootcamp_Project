package chechi.nino.bootcamp.entity.reservation;

import chechi.nino.bootcamp.entity.car.Car;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import chechi.nino.bootcamp.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class CarReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_reservation_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
