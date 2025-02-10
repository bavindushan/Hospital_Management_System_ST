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
public class PaymentBill {
    private String id;
    private String patientID;
    private Double total;
    private String status;
    private String invoiceName;
    private LocalDate date;
}
