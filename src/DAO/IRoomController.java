package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Room;

public interface IRoomController {
	public void ajoutRoom(Room room);
	public List<Room> getListRooms();
	public void deleteAll();
	List<Room> getListRooms(int UserID) throws SQLException;
}
