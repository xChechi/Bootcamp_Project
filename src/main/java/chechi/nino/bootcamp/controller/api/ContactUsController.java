package chechi.nino.bootcamp.controller.api;

import chechi.nino.bootcamp.dto.contact.ContactUsRequest;
import chechi.nino.bootcamp.entity.contact.ContactUsForm;
import chechi.nino.bootcamp.service.ContactUsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/contact-us")
public class ContactUsController {

    private final ContactUsService contactUsService;

    @GetMapping
    public ResponseEntity<List<ContactUsForm>> viewAllMessages () {

        List<ContactUsForm> messages = contactUsService.getAllMessages();
        return ResponseEntity.status(HttpStatus.FOUND).body(messages);
    }

    @PostMapping
    public ResponseEntity<String> createMessage (@Valid @RequestBody ContactUsRequest request) {

        contactUsService.saveMessage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message sent");
    }

}
