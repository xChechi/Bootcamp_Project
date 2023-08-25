package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.reservation_table.RestaurantTableReservationRequest;
import chechi.nino.bootcamp.entity.room.RestaurantTable;

import java.util.List;

public interface TableReservationService {

    //List<RestaurantTable> getAvailableTablesWithCapacity(int numberOfGuests);

    List<RestaurantTable> findAvailableTablesWithClosestCapacity(int numberOfGuests);
}
/*
@Service
public class TableReservationService {

    @Autowired
    private TableRepository tableRepository;

    public List<RestaurantTable> getAvailableTablesWithCapacity(int numberOfGuests) {
        return tableRepository.findAvailableTablesWithCapacity(numberOfGuests);
    }
}
 */