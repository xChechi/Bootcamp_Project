package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.contact.ContactUsRequest;
import chechi.nino.bootcamp.entity.contact.ContactUsForm;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.exception.UserNotFoundException;
import chechi.nino.bootcamp.repository.ContactUsRepository;
import chechi.nino.bootcamp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ContactUsConverter {

    public ContactUsForm createMessage (ContactUsRequest request) {

        return ContactUsForm.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .message(request.getMessage())
                .build();
    }
}
