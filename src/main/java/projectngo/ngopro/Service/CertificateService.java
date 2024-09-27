package projectngo.ngopro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectngo.ngopro.Entity.Certificate;
import projectngo.ngopro.Repository.CertificateRepository;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private S3Client s3Client;

    private String bucketName = "ngo-project";

    public void saveCertificate(String participantName, String certificateUrl) {
        Certificate certificate = new Certificate();
        certificate.setParticipantName(participantName);
        certificate.setCertificateUrl(certificateUrl);
        certificateRepository.save(certificate);
    }


    public String uploadCertificateToS3(File certificateFile, String participantName) throws IOException {
        String fileName = participantName + "-certificate.png";
        return uploadToS3(certificateFile, fileName);
    }

    public String uploadToS3(File file, String fileName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, file.toPath());

        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
    }





    public List<Certificate> findCertificateByParticipantName(String participantName) {
        return certificateRepository.findByParticipantName(participantName);
    }

}











