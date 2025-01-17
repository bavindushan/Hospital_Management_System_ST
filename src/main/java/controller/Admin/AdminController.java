package controller.Admin;

import db.DBConnection;
import model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController implements AdminServices{
    @Override
    public boolean addAdmin(Admin admin) throws SQLException {
        String SQL = "INSERT INTO admin (password,name,email) VALUES (?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, admin.getAdminPassword());
        preparedStatement.setString(2, admin.getAdminName());
        preparedStatement.setString(3, admin.getAdminEmail());
        int affectedRows = preparedStatement.executeUpdate();

        return affectedRows>0;
    }

    @Override
    public Admin searchAdmin(String email) {
        return null;
    }

    @Override
    public boolean updateAdmin(String password, String name, String email) {
        return false;
    }

    @Override
    public boolean deleteAdmin(String email) {
        return false;
    }

    @Override
    public List<Admin> getAllAdmin() {
        ArrayList<Admin> adminArrayList=new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Admin");

            while(resultSet.next()){
                Admin admin = new Admin(//continue this);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
