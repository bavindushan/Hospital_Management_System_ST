package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class roomStatic {
    private String type;
    private Integer totalBeds;
    private Integer AvailableBeds;
}
