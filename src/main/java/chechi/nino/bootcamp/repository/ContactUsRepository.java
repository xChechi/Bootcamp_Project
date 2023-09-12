package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.contact.ContactUsForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<ContactUsForm, Integer> {
}
