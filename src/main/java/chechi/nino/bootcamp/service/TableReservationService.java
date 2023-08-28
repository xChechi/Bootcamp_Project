package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.reservation_table.TableReservationRequest;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationUpdateTableRequest;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationUpdateTimeRequest;
import chechi.nino.bootcamp.entity.room.RestaurantTable;

import java.time.LocalDate;
import java.util.List;

public interface TableReservationService {

    List<RestaurantTable> findAvailableTablesWithClosestCapacity(int numberOfGuests);

    List<TableReservationResponse> getAllReservations();

    TableReservationResponse findReservationById (Integer id);

    TableReservationResponse bookReservation (Integer userId, TableReservationRequest request);

    TableReservationResponse updateTable (Integer id, TableReservationUpdateTableRequest request);

    TableReservationResponse updateTime (Integer id, TableReservationUpdateTimeRequest request);

    void deleteTableReservation (Integer id);

    List<TableReservationResponse> searchByUser (Integer userId);

    List<TableReservationResponse> searchByTable (Integer tableId);

    List<TableReservationResponse> searchByDate (LocalDate reservationDate);




}
