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
    private String id;
    private String name;
    private String specialty;
    private String telNo;
    private String qualifications;
    private String availability;
    private String email;
    private String password;
}
