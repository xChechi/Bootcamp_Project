package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.entity.user.Role;
import chechi.nino.bootcamp.repository.UserRepository;
import chechi.nino.bootcamp.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/api/v1/demo")
@AllArgsConstructor
public class DemoController {

    private final String SECURED_API_URL = "http://localhost:3000/api/v1/demo"; // Replace this with your secured API endpoint URL
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @GetMapping
    public String secureEndpoint(Model model, HttpServletRequest httpServletRequest) {

        String jwtToken = (String) httpServletRequest.getSession().getAttribute("jwtToken");

        System.out.println("Retrieved JWT Token: " + jwtToken);

        if (jwtToken != null) {

            String authorizationHeader = "Bearer " + jwtToken;
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationHeader);

            String responseData = restTemplate.getForObject(SECURED_API_URL, String.class, headers);

            model.addAttribute("data", responseData);

            var email = jwtService.extractUsername(jwtToken);
            var user = userRepository.findByEmail(email);

            if (user.filter(f -> f.getRole().equals(Role.ADMIN)).isPresent()) {
                return "redirect:/api/v1/admin-dashboard/dashboard";
            } else if (user.filter(f -> f.getRole().equals(Role.USER)).isPresent()) {
                return "my-dashboard";
            } else
                return "demo";

        } else {
            return "redirect:/api/v1/login";
        }
    }
}
