package chechi.nino.bootcamp.controller.html;

import chechi.nino.bootcamp.dto.contact.ContactUsRequest;
import chechi.nino.bootcamp.service.ContactUsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/contact")
@AllArgsConstructor
public class ContactController {

    private final ContactUsService contactUsService;

    @GetMapping
    public String contact (Model model) {
        model.addAttribute("contactUsRequest", new ContactUsRequest());
        return "contact";
    }

    @PostMapping
    public String sendMessage (ContactUsRequest request, Model model) {
        contactUsService.saveMessage(request);
        model.addAttribute("message", true);
        return "contact";
    }


}
