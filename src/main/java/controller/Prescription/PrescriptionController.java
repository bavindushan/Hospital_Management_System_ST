package controller.Prescription;

import db.DBConnection;
import model.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class PrescriptionController implements PrescriptionServices{
    @Override
    public boolean add(Prescription prescription) throws SQLException {
        String SQL = "INSERT INTO prescription(prescription_id,patient_id,doctor_id,dosage,duration,medicine_details,additional_notes) VALUES(?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,prescription.getId());
        preparedStatement.setString(2,prescription.getPatient_id());
        preparedStatement.setString(3,prescription.getDoctor_id());
        preparedStatement.setString(4,prescription.getDosage());
        preparedStatement.setString(5,prescription.getDuration());
        preparedStatement.setString(6,prescription.getMedicine_details());

        //null handling in additional notes
        if (prescription.getAdditional_notes() != null) {
            preparedStatement.setString(7, prescription.getAdditional_notes());
        } else {
            preparedStatement.setNull(7, Types.VARCHAR); // Safe way to insert NULL
        }

        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows>0;
    }

    @Override
    public boolean update(Prescription prescription) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Prescription search(String id) {
        return null;
    }

    @Override
    public List<Prescription> getAll() {
        return List.of();
    }

    @Override
    public String getLastId() {
        return "";
    }
}
