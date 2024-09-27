package projectngo.ngopro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectngo.ngopro.Entity.Certificate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Long> {
    List<Certificate> findByParticipantName(String participantName);

}

