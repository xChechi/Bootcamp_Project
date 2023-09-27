package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.security.AuthResponse;
import chechi.nino.bootcamp.dto.user.UserRequest;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.security.AuthenticationService;
import chechi.nino.bootcamp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/register")
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public String showRegisterPage (Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(UserRequest request, Model model) {
        AuthResponse authResponse = authenticationService.register(request);
        if (authResponse != null && authResponse.getToken() != null) {
            model.addAttribute("jwtToken", authResponse.getToken());
            model.addAttribute("showSuccess", true);
            model.addAttribute("user", new User());
        } else {
            // Handle registration failure, e.g., return an error message
            model.addAttribute("registrationError", "Registration failed");
        }
        return "register";
    }

}
