package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.room.FacilityType;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.service.RoomService;
import chechi.nino.bootcamp.service.UserService;
import chechi.nino.bootcamp.util.HtmlFragmentGenerator;
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
    private final HtmlFragmentGenerator fragmentGenerator;

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

    @GetMapping("/users")
    public String showUsers(Model model) {
        // Fetch users from your service
        List<UserResponse> users = userService.findAllUsers();
        model.addAttribute("usersFragment", fragmentGenerator.generateUserListFragment(users));
        model.addAttribute("showUsers", true);
        model.addAttribute("showRooms", false); // Hide the rooms section
        return "admin-dashboard";
    }

    @GetMapping("/rooms")
    public String showRooms(Model model) {
        // Fetch rooms from your service
        List<RoomResponse> rooms = roomService.getAllRoomResponses();
        model.addAttribute("roomsFragment", fragmentGenerator.generateRoomListFragment(rooms));
        model.addAttribute("showUsers", false); // Hide the users section
        model.addAttribute("showRooms", true);
        return "admin-dashboard";
    }

}
