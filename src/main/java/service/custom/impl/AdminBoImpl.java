package service.custom.impl;

import db.DBConnection;
import model.Admin;
import service.custom.AdminBo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminBoImpl implements AdminBo {
    @Override
    public boolean addAdmin(Admin admin) throws SQLException {
        String SQL = "INSERT INTO admin (admin_id,password,name,email) VALUES (?,?,?,?)";
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
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM  admin WHERE email=" + "'" + email + "'");
        resultSet.next();
        return new Admin(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)
        );
    }

    @Override
    public boolean updateAdmin(Admin admin) throws SQLException {
        String SQL = "UPDATE admin SET password = ?, name = ?, email = ? WHERE admin_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        //Set new values
        preparedStatement.setString(1,admin.getAdminPassword());
        preparedStatement.setString(2,admin.getAdminName());
        preparedStatement.setString(3,admin.getAdminEmail());
        preparedStatement.setString(4,admin.getAdminID());//search condition
        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows > 0;
    }

    @Override
    public boolean deleteAdmin(String email) throws SQLException {
        String SQL = "DELETE FROM admin WHERE  email = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,email);
        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows > 0;
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

    public String getLastAdminID(){
        List<Admin> adminList = getAllAdmin();

        if (adminList.isEmpty()) return null;

        Admin lastAdmin = adminList.get(adminList.size() - 1);
        return lastAdmin.getAdminID();
    }
}
