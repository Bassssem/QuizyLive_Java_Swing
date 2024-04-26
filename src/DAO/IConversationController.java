package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConversationController {
	public ResultSet getMessage(int RoomID) throws SQLException ;
		
	public void sendMessage(int PlayerID , String Input) throws SQLException ;

}
