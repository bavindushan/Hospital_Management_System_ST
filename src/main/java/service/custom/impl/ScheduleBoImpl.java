package service.custom.impl;

import db.DBConnection;
import model.Schedule;
import service.custom.ScheduleBo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleBoImpl implements ScheduleBo {
    @Override
    public boolean assignSchedule(Schedule schedule) throws SQLException {
        String SQL = "INSERT INTO schedules (schedule_id,doctor_id,staff_id,schedule_details) VALUES (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, schedule.getId());
        preparedStatement.setString(2, schedule.getDoctorID());
        preparedStatement.setString(3, schedule.getStaffID());
        preparedStatement.setString(4, schedule.getScheduleDetails());
        int affectedrows = preparedStatement.executeUpdate();

        return affectedrows>0;
    }

    @Override
    public boolean deleteSchedule(String id) throws SQLException {
        String SQL = "DELETE FROM schedules WHERE schedule_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,id);
        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows>0;
    }

    @Override
    public Schedule searchSchedule(String id) throws SQLException {
        String SQL = "SELECT * FROM schedules WHERE schedule_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean updateSchedule(Schedule schedule) throws SQLException {
        String SQL = "UPDATE schedules SET doctor_id = ?,staff_id = ?,schedule_details = ? WHERE schedule_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, schedule.getDoctorID());
        preparedStatement.setString(2, schedule.getStaffID());
        preparedStatement.setString(3, schedule.getScheduleDetails());

        preparedStatement.setString(4, schedule.getId());
        int affectedrows = preparedStatement.executeUpdate();

        return affectedrows>0;
    }

    @Override
    public List<Schedule> getAll() {
        List<Schedule> list = new ArrayList<>();

        String SQL = "SELECT * FROM schedules";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
                Schedule schedule = new Schedule(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                list.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());

        }
        return list;
    }

    @Override
    public String getLastID() {
        List<Schedule> all = getAll();
        Schedule schedule = all.get(all.size() - 1);
        return schedule.getId();
    }
}
