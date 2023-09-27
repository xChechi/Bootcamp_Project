package chechi.nino.bootcamp.controller.html;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    public String demo() {
        // Check if the user is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // User is authenticated, so they can access the demo page
            return "demo";
        } else {
            // User is not authenticated, so redirect to the login page
            return "redirect:/api/v1/login"; // Replace with your login page URL
        }
    }
}
