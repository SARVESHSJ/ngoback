package projectngo.ngopro.Controller;


import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projectngo.ngopro.Entity.Certificate;
import projectngo.ngopro.Entity.Contact;
import projectngo.ngopro.Repository.ContactRepository;
import projectngo.ngopro.Service.CertificateService;
import projectngo.ngopro.Service.QRCodeService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CertificateService certificateService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/dashboard")
    public String adminDashboard() {
        // This will only be accessible if the user is authenticated
        return "Admin Dashboard Access Granted";
    }

    @PostMapping("/uploadCertificate")
    public ResponseEntity<String> uploadCertificate(@RequestParam("participantName") String participantName,
                                                    @RequestParam("certificate") MultipartFile certificateFile) {
        try {
            File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + certificateFile.getOriginalFilename());
            certificateFile.transferTo(tempFile);

            String certificateUrl = certificateService.uploadCertificateToS3(tempFile, participantName);

            certificateService.saveCertificate(participantName,certificateUrl);

            return ResponseEntity.ok("Certificate uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading certificate.");
        }
    }

    @PostMapping("/generateQRCode")
    public ResponseEntity<String> generateQRCode(@RequestBody Map<String, String> request) {
        String participantName = request.get("participantName");
        try {
            String qrCodeUrl = "http://localhost:5173/certificates/" + participantName;
            String qrFilePath = System.getProperty("java.io.tmpdir") + "/" + participantName + "-qrcode.png";


            qrCodeService.generateQRCode(qrCodeUrl, qrFilePath);
            File qrCodeFile = new File(qrFilePath);


            String qrCodeS3Url = qrCodeService.uploadQRCodeToS3(qrCodeFile, participantName);


            // Save QR code details to the database
            qrCodeService.saveQRCode(participantName,qrCodeS3Url);

            return ResponseEntity.ok("QR Code generated and uploaded successfully. QR Code URL: " + qrCodeS3Url);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating QR Code.");
        }
    }


    @GetMapping("/contact/all")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }





}
