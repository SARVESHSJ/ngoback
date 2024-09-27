package projectngo.ngopro.Entity;

import jakarta.persistence.*;

@Entity
public class Certificate {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "participantName", unique = true)
    private String participantName;
    private String certificateUrl;  // URL of the certificate in AWS


    // Getters and Setters
    public Long getId() { return id; }
    public String getParticipantName() { return participantName; }
    public void setParticipantName(String participantName) { this.participantName = participantName; }
    public String getCertificateUrl() { return certificateUrl; }
    public void setCertificateUrl(String certificateUrl) { this.certificateUrl = certificateUrl; }

}
