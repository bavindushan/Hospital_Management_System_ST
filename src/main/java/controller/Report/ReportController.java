package controller.Report;

import db.DBConnection;
import model.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportController implements ReportServices{
    @Override
    public boolean add(Report report) throws SQLException {
        String SQL = "INSERT INTO reports(report_id,admin_id,report_type,generated_date,exported_format) VALUES(?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,report.getId());
        preparedStatement.setString(2,report.getAdminId());
        preparedStatement.setString(3,report.getType());
        preparedStatement.setDate(4,report.getDate());
        preparedStatement.setString(5,report.getFormat());
    }

    @Override
    public boolean genarateReport(String type) {
        return false;
    }
}
