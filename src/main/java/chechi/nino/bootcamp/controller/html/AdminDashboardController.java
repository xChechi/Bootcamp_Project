package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.room.FacilityType;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.service.RoomService;
import chechi.nino.bootcamp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin-dashboard")
@AllArgsConstructor
public class AdminDashboardController {

    private final String SECURED_API_URL = "http://localhost:3000/api/v1/admin-dashboard";
    private final UserService userService;
    private final RoomService roomService;

    @GetMapping
    public String adminDashboard (Model model, HttpServletRequest httpServletRequest) {

        String jwtToken = (String) httpServletRequest.getSession().getAttribute("jwtToken");

        System.out.println("Retrieved JWT Token: " + jwtToken);

        if (jwtToken != null) {

            String authorizationHeader = "Bearer " + jwtToken;
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationHeader);

            String responseData = restTemplate.getForObject(SECURED_API_URL, String.class, headers);

            model.addAttribute("data", responseData);

            return "admin-dashboard";
        } else return "redirect:/api/v1/demo";
    }

    private String generateUserListFragment(List<UserResponse> users) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h1 class='bg-primary text-white p-2'>Users List</h1>");
        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>ID</th><th>Name</th><th>Phone number</th><th>Email</th></tr></thead>");
        fragment.append("<tbody>");
        for (UserResponse user : users) {
            fragment.append("<tr><td class='text-center'>").append(user.getId()).append("</td>");
            fragment.append("<td>").append(user.getFirstName()).append(" ").append(user.getLastName()).append("</td>");
            fragment.append("<td>").append(user.getPhoneNumber()).append("</td>");
            fragment.append("<td>").append(user.getEmail()).append("</td></tr>");
        }
        fragment.append("</tbody></table>");
        return fragment.toString();
    }

    private String generateRoomListFragment(List<RoomResponse> rooms) {
        StringBuilder fragment = new StringBuilder();
        fragment.append("<h1 class='bg-primary text-white p-2'>Rooms List</h1>");
        fragment.append("<table class='table table-bordered'>");
        fragment.append("<thead class='bg-primary text-center text-white'><tr><th>Room Number</th><th>Room Type</th><th>Room View</th><th>Price</th><th>Size</th><th>Capacity</th><th>Facilities</th></tr></thead>");
        fragment.append("<tbody>");

        for (RoomResponse room : rooms) {
            fragment.append("<tr>");
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

    @GetMapping("/users")
    public String showUsers(Model model) {
        // Fetch users from your service
        List<UserResponse> users = userService.findAllUsers();
        model.addAttribute("usersFragment", generateUserListFragment(users));
        model.addAttribute("showUsers", true);
        model.addAttribute("showRooms", false); // Hide the rooms section
        return "admin-dashboard";
    }

    @GetMapping("/rooms")
    public String showRooms(Model model) {
        // Fetch rooms from your service
        List<RoomResponse> rooms = roomService.getAllRoomResponses();
        model.addAttribute("roomsFragment", generateRoomListFragment(rooms));
        model.addAttribute("showUsers", false); // Hide the users section
        model.addAttribute("showRooms", true);
        return "admin-dashboard";
    }



}
