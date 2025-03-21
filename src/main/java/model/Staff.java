package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Staff {
    private String staffID;
    private String staffName;
    private String staffRole;
    private String staffAvailability;
    private String staffEmail;
    private String staffPassword;
}
