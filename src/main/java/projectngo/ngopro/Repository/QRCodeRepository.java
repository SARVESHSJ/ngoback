package projectngo.ngopro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectngo.ngopro.Entity.QRCode;

import java.util.Optional;

public interface QRCodeRepository extends JpaRepository<QRCode,Long> {
    Optional<QRCode> findByParticipantName(String participantName);
}
