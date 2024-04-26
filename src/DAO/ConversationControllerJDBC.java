package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversationControllerJDBC implements IConversationController{
	Connection connection;
	public ConversationControllerJDBC(){
		connection=Connect_db.connect();
	}
	
	public ResultSet getMessage(int RoomID) throws SQLException {
	String sql = "SELECT * FROM conversation c , players p  where c.sender_id = p.id and p.id_room = "+RoomID+" order by c.date;";
    
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();
    return resultSet;
	}
	
	public void sendMessage(int PlayerID , String Input) throws SQLException {
		String sql = "INSERT INTO conversation ( sender_id, message, date) VALUES (" + PlayerID + ", '" + Input + "', NOW());";

		
	    PreparedStatement statement = connection.prepareStatement(sql);
	    statement.executeUpdate();
	}
	
}
