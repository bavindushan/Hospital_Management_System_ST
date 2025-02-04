package controller.Appointment;

import model.Appointment;

import java.util.List;

public interface AppointmentServices {
    boolean addAppointment(Appointment appointment);
    boolean UpdateAppointment(Appointment appointment);
    boolean deleteAppointment(String ID);
    Appointment searchAppointment(String ID);
    List<Appointment> getAll();
    String getLastID();
}
