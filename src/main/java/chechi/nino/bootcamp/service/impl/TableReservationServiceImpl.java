package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.TableReservationConverter;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationRequest;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationUpdateTableRequest;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationUpdateTimeRequest;
import chechi.nino.bootcamp.entity.reservation.TableReservation;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import chechi.nino.bootcamp.exception.TableNotFoundException;
import chechi.nino.bootcamp.exception.TableReservationNotFoundException;
import chechi.nino.bootcamp.repository.RestaurantTableRepository;
import chechi.nino.bootcamp.repository.TableReservationRepository;
import chechi.nino.bootcamp.service.TableReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<TableReservationResponse> getAllReservations() {

        List<TableReservation> reservations = tableReservationRepository.findAll();
        List<TableReservationResponse> responses = new ArrayList<>();

        for (TableReservation r : reservations) {
            TableReservationResponse response = tableReservationConverter.toTableReservationResponse(r);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public TableReservationResponse findReservationById(Integer id) {

        TableReservation reservation = tableReservationRepository.findById(id)
                .orElseThrow(() -> new TableReservationNotFoundException("Reservation not found"));
        return tableReservationConverter.toTableReservationResponse(reservation);
    }

    @Override
    public TableReservationResponse bookReservation(Integer userId, TableReservationRequest request) {

        TableReservation reservation = tableReservationConverter.bookReservation(userId, request);
        TableReservation savedReservation = tableReservationRepository.save(reservation);

        int guests = reservation.getGuests();
        int tableCapacity = request.getTableZone().getTableCapacity();

        if (guests > tableCapacity) {
            throw new IllegalArgumentException("Number of guests exceeds room capacity");
        }

        boolean reservationIsSmoke = reservation.isSmoke();
        boolean tableIsSmoke = request.getTableZone().isSmoke();

        if (reservationIsSmoke != tableIsSmoke) {
            throw new IllegalArgumentException("Requested table not met reservation smoking requirement");
        }

        return tableReservationConverter.toTableReservationResponse(savedReservation);
    }

    @Override
    public TableReservationResponse updateTable(Integer id, TableReservationUpdateTableRequest request) {

        TableReservation reservation = tableReservationRepository.findById(id)
                .orElseThrow(() -> new TableReservationNotFoundException("Reservation not found"));
        RestaurantTable table = tableRepository.findById(request.getTableId()).orElseThrow(() -> new TableNotFoundException("Table not found"));

        reservation.setTable(table);

        Double newCalculatedCharge = tableReservationConverter.calculatedCharge(reservation.getTableZone().getTableCapacity());
        reservation.setTotalCharge(newCalculatedCharge);

        int guests = reservation.getGuests();
        int tableCapacity = table.getTableZone().getTableCapacity();

        if (guests > tableCapacity) {
            throw new IllegalArgumentException("Number of guests exceeds room capacity");
        }

        boolean reservationIsSmoke = reservation.isSmoke();
        boolean tableIsSmoke = table.getTableZone().isSmoke();

        if (reservationIsSmoke != tableIsSmoke) {
            throw new IllegalArgumentException("Requested table not met reservation smoking requirement");
        }

        TableReservation savedReservation = tableReservationRepository.save(reservation);

        return tableReservationConverter.toTableReservationResponse(savedReservation);
    }

    @Override
    public TableReservationResponse updateTime(Integer id, TableReservationUpdateTimeRequest request) {

        TableReservation reservation = tableReservationRepository.findById(id)
                .orElseThrow(() -> new TableReservationNotFoundException("Reservation not found"));

        reservation.setReservationDate(request.getReservationDate());
        reservation.setReservationTime(request.getReservationTime());

        TableReservation savedReservation = tableReservationRepository.save(reservation);

        return tableReservationConverter.toTableReservationResponse(savedReservation);
    }

    @Override
    public void deleteTableReservation(Integer id) {
        tableReservationRepository.deleteById(id);
    }

    @Override
    public List<TableReservationResponse> searchByUser(Integer userId) {

        List<TableReservation> reservations = tableReservationRepository.findAll();

        return reservations.stream()
                .filter(tableReservation -> {
                    Integer reservationUserId = tableReservation.getUser().getId();
                    return reservationUserId != null && reservationUserId.equals(userId);
                })
                .map(tableReservationConverter::toTableReservationResponse)
                .toList();
    }

    @Override
    public List<TableReservationResponse> searchByTable(Integer tableId) {

        List<TableReservation> reservations = tableReservationRepository.findAll();

        return reservations.stream()
                .filter( tableReservation -> {
                    Integer reservationTableId = tableReservation.getTable().getId();
                    return reservationTableId != null && reservationTableId.equals(tableId);
                })
                .map(tableReservationConverter::toTableReservationResponse)
                .toList();
    }

    @Override
    public List<TableReservationResponse> searchByDate(LocalDate reservationDate) {

        List<TableReservation> reservations = tableReservationRepository.searchByDate(reservationDate);

        return reservations.stream()
                .map(tableReservationConverter::toTableReservationResponse)
                .toList();
    }

}
