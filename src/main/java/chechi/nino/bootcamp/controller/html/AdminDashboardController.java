package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.config.ApiUrlProviderConfig;
import chechi.nino.bootcamp.dto.bar.BarSeatResponse;
import chechi.nino.bootcamp.dto.car.CarResponse;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.room.RoomResponse;
import chechi.nino.bootcamp.dto.table.TableResponse;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.entity.room.FacilityType;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.repository.RestaurantTableRepository;
import chechi.nino.bootcamp.service.*;
import chechi.nino.bootcamp.util.HtmlAuthorizeData;
import chechi.nino.bootcamp.util.HtmlFragmentGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
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
    private final TableService tableService;
    private final BarSeatService seatService;
    private final RoomReservationService roomReservationService;
    private final CarService carService;

    @GetMapping
    public String adminDashboard (Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        if (responseData != null) {

            model.addAttribute("data", responseData);

            model.addAttribute("dashboardFragment", fragmentGenerator.generateDashboard());
            model.addAttribute("showDashboard", true);
            model.addAttribute("showUsers", false);
            model.addAttribute("showRooms", false);
            model.addAttribute("showTables", false);
            model.addAttribute("seatsFragment", false);
            model.addAttribute("ShowRoomReservations", false);
            model.addAttribute("showCars", false);

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
            model.addAttribute("showTables", false);
            model.addAttribute("showDashboard", false);
            model.addAttribute("seatsFragment", false);
            model.addAttribute("ShowRoomReservations", false);
            model.addAttribute("showCars", false);

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
            model.addAttribute("showTables", false);
            model.addAttribute("showDashboard", false);
            model.addAttribute("seatsFragment", false);
            model.addAttribute("ShowRoomReservations", false);
            model.addAttribute("showCars", false);

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
            model.addAttribute("showTables", false);
            model.addAttribute("seatsFragment", false);
            model.addAttribute("ShowRoomReservations", false);
            model.addAttribute("showCars", false);

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }

    @GetMapping("/tables")
    public String showTables (Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        if (responseData != null) {

            List<TableResponse> tables = tableService.getAllTableResponses();
            model.addAttribute("tablesFragment", fragmentGenerator.generateTableListFragment(tables));
            model.addAttribute("showTables", true);
            model.addAttribute("showDashboard", false);
            model.addAttribute("showUsers", false);
            model.addAttribute("showRooms", false);
            model.addAttribute("seatsFragment", false);
            model.addAttribute("ShowRoomReservations", false);
            model.addAttribute("showCars", false);

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }

    @GetMapping("/seats")
    public String showSeats (Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        if (responseData != null) {

            List<BarSeatResponse> seats = seatService.getAllBarSeats();
            model.addAttribute("seatsFragment", fragmentGenerator.generateSeatListFragment(seats));
            model.addAttribute("showSeats", true);
            model.addAttribute("showTables", false);
            model.addAttribute("showDashboard", false);
            model.addAttribute("showUsers", false);
            model.addAttribute("showRooms", false);
            model.addAttribute("ShowRoomReservations", false);
            model.addAttribute("showCars", false);

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }

    @GetMapping("/roomReservations")
    public String showRoomReservations (@RequestParam(name = "userSearch", required = false) Integer userSearch,
                                        @RequestParam(name = "roomSearch", required = false) Integer roomSearch,
                                        @RequestParam(name = "dateSearch", required = false) LocalDate dateSearch,
                                        Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        List<RoomReservationResponse> roomReservations;

        if (responseData != null) {

            if (userSearch != null) {
                roomReservations = roomReservationService.searchByUser(userSearch);
            } else if (roomSearch != null) {
                roomReservations = roomReservationService.searchByRoom(roomSearch);
            } else if (dateSearch != null) {
                LocalDate endDate = dateSearch.plusDays(1);
                roomReservations = roomReservationService.findReservationsWithinPeriod(dateSearch, endDate);
            } else {
                roomReservations = roomReservationService.getAllReservations();
            }

            model.addAttribute("roomReservationsFragment", fragmentGenerator.generateRoomReservationListFragment(roomReservations));
            model.addAttribute("showRoomReservations", true);
            model.addAttribute("showSeats", false);
            model.addAttribute("showTables", false);
            model.addAttribute("showDashboard", false);
            model.addAttribute("showUsers", false);
            model.addAttribute("showRooms", false);
            model.addAttribute("showCars", false);

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }

    @GetMapping("/cars")
    public String showCars (Model model, HttpServletRequest httpServletRequest) {

        String responseData = htmlAuthorizeData.authorizeData(httpServletRequest);
        if (responseData != null) {
            List<CarResponse> cars = carService.getAllCars();
            model.addAttribute("carsFragment", fragmentGenerator.generateCarsFragment(cars));
            model.addAttribute("showCars", true);
            model.addAttribute("showRoomReservations", false);
            model.addAttribute("showSeats", false);
            model.addAttribute("showTables", false);
            model.addAttribute("showDashboard", false);
            model.addAttribute("showUsers", false);
            model.addAttribute("showRooms", false);

            return "admin-dashboard";
        }
        return "redirect:/api/v1/demo";
    }
}
