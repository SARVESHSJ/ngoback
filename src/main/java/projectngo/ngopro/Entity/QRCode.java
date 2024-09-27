package projectngo.ngopro.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String participantName;
    private String qrCodeUrl;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "QRCode{" +
                "id=" + id +
                ", participantName='" + participantName + '\'' +
                ", qrCodeUrl='" + qrCodeUrl + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }
// Getters and Setters
}