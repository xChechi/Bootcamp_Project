package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.contact.ContactUsRequest;
import chechi.nino.bootcamp.entity.contact.ContactUsForm;

import java.util.List;

public interface ContactUsService {

    void saveMessage (ContactUsRequest request);

    List<ContactUsForm> getAllMessages ();

}
