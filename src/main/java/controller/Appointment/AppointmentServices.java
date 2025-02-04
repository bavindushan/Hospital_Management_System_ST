package controller.Appointment;

import model.Appointment;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentServices {
    boolean addAppointment(Appointment appointment) throws SQLException;
    boolean UpdateAppointment(Appointment appointment);
    boolean deleteAppointment(String ID);
    Appointment searchAppointment(String ID);
    List<Appointment> getAll();
    String getLastID();
}
