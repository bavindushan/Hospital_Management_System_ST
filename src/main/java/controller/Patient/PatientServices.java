package controller.Patient;

import model.Patient;

import java.util.List;

public interface PatientServices {
    boolean addPatient(Patient patient);
    Patient searchPatient(String TelNo);
    boolean updatePatient(Patient patient);
    boolean deletePatient(String TelNo);
    List<Patient> getAll();
    String getLastID();
}
