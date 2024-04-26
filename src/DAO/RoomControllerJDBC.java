package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Room;

public class RoomControllerJDBC implements IRoomController{
	Connection connection;
	public RoomControllerJDBC(){
		connection=Connect_db.connect();
	}
	@Override
	public void ajoutRoom(Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Room> getListRooms(int UserID) throws SQLException {
		List<Room> Rooms = new ArrayList<Room>();
		String sql = "SELECT * FROM room r , quiz q where r.id_quiz = q.id and q.id_creator = "+UserID+";";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
        	int id = resultSet.getInt(1);
            String date = resultSet.getString(4);
            System.out.println(id);
            Rooms.add(new Room(id, date));
        }
        return Rooms;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	
	public int verifRoomIfExist(int RoomID) throws SQLException {
		String sql = "select * from room where id = "+RoomID+" ;";
		 PreparedStatement statement = connection.prepareStatement(sql);
		 ResultSet rowsInserted = statement.executeQuery();
           if (rowsInserted.next())
        	   return 1;
           else
        	   return 0;
	}
	
	public int getRoomStatus(int RoomID) throws SQLException {
		String sql = "SELECT * FROM room where id = "+RoomID+" ;";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next())
            return resultSet.getInt(3);
        return 0;
	}
	
	public void ChangeRoomStatus(int RoomID) throws SQLException {
		String sql = "UPDATE room SET status = 1 WHERE id ="+RoomID+" LIMIT 1 ;";
		PreparedStatement statement = connection.prepareStatement(sql);
		int rowsInserted = statement.executeUpdate();
	}
	
	public void CreateRoom(int RoomID , int QuizID) throws SQLException {
		String sql = "INSERT INTO room VALUES ("+RoomID+" , "+QuizID+",0, now()) ;";
		PreparedStatement statement = connection.prepareStatement(sql);
		int rowsInserted = statement.executeUpdate();
	}
	@Override
	public List<Room> getListRooms() {
		// TODO Auto-generated method stub
		return null;
	}

}
