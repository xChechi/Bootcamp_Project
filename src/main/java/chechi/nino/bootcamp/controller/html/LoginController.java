package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.security.AuthResponse;
import chechi.nino.bootcamp.dto.security.LoginRequest;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.repository.UserRepository;
import chechi.nino.bootcamp.security.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpHeaders;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/api/v1/login")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public String login (Model model) {
        model.addAttribute("loginRequest", new LoginRequest()); // Initialize loginRequest for the form
        return "login";
    }

    @PostMapping
    public String login (@ModelAttribute("loginRequest") @Valid LoginRequest request, Model model, HttpServletRequest httpServletRequest, BindingResult bindingResult) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

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
                    // Handle other authentication failures (if needed)
                    model.addAttribute("data", true);
                    return "login";
                }
            } else {
                // Password doesn't match, return error message
                bindingResult.rejectValue("password", "error.login", "Incorrect password. Please try again.");
                return "login";
            }
        } else {
            // User not found, return error message
            bindingResult.rejectValue("email", "error.login", "User not found.");
            return "login";
        }
    }
}
