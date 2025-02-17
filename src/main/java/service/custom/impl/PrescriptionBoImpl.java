package service.custom.impl;

import db.DBConnection;
import model.Prescription;
import service.custom.PrescriptionBo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionBoImpl implements PrescriptionBo {
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
    public boolean update(Prescription prescription) throws SQLException {
        String SQL = "UPDATE prescription SET patient_id=?,doctor_id=?,dosage=?,duration=?,medicine_details=?,additional_notes=? WHERE prescription_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,prescription.getPatient_id());
        preparedStatement.setString(2,prescription.getDoctor_id());
        preparedStatement.setString(3,prescription.getDosage());
        preparedStatement.setString(4,prescription.getDuration());
        preparedStatement.setString(5,prescription.getMedicine_details());

        //null handling in "additional notes"
        if (prescription.getAdditional_notes() != null) {
            preparedStatement.setString(6, prescription.getAdditional_notes());
        } else {
            preparedStatement.setNull(6, Types.VARCHAR); // Safe way to insert NULL
        }

        preparedStatement.setString(7,prescription.getId());

        int affectedrows = preparedStatement.executeUpdate();
        //System.out.println("Affectedrows"+affectedrows);
        return affectedrows>0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String SQL = "DELETE FROM prescription WHERE prescription_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,id);
        int affectedrows = preparedStatement.executeUpdate();

        return affectedrows>0;
    }

    @Override
    public Prescription search(String id) throws SQLException {
        String SQL = "SELECT * FROM prescription WHERE prescription_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return new Prescription(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
                    );
        }
        return null;
    }

    @Override
    public List<Prescription> getAll() {
        List<Prescription> list = new ArrayList<>();
        String SQL = "SELECT * FROM prescription";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);

            while(resultSet.next()){
                Prescription prescription = new Prescription(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                list.add(prescription);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
        return null;
    }

    @Override
    public String getLastId() {
        List<Prescription> all = getAll();

        Prescription prescription = all.get(all.size() - 1);
        return prescription.getId();
    }
}
