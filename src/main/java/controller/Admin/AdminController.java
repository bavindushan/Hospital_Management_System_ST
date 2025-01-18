package controller.Admin;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        preparedStatement.setString(1, admin.getAdminID());
        preparedStatement.setString(2, admin.getAdminPassword());
        preparedStatement.setString(3, admin.getAdminName());
        preparedStatement.setString(4, admin.getAdminEmail());
        int affectedRows = preparedStatement.executeUpdate();

        return affectedRows>0;
    }

    @Override
    public Admin searchAdmin(String email) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM  admin WHERE id=" + "'" + email + "'");
        resultSet.next();
        return new Admin(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)
        );
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
                Admin admin = new Admin(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                adminArrayList.add(admin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminArrayList;
    }
    public ObservableList<Object> getAllAdminIds(){
        List<Admin> allAdmin = getAllAdmin();
        ObservableList<Object> adminIdObsavableList = FXCollections.observableArrayList();

        allAdmin.forEach(admin -> adminIdObsavableList.add(admin.getAdminID()));

        return adminIdObsavableList;
    }
    public String getLastAdminID(){
        List<Admin> adminList = getAllAdmin();

        if (adminList.isEmpty()) return null;

        Admin lastAdmin = adminList.get(adminList.size() - 1);
        return lastAdmin.getAdminID();
    }
}
