package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schedule {
    private String id;
    private String doctorID;
    private String staffID;
    private String scheduleDetails;
}
