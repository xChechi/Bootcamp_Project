package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.config.ApiUrlProviderConfig;
import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.room.FacilityType;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.service.RoomService;
import chechi.nino.bootcamp.service.UserService;
import chechi.nino.bootcamp.util.HtmlAuthorizeData;
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

    //private final String SECURED_API_URL = "http://localhost:3000/api/v1/admin-dashboard";
    private final UserService userService;
    private final RoomService roomService;
    private final HtmlFragmentGenerator fragmentGenerator;
    private final HtmlAuthorizeData htmlAuthorizeData;


    @GetMapping
    public String adminDashboard (Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        if (responseData != null) {

            model.addAttribute("data", responseData);

            model.addAttribute("dashboardFragment", fragmentGenerator.generateDashboard());
            model.addAttribute("showDashboard", true);
            model.addAttribute("showUsers", false);
            model.addAttribute("showRooms", false);

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }

    @GetMapping("/users")
    public String showUsers(Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        if (responseData != null) {

            List<UserResponse> users = userService.findAllUsers();
            model.addAttribute("usersFragment", fragmentGenerator.generateUserListFragment(users));
            model.addAttribute("showUsers", true);
            model.addAttribute("showRooms", false); // Hide the rooms section

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }

    @GetMapping("/rooms")
    public String showRooms(Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        if (responseData != null) {

            List<RoomResponse> rooms = roomService.getAllRoomResponses();
            model.addAttribute("roomsFragment", fragmentGenerator.generateRoomListFragment(rooms));
            model.addAttribute("showUsers", false); // Hide the users section
            model.addAttribute("showRooms", true);

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }

    @GetMapping("/dashboard")
    public String showDashboard (Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        if (responseData != null) {

            model.addAttribute("dashboardFragment", fragmentGenerator.generateDashboard());
            model.addAttribute("showDashboard", true);
            model.addAttribute("showUsers", false); // Hide the users section
            model.addAttribute("showRooms", false);

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }
}
