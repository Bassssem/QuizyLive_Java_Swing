package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.IRoomController;
import Model.Room;

public class RoomController  implements IRoomController {
	private List<Room> ListRoom ;
	
	public RoomController(){
		ListRoom = new  ArrayList<Room>();
	}
	public void ajoutRoom(Room room){
		ListRoom.add(room);
	}

	public List<Room> getListRooms() {
		return ListRoom;
	}
	public void deleteAll() {
		ListRoom.clear();
	}
	@Override
	public List<Room> getListRooms(int UserID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
