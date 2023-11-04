package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.reservation_table.TableReservationRequest;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import chechi.nino.bootcamp.entity.reservation.TableReservation;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.exception.TableNotFoundException;
import chechi.nino.bootcamp.exception.UserNotFoundException;
import chechi.nino.bootcamp.repository.RestaurantTableRepository;
import chechi.nino.bootcamp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TableReservationConverter {

    private final RestaurantTableRepository tableRepository;
    private final TableConverter tableConverter;
    private final UserRepository userRepository;
    private final UserConverter userConverter;



    public TableReservation bookReservation (Integer userId, TableReservationRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        RestaurantTable table = tableRepository.findById(request.getTableId()).orElseThrow(() -> new TableNotFoundException("Table not found"));

        int requestedGuests = request.getGuests();
        int tableCapacity = table.getTableZone().getTableCapacity();

        if (requestedGuests > tableCapacity) {
            throw new IllegalArgumentException("Number of guests exceeds table capacity");
        }

        return TableReservation.builder()
                .user(user)
                .table(table)
                .reservationDate(request.getReservationDate())
                .reservationTime(request.getReservationTime())
                .tableZone(request.getTableZone())
                .isSmoke(request.isSmoke())
                .totalCharge(calculatedCharge(request.getTableZone().getTableCapacity()))
                .guests(request.getGuests())
                .build();
    }

    public Double calculatedCharge(int tableCapacity) {
        return tableCapacity * 10.00;
    }

    public List<RestaurantTable> findAvailableTablesWithClosestCapacity(int numberOfGuests) {
        List<RestaurantTable> allTables = tableRepository.findAll();
        int minCapacityDifference = Integer.MAX_VALUE;
        List<RestaurantTable> closestCapacityTables = new ArrayList<>();

        for (RestaurantTable table : allTables) {
            int capacityDifference = Math.abs(table.getTableZone().getTableCapacity() - numberOfGuests);

            if (capacityDifference < minCapacityDifference) {
                minCapacityDifference = capacityDifference;
                closestCapacityTables.clear();
                closestCapacityTables.add(table);
            } else if (capacityDifference == minCapacityDifference) {
                closestCapacityTables.add(table);
            }
        }

        return closestCapacityTables;
    }

    public TableReservationResponse toTableReservationResponse (TableReservation tableReservation) {

        return TableReservationResponse.builder()
                .id(tableReservation.getId())
                .user(userConverter.toUserResponse(tableReservation.getUser()))
                .table(tableConverter.totableResponse(tableReservation.getTable()))
                .reservationDate(tableReservation.getReservationDate())
                .reservationTime(tableReservation.getReservationTime())
                .tableZone(tableReservation.getTableZone())
                .isSmoke(tableReservation.isSmoke())
                .guests(tableReservation.getGuests())
                .totalCharge(tableReservation.getTotalCharge())
                .build();
    }

}
