package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.ContactUsConverter;
import chechi.nino.bootcamp.dto.contact.ContactUsRequest;
import chechi.nino.bootcamp.entity.contact.ContactUsForm;
import chechi.nino.bootcamp.repository.ContactUsRepository;
import chechi.nino.bootcamp.service.ContactUsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactUsImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;
    private final ContactUsConverter contactUsConverter;

    @Override
    public void saveMessage(ContactUsRequest request) {
        ContactUsForm message = contactUsConverter.createMessage(request);
        contactUsRepository.save(message);
    }

    @Override
    public List<ContactUsForm> getAllMessages() {
        return contactUsRepository.findAll();
    }
}
