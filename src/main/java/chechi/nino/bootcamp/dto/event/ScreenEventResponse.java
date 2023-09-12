package chechi.nino.bootcamp.dto.event;

import chechi.nino.bootcamp.entity.bar.ZoneType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ScreenEventResponse {

    private Integer id;

    @NotBlank
    @Size(min = 10)
    private String eventName;

    @Future
    private LocalDateTime eventTime;

    private ZoneType zoneType;
}
