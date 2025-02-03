package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {
    private String id;
    private String patientID;
    private String doctorID;
    private LocalDate date;
    private String time;
    private String status;
}
