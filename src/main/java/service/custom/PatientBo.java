package service.custom;

import model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientBo {
    boolean addPatient(Patient patient) throws SQLException;
    Patient searchPatient(String TelNo) throws SQLException;
    boolean updatePatient(Patient patient) throws SQLException;
    boolean deletePatient(String TelNo) throws SQLException;
    List<Patient> getAll() throws SQLException;
    String getLastID() throws SQLException;
}
