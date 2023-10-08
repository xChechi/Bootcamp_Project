package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.contact.ContactUsRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/contact")
public class ContactController {

    @GetMapping
    public String contact (Model model) {
        model.addAttribute("contactUsRequest", new ContactUsRequest());
        return "contact";
    }


}
