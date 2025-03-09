package service.custom.impl;

import db.DBConnection;
import model.Report;
import service.custom.ReportBo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportBoImpl implements ReportBo {
    @Override
    public boolean add(Report report) throws SQLException {
        String SQL = "INSERT INTO reports(report_id,admin_id,report_type,generated_date,exported_format) VALUES(?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,report.getId());
        preparedStatement.setString(2,report.getAdminId());
        preparedStatement.setString(3,report.getType());
        preparedStatement.setDate(4, Date.valueOf(report.getDate()));
        preparedStatement.setString(5,report.getFormat());
        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows>0;
    }

    @Override
    public List<Report> getAll() throws SQLException {
        List<Report> list = new ArrayList<>();
        String SQL = "SELECT * FROM reports";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);

        while(resultSet.next()){
            Report report = new Report(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getString(5)
            );
            list.add(report);
        }
        return list;
    }

    @Override
    public String getLastId() {
        String SQL = "SELECT report_id FROM reports ORDER BY report_id DESC LIMIT 1;";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            if (resultSet.next()){
                return resultSet.getString(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching last report ID: " + e.getMessage(), e);
        }
        return null;
    }

}
