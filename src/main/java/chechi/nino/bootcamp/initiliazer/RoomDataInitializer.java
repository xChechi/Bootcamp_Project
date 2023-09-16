package chechi.nino.bootcamp.initiliazer;

import chechi.nino.bootcamp.entity.room.*;
import chechi.nino.bootcamp.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class RoomDataInitializer implements CommandLineRunner {

    private final RoomRepository roomRepository;

    //public RoomDataInitializer(RoomRepository roomRepository) {
    //    this.roomRepository = roomRepository;
    //}

    @Override
    public void run(String... args) {
        if (roomRepository.count() == 0) {
            initializeRooms();
        }
    }

    private void initializeRooms() {
        Room room1 = Room.builder()
                .roomNumber("SS 101")
                .roomType(RoomType.STANDARD)
                .roomView(RoomView.SEA)
                .roomPrice(220.0)
                .build();

        Room room2 = Room.builder()
                .roomNumber("SS 102")
                .roomType(RoomType.STANDARD)
                .roomView(RoomView.SEA)
                .roomPrice(220.0)
                .build();

        Room room3 = Room.builder()
                .roomNumber("SP 103")
                .roomType(RoomType.STANDARD)
                .roomView(RoomView.POOL)
                .roomPrice(220.0)
                .build();

        Room room4 = Room.builder()
                .roomNumber("SP 104")
                .roomType(RoomType.STANDARD)
                .roomView(RoomView.POOL)
                .roomPrice(220.0)
                .build();

        Room room5 = Room.builder()
                .roomNumber("SG 105")
                .roomType(RoomType.STANDARD)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.0)
                .build();

        Room room6 = Room.builder()
                .roomNumber("SG 106")
                .roomType(RoomType.STANDARD)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.0)
                .build();

        Room room7 = Room.builder()
                .roomNumber("SG 107")
                .roomType(RoomType.STANDARD)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.0)
                .build();

        Room room8 = Room.builder()
                .roomNumber("SG 108")
                .roomType(RoomType.STANDARD)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.0)
                .build();

        Room room9 = Room.builder()
                .roomNumber("LG 201")
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.GARDEN)
                .roomPrice(320.0)
                .build();

        Room room10 = Room.builder()
                .roomNumber("LG 202")
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.GARDEN)
                .roomPrice(320.0)
                .build();

        Room room11 = Room.builder()
                .roomNumber("LP 203")
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.POOL)
                .roomPrice(320.0)
                .build();

        Room room12 = Room.builder()
                .roomNumber("LP 204")
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.POOL)
                .roomPrice(320.0)
                .build();

        Room room13 = Room.builder()
                .roomNumber("LS 207")
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.SEA)
                .roomPrice(320.0)
                .build();

        Room room14 = Room.builder()
                .roomNumber("LS 208")
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.SEA)
                .roomPrice(320.0)
                .build();

        Room room15 = Room.builder()
                .roomNumber("AP 301")
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.POOL)
                .roomPrice(520.0)
                .build();

        Room room16 = Room.builder()
                .roomNumber("AS 302")
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.SEA)
                .roomPrice(520.0)
                .build();

        Room room17 = Room.builder()
                .roomNumber("AS 303")
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.SEA)
                .roomPrice(520.0)
                .build();

        roomRepository.saveAll(Arrays.asList(room1, room2, room3, room4, room5, room6, room7, room8, room9,
                                            room10, room11, room12, room13, room14, room15, room16, room17));
    }
}
