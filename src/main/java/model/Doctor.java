package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doctor {
    private String doctorName;
    private String doctorSpecialty;
    private String doctorTelNo;
    private String doctorQualifications;
    private String doctorAvailability;
}
