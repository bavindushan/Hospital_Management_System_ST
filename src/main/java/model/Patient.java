package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {
    private String patientID;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String patientTelNo;
    private String patientMedicalHistory;
    private String patientEmail;
    private String patientPassword;
}
