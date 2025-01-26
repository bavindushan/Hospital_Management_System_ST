package controller.Room;

import db.DBConnection;
import model.assignRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomController implements RoomServices{
    @Override
    public boolean addRoom(assignRoom assignRoom) throws SQLException {
        // Insert a new room into roommanagement
        String SQL = "INSERT INTO roommanagement (room_id, patient_id, room_type) VALUES (?, ?, ?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, assignRoom.getId());        // room_id
        preparedStatement.setString(2, assignRoom.getPatientId()); // patient_id
        preparedStatement.setString(3, assignRoom.getType());      // room_type

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows > 0) {
            updateBedsCount(assignRoom.getType(), -1);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRoom(assignRoom assignRoom) throws SQLException {

        String SQL = "UPDATE roommanagement SET patient_id = ?, room_type = ? WHERE room_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, assignRoom.getPatientId()); // Update patient_id
        preparedStatement.setString(2, assignRoom.getType());      // Update room_type
        preparedStatement.setString(3, assignRoom.getId());        // Identify the room by room_id

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
    public assignRoom searchRom(String id) throws SQLException {
        String SQL = "SELECT * FROM roommanagement WHERE room_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new assignRoom(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3)
        );
    }

    @Override
    public List<assignRoom> getAll() throws SQLException {
        ArrayList<assignRoom> list = new ArrayList<>();

        String SQL = "SELECT * FROM roommanagement";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);

        while(resultSet.next()){
            assignRoom assignRoom = new assignRoom(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );

            list.add(assignRoom);
        }
        return list;
    }

    @Override
    public String lastID() throws SQLException {
        List<assignRoom> list = getAll();
        if (list.isEmpty()) return null;
        assignRoom assignRoom = list.get(list.size() - 1);
        return assignRoom.getId();
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
