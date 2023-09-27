package chechi.nino.bootcamp.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/forgotten-password")
public class ForgottenPasswordController {

    @GetMapping
    public String forgottenPassword () { return "forgotten-password";}
}
