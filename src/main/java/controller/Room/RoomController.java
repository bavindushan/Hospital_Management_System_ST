package controller.Room;

import db.DBConnection;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomController implements RoomServices{
    @Override
    public boolean addRoom(Room room) throws SQLException {
        String SQL = "INSERT INTO roommanagement (room_id,patient_id,room_type,available_beds,beds_count) VALUES (?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,room.getId());
        preparedStatement.setString(2, room.getPatientId());
        preparedStatement.setString(3, room.getType());
        preparedStatement.setString(4, room.getAvailableBeds());
        preparedStatement.setString(5, room.getBedsCount());

        int affctedRows = preparedStatement.executeUpdate();
        return affctedRows>0;
    }

    @Override
    public boolean updateRoom(Room room) throws SQLException {

        String SQL = "UPDATE roommanagement SET room_id=?,patient_id=?,room_type=?,available_beds=?,beds_count=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,room.getId());
        preparedStatement.setString(2, room.getPatientId());
        preparedStatement.setString(3, room.getType());
        preparedStatement.setString(4, room.getAvailableBeds());
        preparedStatement.setString(5, room.getBedsCount());

        int affctedRows = preparedStatement.executeUpdate();
        return affctedRows>0;
    }

    @Override
    public boolean deleteRom(String id) throws SQLException {
        String SQL = "DELETE FROM roommanagement WHERE room_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,id);
        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows>0;
    }

    @Override
    public Room searchRom(String id) throws SQLException {
        String SQL = "SELECT * FROM roommanagement WHERE room_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new Room(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5)
        );
    }

    @Override
    public List<Room> getAll() throws SQLException {
        ArrayList<Room> list = new ArrayList<>();

        String SQL = "SELECT * FROM roommanagement";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);

        while(resultSet.next()){
            Room room = new Room(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            list.add(room);
        }
        return list;
    }

    @Override
    public String lastID() throws SQLException {
        List<Room> list = getAll();
        if (list.isEmpty()) return null;
        Room room = list.get(list.size() - 1);
        return room.getId();
    }
}
