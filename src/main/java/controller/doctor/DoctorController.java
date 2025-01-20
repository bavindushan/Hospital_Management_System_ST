package controller.doctor;

import model.Admin;

import java.sql.SQLException;
import java.util.List;

public class DoctorController implements DoctorServices{
    @Override
    public boolean addAdmin(Admin admin) throws SQLException {
        return false;
    }

    @Override
    public Admin searchAdmin(String email) throws SQLException {
        return null;
    }

    @Override
    public boolean updateAdmin(Admin admin) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteAdmin(String email) throws SQLException {
        return false;
    }

    @Override
    public String getLastID() {
        return "";
    }

    @Override
    public List<Admin> getAll() {
        return List.of();
    }
}
