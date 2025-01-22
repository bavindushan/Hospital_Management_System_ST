package controller.doctor;

import model.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorServices {
    boolean addDoctor(Doctor doctor) throws SQLException;
    Doctor searchDoctor(String email) throws SQLException;
    boolean updateDoctor(Doctor doctor) throws SQLException;
    boolean deleteDoctor(String email) throws SQLException;
    String getLastID() throws SQLException;
    List<Doctor> getAll() throws SQLException;
}
