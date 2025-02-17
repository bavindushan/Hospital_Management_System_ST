package controller.Report;

import model.Report;

import java.sql.SQLException;

public interface ReportServices {
    boolean add(Report report) throws SQLException;

}
