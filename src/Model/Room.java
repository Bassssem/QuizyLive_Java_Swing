package Model;

public class Room {
	private int RoomID;
	private String date;
	public Room(int roomID, String date) {
		super();
		RoomID = roomID;
		this.date = date;
	}
	public int getRoomID() {
		return RoomID;
	}
	public void setRoomID(int roomID) {
		RoomID = roomID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
