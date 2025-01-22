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
    private String ID;
    private String Name;
    private String Age;
    private String Gender;
    private String TelNo;
    private String MedicalHistory;
}
