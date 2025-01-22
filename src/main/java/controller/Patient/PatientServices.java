package controller.Patient;

import model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientServices {
    boolean addPatient(Patient patient) throws SQLException;
    Patient searchPatient(String TelNo) throws SQLException;
    boolean updatePatient(Patient patient) throws SQLException;
    boolean deletePatient(String TelNo);
    List<Patient> getAll();
    String getLastID();
}
