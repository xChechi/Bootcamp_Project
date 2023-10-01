package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.security.AuthResponse;
import chechi.nino.bootcamp.dto.security.LoginRequest;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.security.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;

@Controller
@RequestMapping("/api/v1/login")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public String login (Model model) {
        model.addAttribute("loginRequest", new LoginRequest()); // Initialize loginRequest for the form
        return "login";
    }

    @PostMapping
    //public String login(LoginRequest request, Model model, HttpServletResponse response) {
    public String login (LoginRequest request, Model model, HttpServletRequest httpServletRequest) {

        AuthResponse authResponse = authenticationService.login(request);


        if (authResponse != null && authResponse.getToken() != null) {

            httpServletRequest.getSession().setAttribute("jwtToken", authResponse.getToken());

            model.addAttribute("jwtToken", authResponse.getToken());
            model.addAttribute("showSuccess", true);
            model.addAttribute("user", new User());

            System.out.println("---> Stored JWT Token: " + authResponse.getToken());
            System.out.println("---> Session saved token " + httpServletRequest.getSession().getAttribute("jwtToken"));

            return "redirect:/api/v1/demo";
        } else {
            model.addAttribute("loginError", "Invalid credentials");
            return "login";

        }
    }
}
