package service.custom;

import model.assignRoom;

import java.sql.SQLException;
import java.util.List;

public interface RoomBo {
    boolean addRoom(assignRoom assignRoom) throws SQLException;
    boolean updateRoom(assignRoom assignRoom) throws SQLException;
    boolean deleteRom(String id,String roomType) throws SQLException;
    assignRoom searchRom(String id) throws SQLException;
    List<assignRoom> getAll() throws SQLException;
    String lastID() throws SQLException;
}
