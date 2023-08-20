package chechi.nino.bootcamp.entity.room;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;

    @NotBlank
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    private RoomView roomView;

    @NotNull
    private double roomPrice;

    @ElementCollection(targetClass = FacilityType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "room_facilities", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "facility_type")
    private List<FacilityType> facilityTypeList;
}
