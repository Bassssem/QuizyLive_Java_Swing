package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableOfRoom extends AbstractTableModel  {
	private List<Room> ListeOfRoom ;
	private String titles[] = {"Id","Date"};
	public TableOfRoom() {
		ListeOfRoom = new ArrayList<Room>();
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ListeOfRoom.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return  titles.length;
	}
	@Override
    public String getColumnName(int column) {
        return titles[column];
    }
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Room room = ListeOfRoom.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return room.getRoomID();
            case 1:
                return room.getDate();
            default:
                return null;
        }
	}
	
	public void charger(List<Room> l) {
		ListeOfRoom = l;
		fireTableDataChanged();
	}
	
	
}
