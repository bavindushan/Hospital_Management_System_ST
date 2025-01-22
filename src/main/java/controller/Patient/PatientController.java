package controller.Patient;

import db.DBConnection;
import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PatientController implements PatientServices {

    @Override
    public boolean addPatient(Patient patient) throws SQLException {
        String SQL = "INSERT INTO patient (patient_id,,name,age,gender,contact_number,medical_history) VALUES (?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, patient.getID());
        preparedStatement.setString(2, patient.getName());
        preparedStatement.setString(3, patient.getAge());
        preparedStatement.setString(4, patient.getGender());
        preparedStatement.setString(5, patient.getTelNo());
        preparedStatement.setString(6, patient.getMedicalHistory());

        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows >0;
    }

    @Override
    public Patient searchPatient(String TelNo) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient WHERE contact_number=" + "'" + TelNo + "'");
        resultSet.next();

        return new Patient(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6)
        );
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return false;
    }

    @Override
    public boolean deletePatient(String TelNo) {
        return false;
    }

    @Override
    public List<Patient> getAll() {
        return List.of();
    }

    @Override
    public String getLastID() {
        return "";
    }
}
