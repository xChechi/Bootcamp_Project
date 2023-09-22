package chechi.nino.bootcamp.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/v1/home")
public class HomeController {

    @GetMapping
    public String home () {
        return "home";
    }
}
