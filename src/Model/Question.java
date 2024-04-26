package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
	private int id;
	private String qt;
	private String correct_inswer;
	private List <String> inswers = new ArrayList();
	public Question(String qt, String correct_inswer, String inswer2 , String inswer3 , String inswer4) {
		this.qt = qt;
		this.correct_inswer = correct_inswer;
		inswers.add(correct_inswer);
		inswers.add(inswer2);
		inswers.add(inswer3);
		inswers.add(inswer4);
	}
	
	public Question(int id,String qt, String correct_inswer, String inswer2 , String inswer3 , String inswer4) {
		this.id=id;
		this.qt = qt;
		this.correct_inswer = correct_inswer;
		inswers.add(correct_inswer);
		inswers.add(inswer2);
		inswers.add(inswer3);
		inswers.add(inswer4);
	}
	
	public int getId() {
		return id;
	}

	public String getQt() {
		return qt;
	}

	public String getCorrect_inswer() {
		return correct_inswer;
	}

	public List<String> getInswers() {
		return inswers;
	}

	public void melanger() {
		Collections.shuffle(inswers);
	}
	
}