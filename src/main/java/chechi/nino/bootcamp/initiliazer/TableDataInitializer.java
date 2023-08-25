package chechi.nino.bootcamp.initiliazer;


import chechi.nino.bootcamp.entity.restaurant.TableZone;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import chechi.nino.bootcamp.repository.RestaurantTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class TableDataInitializer implements CommandLineRunner {

    private final RestaurantTableRepository tableRepository;

    @Override
    public void run(String... args) throws Exception {
        if (tableRepository.count() == 0) {
            initializeRooms();
        }
    }

    private void initializeRooms() {
        RestaurantTable table1 = RestaurantTable.builder()
                .tableNumber("S_1")
                .tableZone(TableZone.SALOON_4)
                .build();

        RestaurantTable table2 = RestaurantTable.builder()
                .tableNumber("S_2")
                .tableZone(TableZone.SALOON_4)
                .build();

        RestaurantTable table3 = RestaurantTable.builder()
                .tableNumber("S_3")
                .tableZone(TableZone.SALOON_4)
                .build();

        RestaurantTable table4 = RestaurantTable.builder()
                .tableNumber("S_4")
                .tableZone(TableZone.SALOON_4)
                .build();

        RestaurantTable table5 = RestaurantTable.builder()
                .tableNumber("S_5")
                .tableZone(TableZone.SALOON_4)
                .build();

        RestaurantTable table6 = RestaurantTable.builder()
                .tableNumber("S_6")
                .tableZone(TableZone.SALOON_2)
                .build();

        RestaurantTable table7 = RestaurantTable.builder()
                .tableNumber("S_7")
                .tableZone(TableZone.SALOON_7)
                .build();

        RestaurantTable table8 = RestaurantTable.builder()
                .tableNumber("S_8")
                .tableZone(TableZone.SALOON_7)
                .build();

        RestaurantTable table9 = RestaurantTable.builder()
                .tableNumber("S_7")
                .tableZone(TableZone.SALOON_8)
                .build();

        RestaurantTable table10 = RestaurantTable.builder()
                .tableNumber("S_10")
                .tableZone(TableZone.SALOON_2)
                .build();

        RestaurantTable table11 = RestaurantTable.builder()
                .tableNumber("T_11")
                .tableZone(TableZone.TERRACE)
                .build();

        RestaurantTable table12 = RestaurantTable.builder()
                .tableNumber("T_12")
                .tableZone(TableZone.TERRACE)
                .build();

        RestaurantTable table13 = RestaurantTable.builder()
                .tableNumber("T_13")
                .tableZone(TableZone.TERRACE)
                .build();

        RestaurantTable table14 = RestaurantTable.builder()
                .tableNumber("T_14")
                .tableZone(TableZone.TERRACE)
                .build();

        RestaurantTable table15 = RestaurantTable.builder()
                .tableNumber("T_15")
                .tableZone(TableZone.TERRACE)
                .build();

        RestaurantTable table16 = RestaurantTable.builder()
                .tableNumber("T_16")
                .tableZone(TableZone.TERRACE)
                .build();

        RestaurantTable table17 = RestaurantTable.builder()
                .tableNumber("BAR_1")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table18 = RestaurantTable.builder()
                .tableNumber("BAR_2")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table19 = RestaurantTable.builder()
                .tableNumber("BAR_3")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table20 = RestaurantTable.builder()
                .tableNumber("BAR_4")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table21 = RestaurantTable.builder()
                .tableNumber("BAR_5")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table22 = RestaurantTable.builder()
                .tableNumber("BAR_6")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table23 = RestaurantTable.builder()
                .tableNumber("BAR_7")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table24 = RestaurantTable.builder()
                .tableNumber("BAR_8")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table25 = RestaurantTable.builder()
                .tableNumber("BAR_9")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table26 = RestaurantTable.builder()
                .tableNumber("BAR_10")
                .tableZone(TableZone.BAR)
                .build();

        RestaurantTable table27 = RestaurantTable.builder()
                .tableNumber("BAR_11")
                .tableZone(TableZone.BAR)
                .build();

        tableRepository.saveAll(Arrays.asList(table1, table2, table3, table4, table5, table6, table7, table8, table9
                , table10, table11, table12, table13, table14, table15, table16, table17, table18, table19, table20
                , table21, table22, table23, table24, table25, table26, table27));
    }
}
