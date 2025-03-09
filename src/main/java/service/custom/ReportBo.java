package service.custom;

import model.Report;

import java.sql.SQLException;
import java.util.List;

public interface ReportBo {
    boolean add(Report report) throws SQLException;
    List<Report> getAll() throws SQLException;
    String getLastId();
}
