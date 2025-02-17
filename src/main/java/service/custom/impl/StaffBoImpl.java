package service.custom.impl;

import db.DBConnection;
import model.Staff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StaffBoImpl {

    public List<Staff> getAll(){
        List<Staff> list = new ArrayList<>();
        String SQL = "SELECT * FROM staff";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Staff staff = new Staff(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
                list.add(staff);
            }

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
        return list;
    }
}
