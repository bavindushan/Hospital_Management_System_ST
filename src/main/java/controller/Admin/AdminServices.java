package controller.Admin;

import model.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminServices {
    boolean addAdmin(Admin admin) throws SQLException;
    Admin searchAdmin(String email) throws SQLException;
    boolean updateAdmin(Admin admin) throws SQLException;
    boolean deleteAdmin(String email) throws SQLException;
    List<Admin> getAllAdmin();
}
