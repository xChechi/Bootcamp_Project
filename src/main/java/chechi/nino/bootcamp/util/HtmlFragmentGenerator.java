package chechi.nino.bootcamp.util;

import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.room.FacilityType;
import org.springframework.stereotype.Component;

import java.util.List;

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
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>Room Number</th><th>Room Type</th><th>Room View</th><th>Price</th><th>Size</th><th>Capacity</th><th>Facilities</th></tr></thead>");
        fragment.append("<tbody>");

        for (RoomResponse room : rooms) {
            fragment.append("<tr class='table-row-hover'>");
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

}
