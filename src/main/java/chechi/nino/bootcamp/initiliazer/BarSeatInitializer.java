package chechi.nino.bootcamp.initiliazer;

import chechi.nino.bootcamp.entity.bar.BarSeat;
import chechi.nino.bootcamp.entity.bar.ZoneType;
import chechi.nino.bootcamp.repository.BarSeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class BarSeatInitializer implements CommandLineRunner {

    private final BarSeatRepository barSeatRepository;

    @Override
    public void run(String... args) throws Exception {
        if (barSeatRepository.count() == 0) {
            initializeRooms();
        }
    }

    private void initializeRooms() {

        BarSeat seat1 = BarSeat.builder()
                .seatNumber(1)
                .zoneType(ZoneType.SCREEN_1)
                .build();

        BarSeat seat2 = BarSeat.builder()
                .seatNumber(2)
                .zoneType(ZoneType.SCREEN_1)
                .build();

        BarSeat seat3 = BarSeat.builder()
                .seatNumber(3)
                .zoneType(ZoneType.SCREEN_1)
                .build();

        BarSeat seat4 = BarSeat.builder()
                .seatNumber(4)
                .zoneType(ZoneType.SCREEN_1)
                .build();

        BarSeat seat5 = BarSeat.builder()
                .seatNumber(5)
                .zoneType(ZoneType.SCREEN_1)
                .build();

        BarSeat seat6 = BarSeat.builder()
                .seatNumber(6)
                .zoneType(ZoneType.SCREEN_1)
                .build();

        BarSeat seat7 = BarSeat.builder()
                .seatNumber(7)
                .zoneType(ZoneType.SCREEN_1)
                .build();

        BarSeat seat8 = BarSeat.builder()
                .seatNumber(8)
                .zoneType(ZoneType.SCREEN_2)
                .build();

        BarSeat seat9 = BarSeat.builder()
                .seatNumber(9)
                .zoneType(ZoneType.SCREEN_2)
                .build();

        BarSeat seat10 = BarSeat.builder()
                .seatNumber(10)
                .zoneType(ZoneType.SCREEN_2)
                .build();

        BarSeat seat11 = BarSeat.builder()
                .seatNumber(11)
                .zoneType(ZoneType.SCREEN_2)
                .build();

        BarSeat seat12 = BarSeat.builder()
                .seatNumber(12)
                .zoneType(ZoneType.SCREEN_2)
                .build();

        BarSeat seat13 = BarSeat.builder()
                .seatNumber(13)
                .zoneType(ZoneType.SCREEN_2)
                .build();

        BarSeat seat14 = BarSeat.builder()
                .seatNumber(14)
                .zoneType(ZoneType.SCREEN_2)
                .build();

        BarSeat seat15 = BarSeat.builder()
                .seatNumber(15)
                .zoneType(ZoneType.SCREEN_3)
                .build();

        BarSeat seat16 = BarSeat.builder()
                .seatNumber(16)
                .zoneType(ZoneType.SCREEN_3)
                .build();

        BarSeat seat17 = BarSeat.builder()
                .seatNumber(17)
                .zoneType(ZoneType.SCREEN_3)
                .build();

        BarSeat seat18 = BarSeat.builder()
                .seatNumber(18)
                .zoneType(ZoneType.SCREEN_3)
                .build();

        BarSeat seat19 = BarSeat.builder()
                .seatNumber(19)
                .zoneType(ZoneType.SCREEN_3)
                .build();

        BarSeat seat20= BarSeat.builder()
                .seatNumber(20)
                .zoneType(ZoneType.SCREEN_3)
                .build();

        BarSeat seat21 = BarSeat.builder()
                .seatNumber(21)
                .zoneType(ZoneType.SCREEN_3)
                .build();

        barSeatRepository.saveAll(Arrays.asList(seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12, seat13
                                                , seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat21));
    }
}
