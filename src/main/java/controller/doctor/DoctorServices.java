package controller.doctor;

import model.Admin;

import java.sql.SQLException;
import java.util.List;

public interface DoctorServices {
    boolean addAdmin(Admin admin) throws SQLException;
    Admin searchAdmin(String email) throws SQLException;
    boolean updateAdmin(Admin admin) throws SQLException;
    boolean deleteAdmin(String email) throws SQLException;
    public String getLastID();
    List<Admin> getAll();
}
