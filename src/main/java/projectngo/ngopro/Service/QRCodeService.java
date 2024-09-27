package projectngo.ngopro.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectngo.ngopro.Entity.QRCode;
import projectngo.ngopro.Repository.QRCodeRepository;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class QRCodeService {
    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private S3Client s3Client;

    private String bucketName = "ngo-project";

    public String generateQRCode(String url, String outputFilePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 300, 300);
        Path path = Paths.get(outputFilePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        return outputFilePath;
    }

    public String uploadQRCodeToS3(File qrCodeFile, String participantName) throws IOException {
        String fileName = participantName + "-qrcode.png";
        return uploadToS3(qrCodeFile, fileName);
    }

    private String uploadToS3(File file, String fileName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, file.toPath());

        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
    }

    public void saveQRCode(String participantName, String qrCodeUrl) {
        QRCode qrCode = new QRCode();
        qrCode.setParticipantName(participantName);
        qrCode.setQrCodeUrl(qrCodeUrl);
        qrCodeRepository.save(qrCode);
    }
}
