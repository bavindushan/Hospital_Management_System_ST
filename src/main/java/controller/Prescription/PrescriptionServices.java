package controller.Prescription;

import model.Prescription;

import java.sql.SQLException;
import java.util.List;

public interface PrescriptionServices {
    boolean add(Prescription prescription) throws SQLException;
    boolean update(Prescription prescription) throws SQLException;
    boolean delete(String id);
    Prescription search(String id);
    List<Prescription> getAll();
    String getLastId();
}
