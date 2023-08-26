package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRequest;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationRoomUpdateRequest;
import chechi.nino.bootcamp.dto.reservation_table.RestaurantTableReservationRequest;
import chechi.nino.bootcamp.dto.reservation_table.RestaurantTableReservationResponse;
import chechi.nino.bootcamp.entity.room.RestaurantTable;

import java.util.List;

public interface TableReservationService {

    List<RestaurantTable> findAvailableTablesWithClosestCapacity(int numberOfGuests);
/*
    List<RestaurantTableReservationResponse> getAllReservations();

    RestaurantTableReservationResponse findReservationById (Integer id);

    RestaurantTableReservationResponse bookReservation (Integer userId, RestaurantTableReservationRequest request);

    RestaurantTableReservationResponse updateTable (Integer id, RestaurantTableReservationUpdateTable request);

 */
}
