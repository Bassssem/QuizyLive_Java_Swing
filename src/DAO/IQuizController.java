package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Quiz;

public interface IQuizController {
	public List<Quiz> getListeOfQuiz();
	public void delete(int id) throws SQLException;
	public void AddQuiz(Quiz quiz);
	public boolean findQuiz(int id);
	List<Quiz> getListeOfQuiz(int UserID) throws SQLException;
	void createQuiz(int QuizID, int UserID, String QuizName) throws SQLException;
}
