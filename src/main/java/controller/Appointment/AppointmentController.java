package controller.Appointment;

import controller.doctor.DoctorController;
import db.DBConnection;
import model.Appointment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AppointmentController implements AppointmentServices{
    @Override
    public boolean addAppointment(Appointment appointment) throws SQLException {
        String SQL = "INSERT INTO appointments (appointment_id,patient_id,doctor_id,appointment_date,time,status) VALUES ?,?,?,?,?,?";
        Connection connection = DBConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, appointment.getId());
            preparedStatement.setString(2, appointment.getPatientID());
            preparedStatement.setString(3, appointment.getDoctorID());
            preparedStatement.setDate(4, Date.valueOf(appointment.getDate()));
            preparedStatement.setString(5, appointment.getTime());
            preparedStatement.setString(6, appointment.getStatus());

            boolean appointmentAdded = preparedStatement.executeUpdate() > 0;
            if (appointmentAdded){
                boolean isDoctorUnavailable = new DoctorController().updateDoctorAvailability(appointment.getDoctorID(), "Unavailable");
                if (isDoctorUnavailable) {
                    connection.commit();
                    return true;
                }
            }
        }finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    @Override
    public boolean UpdateAppointment(Appointment appointment) {
        return false;
    }

    @Override
    public boolean deleteAppointment(String ID) {
        return false;
    }

    @Override
    public Appointment searchAppointment(String ID) {
        return null;
    }

    @Override
    public List<Appointment> getAll() {
        return List.of();
    }

    @Override
    public String getLastID() {
        return "";
    }
}
