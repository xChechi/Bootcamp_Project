package chechi.nino.bootcamp.dto.car;

import chechi.nino.bootcamp.entity.car.CarType;
import chechi.nino.bootcamp.entity.car.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarResponse {

    private Integer id;

    @NotBlank
    private String model;

    @NotBlank
    @Size(min = 4, max = 4)
    private String year;

    private CarType carType;

    @NotNull
    private int seats;

    @NotNull
    private double dailyCharge;

    @NotNull
    private List<Image> images;
}
