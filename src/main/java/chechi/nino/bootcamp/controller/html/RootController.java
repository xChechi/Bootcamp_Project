package chechi.nino.bootcamp.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/api/v1/home";
    }
}
