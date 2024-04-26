package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Model.Player;
import Viewer.WaitRoomToStartPage;

public class PlayerControllerJDBC implements IPlayerController{
	Connection connection;
	public PlayerControllerJDBC(){
		connection=Connect_db.connect();
	}
	
	public int ajoutPlayer(Player player,int RoomID){
		try {
			
			 String sql = "INSERT INTO players VALUES ("+player.getId()+" , "+RoomID+",'"+player.getName()+"',0,'"+player.getImage()+"', now())";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			 int rowsInserted = statement.executeUpdate();
			 return rowsInserted;
			 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	public List<Player> getListPlayers(int RoomID) throws SQLException {
		List<Player> listOfPlayers = new ArrayList<Player>();
		String sql = "SELECT * FROM players  where id_room = "+RoomID+" and id != id_room ;";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
        	int id = resultSet.getInt(1);
            String playerName = resultSet.getString(3);
            int Score = resultSet.getInt(4);
            String image = resultSet.getString(5);
            listOfPlayers.add(new Player(id,playerName,Score,image));
        }
        return listOfPlayers;
	}
	
	public void ChangeScoreOfPlayer(int score, int PlayerID) throws SQLException {
		String sql = "UPDATE players SET score = "+score+" WHERE `id` = "+PlayerID+" LIMIT 1 ;";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 int rowsInserted = statement.executeUpdate(); 
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trier() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortInverse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int FindRankWithID(int PlayerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void ajoutPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getListPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
