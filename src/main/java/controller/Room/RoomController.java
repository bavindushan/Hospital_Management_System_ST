package controller.Room;

import db.DBConnection;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomController implements RoomServices{
    @Override
    public boolean addRoom(Room room) throws SQLException {
        // Insert a new room into roommanagement
        String SQL = "INSERT INTO roommanagement (room_id, patient_id, room_type) VALUES (?, ?, ?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, room.getId());        // room_id
        preparedStatement.setString(2, room.getPatientId()); // patient_id
        preparedStatement.setString(3, room.getType());      // room_type

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows > 0) {
            updateBedsCount(room.getType(), -1);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRoom(Room room) throws SQLException {

        String SQL = "UPDATE roommanagement SET patient_id = ?, room_type = ? WHERE room_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, room.getPatientId()); // Update patient_id
        preparedStatement.setString(2, room.getType());      // Update room_type
        preparedStatement.setString(3, room.getId());        // Identify the room by room_id

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows>0;
    }

    @Override
    public boolean deleteRom(String id,String roomType) throws SQLException {
        String SQL = "DELETE FROM roommanagement WHERE room_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,id);
        int affectedrows = preparedStatement.executeUpdate();
        updateBedsCount(roomType,1);
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
                resultSet.getString(4)
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
                    resultSet.getString(4)
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

    public int availableBedCount(String roomType) throws SQLException {
        String SQL = "SELECT * FROM RoomStatistics WHERE room_type=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,roomType);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return resultSet.getInt("available_beds");
        return 0;
    }
    private void updateBedsCount(String roomType,int number){
        String SQL = "UPDATE RoomStatistics SET available_beds = available_beds + ? WHERE room_type=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1,number);
            preparedStatement.setString(2,roomType);
            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0 ) System.out.println("Beds count updated successfully for room type: " + roomType);
            else System.out.println("No rows update!! " + roomType);

        } catch (SQLException e) {
            System.out.println("An error occur!!"+e.getMessage());
        }
    }
}
