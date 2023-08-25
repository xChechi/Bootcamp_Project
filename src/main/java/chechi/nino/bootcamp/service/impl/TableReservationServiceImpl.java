package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.TableReservationConverter;
import chechi.nino.bootcamp.dto.reservation_table.RestaurantTableReservationRequest;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import chechi.nino.bootcamp.repository.RestaurantTableRepository;
import chechi.nino.bootcamp.repository.TableReservationRepository;
import chechi.nino.bootcamp.service.TableReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TableReservationServiceImpl implements TableReservationService {

    private final TableReservationRepository tableReservationRepository;
    private final TableReservationConverter tableReservationConverter;
    private final RestaurantTableRepository tableRepository;

    @Override
    public List<RestaurantTable> findAvailableTablesWithClosestCapacity(int numberOfGuests) {
        return tableReservationConverter.findAvailableTablesWithClosestCapacity(numberOfGuests);
    }


}
