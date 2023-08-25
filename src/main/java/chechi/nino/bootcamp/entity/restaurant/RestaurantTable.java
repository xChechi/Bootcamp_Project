package chechi.nino.bootcamp.entity.room;

import chechi.nino.bootcamp.entity.reservation.TableReservation;
import chechi.nino.bootcamp.entity.restaurant.TableZone;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "restaurant_tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_table_id")
    private Integer id;

    @NotBlank
    @Column(name = "table_number", nullable = false)
    private String tableNumber;

    @Enumerated(EnumType.STRING)
    private TableZone tableZone;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<TableReservation> tableReservationList;

}
