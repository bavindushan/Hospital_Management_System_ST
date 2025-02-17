package service.custom.impl;

import db.DBConnection;
import model.Report;
import service.custom.ReportBo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
