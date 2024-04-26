package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableOfQuiz extends AbstractTableModel {
	private List<Quiz> ListeOfQuiz ;
	private String titles[] = {"Id","Name","Date"};
	
	public TableOfQuiz() {
		ListeOfQuiz = new ArrayList<Quiz>();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ListeOfQuiz.size();
	}
	
    @Override
    public String getColumnName(int column) {
        return titles[column];
    }
    
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return  titles.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Quiz quiz = ListeOfQuiz.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return quiz.getId();
            case 1:
                return quiz.getNom();
            case 2:
                return quiz.getDate();
            default:
                return null;
        }
	}
	public void charger(List<Quiz> l) {
		ListeOfQuiz = l;
		fireTableDataChanged();
	}
	

}
