package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import DAO.IPlayerController;
import Model.Player;

public class PlayerController implements IPlayerController {
	private List<Player> ListPlayers ;
	
	public PlayerController(){
		ListPlayers = new  ArrayList<Player>();
	}
	
	public void ajoutPlayer(Player player){
		ListPlayers.add(player);
	}

	public List<Player> getListPlayers() {
		return ListPlayers;
	}
	public void deleteAll() {
		ListPlayers.clear();
	}
	public void trier(List<Player> listOfPlayers) {
		Collections.sort(listOfPlayers, new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				// TODO Auto-generated method stub
				return o2.getScore()-o1.getScore();
			}
        	
		});
	}
	public void sortInverse(List<Player> listOfPlayers) {
		Collections.sort(listOfPlayers, new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				// TODO Auto-generated method stub
				return o1.getScore()-o2.getScore();
			}
        	
		});
	}
	public int FindRankWithID(int PlayerID ,List<Player> listOfPlayers) {
		for(int i = 0 ; i<listOfPlayers.size() ; i++)
			if(listOfPlayers.get(i).getId()==PlayerID)
				return i+1;
		return 0;
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
	
	
}
