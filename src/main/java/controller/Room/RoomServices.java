package controller.Room;

import model.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomServices {
    boolean addRoom(Room room) throws SQLException;
    boolean updateRoom(Room room) throws SQLException;
    boolean deleteRom(String id) throws SQLException;
    Room searchRom(String id) throws SQLException;
    List<Room> getAll() throws SQLException;
    String lastID() throws SQLException;
}
