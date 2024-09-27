package projectngo.ngopro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import projectngo.ngopro.Entity.Certificate;
import projectngo.ngopro.Service.CertificateService;

import java.util.List;

@RestController
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/certificates/{participantName}")
    public ResponseEntity<List<Certificate>> getCertificate(@PathVariable String participantName) {
        List<Certificate> certificateOptional = certificateService.findCertificateByParticipantName(participantName);
        if (certificateOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        } else {
            return ResponseEntity.ok(certificateOptional);
        }
    }
}
