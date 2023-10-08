package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.user.ForgottenPasswordRequest;
import chechi.nino.bootcamp.service.UserService;
import chechi.nino.bootcamp.util.EmailUtils;
import chechi.nino.bootcamp.util.PasswordUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/forgotten-password")
@AllArgsConstructor
public class ForgottenPasswordController {

    private final UserService userService;

    @GetMapping
    public String forgottenPassword () { return "forgotten-password";}

    @PostMapping
    public String sendEmail (ForgottenPasswordRequest request, Model model) {

        userService.resetPasswordByEmail(request.getEmail());
        model.addAttribute("message", true);
        return "forgotten-password";
    }
}
