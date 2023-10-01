package chechi.nino.bootcamp.controller.html;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/api/v1/demo")
public class DemoController {

    private final String SECURED_API_URL = "http://localhost:3000/api/v1/demo"; // Replace this with your secured API endpoint URL

    @GetMapping
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
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

            return "demo";
        } else {
            return "redirect:/api/v1/login";
        }
    }
}
