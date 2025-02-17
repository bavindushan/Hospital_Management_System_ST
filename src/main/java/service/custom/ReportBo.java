package service.custom;

import model.Report;

import java.sql.SQLException;

public interface ReportBo {
    boolean add(Report report) throws SQLException;

}
