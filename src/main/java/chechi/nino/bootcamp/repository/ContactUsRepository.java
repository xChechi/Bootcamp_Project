package chechi.nino.bootcamp.repository;

import chechi.nino.bootcamp.entity.contact.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {
}
