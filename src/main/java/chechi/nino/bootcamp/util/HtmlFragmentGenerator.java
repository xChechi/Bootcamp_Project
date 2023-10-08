package chechi.nino.bootcamp.util;

import chechi.nino.bootcamp.dto.bar.BarSeatResponse;
import chechi.nino.bootcamp.dto.car.CarResponse;
import chechi.nino.bootcamp.dto.contact.ContactUsResponse;
import chechi.nino.bootcamp.dto.event.ScreenEventResponse;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.dto.table.TableResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.car.Image;
import chechi.nino.bootcamp.entity.room.FacilityType;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HtmlFragmentGenerator {

    public String generateUserListFragment(List<UserResponse> users) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Clients List</h4>");
        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>ID</th><th>Name</th><th>Phone number</th><th>Email</th></tr></thead>");
        fragment.append("<tbody>");
        for (UserResponse user : users) {
            fragment.append("<tr class='table-row-hover'><td class='text-center'>").append(user.getId()).append("</td>");
            fragment.append("<td>").append(user.getFirstName()).append(" ").append(user.getLastName()).append("</td>");
            fragment.append("<td>").append(user.getPhoneNumber()).append("</td>");
            fragment.append("<td>").append(user.getEmail()).append("</td></tr>");
        }
        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    public String generateRoomListFragment(List<RoomResponse> rooms) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Rooms List</h4>");
        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>Room ID</th><th>Room Number</th><th>Room Type</th><th>Room View</th><th>Price</th><th>Size</th><th>Capacity</th><th>Facilities</th></tr></thead>");
        fragment.append("<tbody>");

        for (RoomResponse room : rooms) {
            fragment.append("<tr class='table-row-hover'>");
            fragment.append("<td class='text-center'>").append(room.getId()).append("</td>");
            fragment.append("<td class='text-center'>").append(room.getRoomNumber()).append("</td>");
            fragment.append("<td class='text-center'>").append(room.getRoomType()).append("</td>");
            fragment.append("<td class='text-center'>").append(room.getRoomView()).append("</td>");
            fragment.append("<td class='text-center'>").append(room.getRoomPrice()).append("</td>");
            fragment.append("<td class='text-center'>").append(room.getRoomSize()).append("</td>");
            fragment.append("<td class='text-center'>").append(room.getRoomCapacity()).append("</td>");
            fragment.append("<td>").append(getFacilitiesDisplayNames(room.getFacilities())).append("</td>");
            fragment.append("</tr>");
        }

        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    private String getFacilitiesDisplayNames(List<FacilityType> facilities) {
        StringBuilder facilityNames = new StringBuilder();
        for (FacilityType facility : facilities) {
            facilityNames.append(facility.getDisplayName()).append(", ");
        }
        // Remove the trailing comma and space
        if (facilityNames.length() > 0) {
            facilityNames.setLength(facilityNames.length() - 2);
        }
        return facilityNames.toString();
    }

    public String generateDashboard() {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<img src='/img/dashboard.png' alt='Default Image' class='img-fluid'>");

        return fragment.toString();
    }

    public String generateTableListFragment (List<TableResponse> tables) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Table List</h4>");
        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>Table ID</th><th>Table Number</th><th>Table Zone</th><th>Smoking Area</th><th>Capacity</th></tr></thead>");
        fragment.append("<tbody>");

        for (TableResponse table : tables) {
            fragment.append("<tr class='table-row-hover'>");
            fragment.append("<td class='text-center'>").append(table.getId()).append("</td>");
            fragment.append("<td class='text-center'>").append(table.getTableNumber()).append("</td>");
            fragment.append("<td class='text-center'>").append(table.getTableZone()).append("</td>");
            fragment.append("<td class='text-center'>").append(table.isSmoke() ? "Yes" : "No").append("</td>");
            fragment.append("<td class='text-center'>").append(table.getTableCapacity()).append("</td>");
            fragment.append("</tr>");
        }

        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    public String generateSeatListFragment (List<BarSeatResponse> seats) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Bar Seats List</h4>");
        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>Bar Seat ID</th><th>Seat Number</th><th>Bar Zone</th></tr></thead>");
        fragment.append("<tbody>");

        for (BarSeatResponse seat : seats) {
            fragment.append("<tr class='table-row-hover'>");
            fragment.append("<td class='text-center'>").append(seat.getId()).append("</td>");
            fragment.append("<td class='text-center'>").append(seat.getSeatNumber()).append("</td>");
            fragment.append("<td class='text-center'>").append(seat.getZoneType().getDisplayName()).append("</td>");
            fragment.append("</tr>");
        }

        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    public String generateRoomReservationListFragment (List<RoomReservationResponse> reservations) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Room Reservations List</h4>");

        fragment.append("<form th:action='@{/api/v1/admin-dashboard/roomReservations}' method='get' class='mb-4'>");
        fragment.append("<div class='row'>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='userSearch' class='form-label'>Search by User:</label>");
        fragment.append("<input type='text' id='userSearch' name='userSearch' class='form-control' placeholder='Enter User ID'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='roomSearch' class='form-label'>Search by Room:</label>");
        fragment.append("<input type='text' id='roomSearch' name='roomSearch' class='form-control' placeholder='Enter Room ID'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='dateSearch' class='form-label'>Search by Date:</label>");
        fragment.append("<input type='date' id='dateSearch' name='dateSearch' class='form-control'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-1'>");
        fragment.append("<button type='submit' class='btn btn-primary mt-4'>Search</button>");
        fragment.append("</div>");
        fragment.append("</div>");
        fragment.append("</form>");

        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>ID</th><th>Client</th><th>Room</th><th>Check In</th><th>Check Out</th><th>Number of Guests</th><th>Total Charge</th><th>Bed Type</th></tr></thead>");
        fragment.append("<tbody>");

        for (RoomReservationResponse reservation : reservations) {
            fragment.append("<tr class='table-row-hover'>");
            fragment.append("<td class='text-center'>").append(reservation.getId()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getUser().getFirstName()).append(" ").append(reservation.getUser().getLastName()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getRoom().getRoomNumber()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getStartDate()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getEndDate()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getGuests()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getTotalCharge()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getBedType().getDisplayName()).append("</td>");
            fragment.append("</tr>");
        }

        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    public String generateCarsFragment(List<CarResponse> cars) {
        StringBuilder fragment = new StringBuilder();

        fragment.append("<div class='row row-cols-1 row-cols-md-5 g-4'>"); // Start row and columns

        for (CarResponse car : cars) {
            fragment.append("<div class='col'>");
            fragment.append("<div class='car-card h-80'>");
            List<Image> images = car.getImages();
            if (!images.isEmpty()) {
                // Get the first image as Base64 encoded string
                Image firstImage = images.get(0);
                byte[] imageData = Base64.getDecoder().decode(firstImage.getImageData());

                // Convert the bytes to Base64
                String base64Image = Base64.getEncoder().encodeToString(imageData);

                // Create a data URL for the image
                String dataUrl = "data:image/png;base64," + base64Image;

                // Display the image using data URL
                fragment.append("<img src='").append(dataUrl).append("' class='card-img-top card-img-custom' alt='Car Image'>");
            }
            fragment.append("<div class='car-card-body'>");
            fragment.append("<h5 class='card-title'>").append(car.getModel()).append(" - ").append(car.getYear()).append("</h5>");
            fragment.append("<p class='card-text'>Car Type: ").append(car.getCarType().toString()).append("</p>");
            fragment.append("<p class='card-text'>Seats: ").append(car.getSeats()).append("</p>");
            fragment.append("<p class='card-text'>Daily Charge: $").append(car.getDailyCharge()).append("</p>");
            fragment.append("</div></div></div>");
        }

        fragment.append("</div>"); // End row and columns

        return fragment.toString();
    }

    public String generateTableReservationListFragment (List<TableReservationResponse> reservations) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Table Reservations List</h4>");

        fragment.append("<form th:action='@{/api/v1/admin-dashboard/roomReservations}' method='get' class='mb-4'>");
        fragment.append("<div class='row'>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='userSearch' class='form-label'>Search by User:</label>");
        fragment.append("<input type='text' id='userSearch' name='userSearch' class='form-control' placeholder='Enter User ID'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='roomSearch' class='form-label'>Search by Table:</label>");
        fragment.append("<input type='text' id='tableSearch' name='tableSearch' class='form-control' placeholder='Enter Table ID'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='dateSearch' class='form-label'>Search by Date:</label>");
        fragment.append("<input type='date' id='dateSearch' name='dateSearch' class='form-control'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-1'>");
        fragment.append("<button type='submit' class='btn btn-primary mt-4'>Search</button>");
        fragment.append("</div>");
        fragment.append("</div>");
        fragment.append("</form>");

        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>ID</th><th>Client</th><th>Table</th><th>Date</th><th>Time</th><th>Number of Guests</th><th>Zone</th><th>Smoking Area</th><th>Capacity</th></tr></thead>");
        fragment.append("<tbody>");

        for (TableReservationResponse reservation : reservations) {
            fragment.append("<tr class='table-row-hover'>");
            fragment.append("<td class='text-center'>").append(reservation.getId()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getUser().getFirstName()).append(" ").append(reservation.getUser().getLastName()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getTable().getTableNumber()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getReservationDate()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getReservationTime()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getGuests()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getTableZone()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getTableZone().isSmoke() ? "Yes" : "No").append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getTableZone().getTableCapacity()).append("</td>");
            fragment.append("</tr>");
        }

        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    public String generateBarReservationListFragment (List<BarReservationResponse> reservations) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Bar Reservations List</h4>");

        fragment.append("<form th:action='@{/api/v1/admin-dashboard/roomReservations}' method='get' class='mb-4'>");
        fragment.append("<div class='row'>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='userSearch' class='form-label'>Search by User:</label>");
        fragment.append("<input type='text' id='userSearch' name='userSearch' class='form-control' placeholder='Enter User ID'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='seatSearch' class='form-label'>Search by Seat:</label>");
        fragment.append("<input type='text' id='seatSearch' name='seatSearch' class='form-control' placeholder='Enter Seat ID'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='dateSearch' class='form-label'>Search by Date:</label>");
        fragment.append("<input type='date' id='dateSearch' name='dateSearch' class='form-control'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-1'>");
        fragment.append("<button type='submit' class='btn btn-primary mt-4'>Search</button>");
        fragment.append("</div>");
        fragment.append("</div>");
        fragment.append("</form>");

        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>ID</th><th>Client</th><th>Seats</th><th>Event Time</th><th>Event Name</th><th>Number of Guests</th><th>Total Charge</th></tr></thead>");
        fragment.append("<tbody>");

        for (BarReservationResponse reservation : reservations) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedEventTime = reservation.getEventTime().format(formatter);

            fragment.append("<tr class='table-row-hover'>");
            fragment.append("<td class='text-center'>").append(reservation.getId()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getUser().getFirstName()).append(" ").append(reservation.getUser().getLastName()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getSeats().stream().map(BarSeatResponse::getSeatNumber).map(String::valueOf).collect(Collectors.joining(", "))).append("</td>");
            fragment.append("<td class='text-center'>").append(formattedEventTime).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getEvent().getEventName()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getGuests()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getTotalCharge()).append("</td>");
            fragment.append("</tr>");
        }

        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    public String generateCarReservationListFragment (List<CarReservationResponse> reservations) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Car Reservations List</h4>");

        fragment.append("<form th:action='@{/api/v1/admin-dashboard/roomReservations}' method='get' class='mb-4'>");
        fragment.append("<div class='row'>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='userSearch' class='form-label'>Search by User:</label>");
        fragment.append("<input type='text' id='userSearch' name='userSearch' class='form-control' placeholder='Enter User ID'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='carSearch' class='form-label'>Search by Car:</label>");
        fragment.append("<input type='text' id='carSearch' name='carSearch' class='form-control' placeholder='Enter Car ID'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-4'>");
        fragment.append("<label for='dateSearch' class='form-label'>Search by Date:</label>");
        fragment.append("<input type='date' id='dateSearch' name='dateSearch' class='form-control'>");
        fragment.append("</div>");
        fragment.append("<div class='col-md-1'>");
        fragment.append("<button type='submit' class='btn btn-primary mt-4'>Search</button>");
        fragment.append("</div>");
        fragment.append("</div>");
        fragment.append("</form>");

        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>ID</th><th>Client</th><th>Date</th><th>Car</th><th>Car Type</th><th>Available Seats</th><th>Number of Passengers</th><th>Total Charge</th></tr></thead>");
        fragment.append("<tbody>");

        for (CarReservationResponse reservation : reservations) {
            fragment.append("<tr class='table-row-hover'>");
            fragment.append("<td class='text-center'>").append(reservation.getId()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getUser().getFirstName()).append(" ").append(reservation.getUser().getLastName()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getReservationDate()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getCar().getModel()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getCar().getCarType()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getCar().getSeats()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getPassengers()).append("</td>");
            fragment.append("<td class='text-center'>").append(reservation.getDailyCharge()).append("</td>");
            fragment.append("</tr>");
        }

        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    public String generateEventListFragment (List<ScreenEventResponse> events) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Events List</h4>");
        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>Event ID</th><th>Name</th><th>Time</th><th>Zone</th></tr></thead>");
        fragment.append("<tbody>");

        for (ScreenEventResponse event : events) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedEventTime = event.getEventTime().format(formatter);

            fragment.append("<tr class='table-row-hover'>");
            fragment.append("<td class='text-center'>").append(event.getId()).append("</td>");
            fragment.append("<td class='text-center'>").append(event.getEventName()).append("</td>");
            fragment.append("<td class='text-center'>").append(formattedEventTime).append("</td>");
            fragment.append("<td class='text-center'>").append(event.getZoneType().getDisplayName()).append("</td>");
            fragment.append("</tr>");
        }

        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    public String generateRequestsListFragment(List<ContactUsResponse> messages) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h4 class='bg-primary text-white p-2'>Clients Message List</h4>");
        fragment.append("<div class='row g-0'>");

        for (ContactUsResponse message : messages) {
            fragment.append("<div class='col-md-4 mt-2'>");
            fragment.append("<div class='message-card message-card-hover h-100'>");

            // Top Div (Image and Name, Phone, Email)
            fragment.append("<div class='row g-0'>");

            // Left Div (Silhouette Avatar)
            fragment.append("<div class='col-md-4 d-flex flex-column align-items-center'>");
            fragment.append("<img src='/img/silluette.png' class='message-img' alt='Avatar'>");
            fragment.append("</div>");

            // Right Div (Name, Phone, Email)
            fragment.append("<div class='col-md-8'>");
            fragment.append("<div class='card-body'>");
            fragment.append("<h5 class='card-title'>").append(message.getFirstName()).append(" ").append(message.getLastName()).append("</h5>");
            fragment.append("<p class='card-text'>Phone: ").append(message.getPhoneNumber()).append("</p>");
            fragment.append("<p class='card-text'>Email: ").append(message.getEmail()).append("</p>");
            fragment.append("</div>");
            fragment.append("</div>");

            fragment.append("</div>");

            // Bottom Div (Message)
            fragment.append("<div class='message-label mt-3'>Message</div>");
            fragment.append("<div class='card-body'>");
            fragment.append("<p class='card-text'>").append(message.getMessage()).append("</p>");
            fragment.append("</div>");

            fragment.append("</div>");
            fragment.append("</div>");
        }
        fragment.append("</div>");
        return fragment.toString();
    }




}
