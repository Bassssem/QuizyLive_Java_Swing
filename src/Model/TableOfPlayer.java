package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class TableOfPlayer extends AbstractTableModel{
	private List<Player> ListPlayers ;
	private String titles[] = {"name","score"};
	
	public TableOfPlayer() {
		ListPlayers = new ArrayList<Player>();
	}

    @Override
    public String getColumnName(int column) {
        return titles[column];
    }
    
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ListPlayers.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return  titles.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Player player = ListPlayers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return player.getName();
            case 1:
                return player.getScore();
            default:
                return null;
        }
	}
	public void charger(List<Player> l) {
		ListPlayers = l;
		fireTableDataChanged();
	}
	

}
