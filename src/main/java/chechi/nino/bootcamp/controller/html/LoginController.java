package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.security.AuthResponse;
import chechi.nino.bootcamp.dto.security.LoginRequest;
import chechi.nino.bootcamp.security.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/login")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public String loginPage () {return "login";}

    @PostMapping
    public String login (LoginRequest request, Model model) {
        AuthResponse authResponse = authenticationService.login(request);
        model.addAttribute("jwtToken", authResponse.getToken());
        return "login";
    }
}
