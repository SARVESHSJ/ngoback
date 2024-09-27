package projectngo.ngopro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectngo.ngopro.Entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
