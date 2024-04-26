package DAO;


import java.sql.SQLException;
import java.util.List;

import Model.Question;

public interface IQuestionController {
	public List<Question> getListeOfQuestion();
	public void ajouterQuestion(Question question);
	public void deleteQuestion(int id) throws SQLException;
	public void deleteAll();
	List<Question> getListeOfQuestion(int RoomID);
	List<Question> getListeOfQuestionWithID(int QuizID) throws SQLException;
	void ajouterQuestionToDB(int QuizID, String Qt, String correct_inswer, String other_inswer1, String other_inswer2,
			String other_inswer3) throws SQLException;

}
