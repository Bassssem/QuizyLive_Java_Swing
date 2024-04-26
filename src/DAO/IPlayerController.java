package DAO;


import java.util.List;

import Model.Player;

public interface IPlayerController {
	public void ajoutPlayer(Player player);
	public List<Player> getListPlayers();
	public void deleteAll();
	public void trier();
	public void sortInverse();
	public int FindRankWithID(int PlayerID);

}
