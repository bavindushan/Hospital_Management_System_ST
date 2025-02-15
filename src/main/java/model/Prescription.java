package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prescription {
    private String id;
    private String patient_id;
    private String doctor_id;
    private String dosage;
    private String duration;
    private String medicine_details;
    private String additional_notes;
}
