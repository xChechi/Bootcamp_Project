package chechi.nino.bootcamp.entity.bar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ZoneType {

    SCREEN_1("Screen 1"),
    SCREEN_2("Screen 2"),
    SCREEN_3("Screen 3");

    private final String displayName;
}
