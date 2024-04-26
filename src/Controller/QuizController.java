package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.Connect_db;
import DAO.IQuizController;
import Model.Quiz;

public class QuizController  implements IQuizController {
	List<Quiz> ListeOfQuiz;
	Connection connection=Connect_db.connect();
	public QuizController() {
		ListeOfQuiz = new ArrayList<Quiz>();
	}

	public List<Quiz> getListeOfQuiz() {
		return ListeOfQuiz;
	}
	
	public void delete(int id) {
		
		
	}
	
	public void insertListOfQuiz(List<Quiz> quizs) {
		ListeOfQuiz = quizs;
	}
	public boolean findQuiz(int id) {
		for(Quiz quiz : ListeOfQuiz)
			if(quiz.getId()==id)
				return true;
		return false;
	}

	@Override
	public void AddQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Quiz> getListeOfQuiz(int UserID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createQuiz(int QuizID, int UserID, String QuizName) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
