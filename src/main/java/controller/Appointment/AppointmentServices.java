package controller.Appointment;

import model.Appointment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentServices {
    boolean addAppointment(Appointment appointment) throws SQLException;
    boolean UpdateAppointment(String id,LocalDate date, String time,String status) throws SQLException;
    boolean deleteAppointment(String ID);
    Appointment searchAppointment(String ID);
    List<Appointment> getAll();
    String getLastID();
}
