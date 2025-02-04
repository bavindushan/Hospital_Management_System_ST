package controller.Appointment;

import model.Appointment;

import java.util.List;

public class AppointmentController implements AppointmentServices{
    @Override
    public boolean addAppointment(Appointment appointment) {
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
