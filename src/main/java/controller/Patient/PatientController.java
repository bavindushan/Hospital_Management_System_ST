package controller.Patient;

import model.Patient;

import java.util.List;

public class PatientController implements PatientServices {

    @Override
    public boolean addPatient(Patient patient) {
        return false;
    }

    @Override
    public Patient searchPatient(String TelNo) {
        return null;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return false;
    }

    @Override
    public boolean deletePatient(String TelNo) {
        return false;
    }

    @Override
    public List<Patient> getAll() {
        return List.of();
    }

    @Override
    public String getLastID() {
        return "";
    }
}
