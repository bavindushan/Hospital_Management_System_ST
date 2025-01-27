package controller.Schedule;

import model.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleServices {
    boolean assignSchedule(Schedule schedule) throws SQLException;
    boolean deleteSchedule(String id) throws SQLException;
    Schedule searchSchedule(String id) throws SQLException;
    boolean updateSchedule(Schedule schedule) throws SQLException;
    List<Schedule> getAll();
    String getLastID();
}
