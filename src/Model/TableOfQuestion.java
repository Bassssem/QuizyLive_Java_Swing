package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableOfQuestion extends AbstractTableModel {
	private List<Question> ListeOfQuestion ;
	private String titles[] = {"Id","Question","Correct Inswer","Other Inswer","Other Inswer","Other Inswer"};
	

	public TableOfQuestion() {
		super();
		ListeOfQuestion = new ArrayList<Question>();
	}

	@Override
    public String getColumnName(int column) {
        return titles[column];
    }
	
	@Override
	public int getRowCount() {
		return ListeOfQuestion.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return titles.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Question question = ListeOfQuestion.get(rowIndex);
        switch (columnIndex) {
	        case 0:
	            return question.getId();
	        case 1:
	            return question.getQt();
	        case 2:
	            return question.getCorrect_inswer();
	        case 3:
	            return question.getInswers().get(1);
	        case 4:
	            return question.getInswers().get(2);
	        case 5:
	            return question.getInswers().get(3);
            default:
                return null;
        }
	}
	
	public void charger(List<Question> l) {
		ListeOfQuestion = l;
		fireTableDataChanged();
	}

}
