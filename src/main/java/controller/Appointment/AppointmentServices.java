package controller.Appointment;

import model.Appointment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentServices {
    boolean addAppointment(Appointment appointment) throws SQLException;
    boolean UpdateAppointment(String id,LocalDate date, String time,String status) throws SQLException;
    boolean UpdateStatus(String id,String status) throws SQLException;
    boolean deleteAppointment(String ID,String doctorID) throws SQLException;
    Appointment searchAppointment(String ID) throws SQLException;
    List<Appointment> getAll();
    String getLastID();
}
