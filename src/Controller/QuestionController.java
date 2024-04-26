package Controller;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.Connect_db;
import DAO.IQuestionController;
import Model.Question;


public class QuestionController implements IQuestionController {
	List<Question> ListeOfQuestion;
	Connection connection=Connect_db.connect();

	public QuestionController() {
		ListeOfQuestion = new ArrayList<Question>();
	}
	
	public List<Question> getListeOfQuestion() {
		return ListeOfQuestion;
	}

	public void ajouterQuestion(Question question) {
		ListeOfQuestion.add(question);
	}
	
	public void deleteQuestion(int id) {
		
		
	}
	public void deleteAll() {
		ListeOfQuestion.clear();
	}

	@Override
	public List<Question> getListeOfQuestion(int RoomID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getListeOfQuestionWithID(int QuizID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterQuestionToDB(int QuizID, String Qt, String correct_inswer, String other_inswer1,
			String other_inswer2, String other_inswer3) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}
