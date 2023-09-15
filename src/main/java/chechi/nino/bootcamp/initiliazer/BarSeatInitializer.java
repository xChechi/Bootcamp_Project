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

        ZoneType[] zones = ZoneType.values();
        int seatsPerZone = 21;

        for (ZoneType zone : zones) {
            for (int i = 1; i <= seatsPerZone; i++) {
                BarSeat seat = new BarSeat();
                seat.setSeatNumber(i);
                seat.setZoneType(zone);
                barSeatRepository.save(seat);
            }
        }
    }

}
