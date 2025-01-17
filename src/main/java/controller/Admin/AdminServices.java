package controller.Admin;

import model.Admin;

import java.util.List;

public interface AdminServices {
    boolean addAdmin(Admin admin);
    Admin searchAdmin(String email);
    boolean updateAdmin(String password,String name,String email);
    boolean deleteAdmin(String email);
    List<Admin> getAllAdmin();
}
