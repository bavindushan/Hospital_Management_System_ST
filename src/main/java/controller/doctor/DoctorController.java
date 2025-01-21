package controller.doctor;


import db.DBConnection;
import model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorController implements DoctorServices{

    @Override
    public boolean addDoctor(Doctor doctor) throws SQLException {
        String SQL = "INSERT INTO doctor (doctor_id,name,specialty,contact_number,qualifications,availability,email,password) VALUES (?,?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, doctor.getId());
        preparedStatement.setString(2, doctor.getName());
        preparedStatement.setString(3, doctor.getSpecialty());
        preparedStatement.setString(4, doctor.getTelNo());
        preparedStatement.setString(5, doctor.getQualifications());
        preparedStatement.setString(6, doctor.getAvailability());
        preparedStatement.setString(7, doctor.getEmail());
        preparedStatement.setString(8, doctor.getPassword());

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows >0;
    }

    @Override
    public Doctor searchDoctor(String email) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM doctor WHERE email=" + "'" + email + "'");
        resultSet.next();
        return new Doctor(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8)
        );
    }

    @Override
    public boolean updateDoctor(Doctor doctor) throws SQLException {
        String SQL = "UPDATE doctor SET name = ?,specialty = ?,contact_number = ?,qualifications = ?,availability = ?,email = ?,password = ? WHERE doctor_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, doctor.getName());
        preparedStatement.setString(2, doctor.getSpecialty());
        preparedStatement.setString(3, doctor.getTelNo());
        preparedStatement.setString(4, doctor.getQualifications());
        preparedStatement.setString(5, doctor.getAvailability());
        preparedStatement.setString(6, doctor.getEmail());
        preparedStatement.setString(7, doctor.getPassword());

        preparedStatement.setString(8, doctor.getId());
        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows>0;

    }

    @Override
    public boolean deleteDoctor(String email) throws SQLException {
        String SQL = "DELETE FROM doctor WHERE  email = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,email);
        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows>0;

    }

    @Override
    public String getLastID() throws SQLException {
        List<Doctor> doctorList = getAll();

        if (doctorList.isEmpty()) return null;
        Doctor lastdoctor = doctorList.get(doctorList.size() - 1);
        return lastdoctor.getId();
    }

    @Override
    public List<Doctor> getAll() throws SQLException {
        ArrayList<Doctor> doctorList = new ArrayList<>();

        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor");

        while(resultSet.next()){
            Doctor doctor = new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
            doctorList.add(doctor);
        }
        return doctorList;
    }
}
