package service.custom.impl;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Appointment;
import service.custom.AppointmentBo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBoImpl implements AppointmentBo {
    @Override
    public boolean addAppointment(Appointment appointment) throws SQLException {
        String checkSQL = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ? AND time = ?";
        String SQL = "INSERT INTO appointments (appointment_id,patient_id,doctor_id,appointment_date,time,status) VALUES (?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();

        try{
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement1 = connection.prepareStatement(checkSQL)){
                preparedStatement1.setString(1,appointment.getDoctorID());
                preparedStatement1.setDate(2,Date.valueOf(appointment.getDate()));
                preparedStatement1.setString(3, appointment.getTime());
                ResultSet resultSet = preparedStatement1.executeQuery();

                resultSet.next();
                int count = resultSet.getInt(1);

                if (count>0){
                    System.out.println("Doctor is Already Booked!");
                    new Alert(Alert.AlertType.ERROR,"Doctor is Already Booked!").show();
                    connection.rollback();
                    return false;
                }
            }

            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
                preparedStatement.setString(1, appointment.getId());
                preparedStatement.setString(2, appointment.getPatientID());
                preparedStatement.setString(3, appointment.getDoctorID());
                preparedStatement.setDate(4, Date.valueOf(appointment.getDate()));
                preparedStatement.setString(5, appointment.getTime());
                preparedStatement.setString(6, appointment.getStatus());

                boolean appointmentAdded = preparedStatement.executeUpdate() > 0;
                if (appointmentAdded){
                    boolean isDoctorUnavailable = new DoctorBoImpl().updateDoctorAvailability(appointment.getDoctorID(), "Unavailable");
                    if (isDoctorUnavailable) {
                        connection.commit();
                        return true;
                    }
                }
            }

        }catch (SQLException e){
            System.out.println("An error occur! "+e.getMessage());
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean UpdateAppointment(String id,LocalDate date,String time,String status) throws SQLException {
        String SQL = "UPDATE appointments SET appointment_date=?,time=?,status=? WHERE appointment_id=? ";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setDate(1,Date.valueOf(date));
        preparedStatement.setString(2,time);
        preparedStatement.setString(3,status);
        preparedStatement.setString(4,id);

        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows>0;
    }

    @Override
    public boolean UpdateStatus(String id,String status) throws SQLException {
        String SQL = "UPDATE appointments SET status=? WHERE appointment_id=? ";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,status);
        preparedStatement.setString(2,id);

        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows>0;
    }

    @Override
    public boolean deleteAppointment(String ID,String doctorID) throws SQLException {
        String SQL = "DELETE FROM appointments WHERE appointment_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1,ID);

            boolean affectedrows = preparedStatement.executeUpdate()>0;
            if (affectedrows){
                boolean isDoctorupdate = new DoctorBoImpl().updateDoctorAvailability(doctorID, "Available");
                if (isDoctorupdate){
                    connection.commit();
                    return true;
                }
            }
        } catch (Exception e) {
            connection.rollback();
            System.out.println("An error occur!"+e.getMessage());
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public Appointment searchAppointment(String ID) throws SQLException {
        String SQL = "SELECT * FROM appointments WHERE appointment_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,ID);
        ResultSet resultSet = preparedStatement.executeQuery();


        if (resultSet.next()){
            java.sql.Date sqlDate = resultSet.getDate(4);

            return new Appointment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    sqlDate != null ? sqlDate.toLocalDate() : null,
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    @Override
    public List<Appointment> getAll() {
        List<Appointment> list = new ArrayList<>();
        String SQL = "SELECT * FROM appointments";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);

            while(resultSet.next()){
                Appointment appointment = new Appointment(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
                list.add(appointment);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
        return null;
    }

    @Override
    public String getLastID() {
        List<Appointment> all = getAll();

        if (all.isEmpty()) return null;

        Appointment appointment = all.get(all.size()-1);
        return appointment.getId();
    }
}
