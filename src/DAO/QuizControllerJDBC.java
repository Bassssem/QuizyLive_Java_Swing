package DAO;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Quiz;

public class QuizControllerJDBC implements IQuizController {
	Connection connection;
	
	public QuizControllerJDBC() {
		connection=Connect_db.connect();
	}
	@Override
	public List<Quiz> getListeOfQuiz(int UserID) throws SQLException {
		List<Quiz> Quizs = new ArrayList<Quiz>();
		
		String sql = "SELECT * FROM quiz where id_creator = "+UserID+";";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
        	int id = resultSet.getInt(1);
            String Name = resultSet.getString(3);
            String date = resultSet.getString(4);
            System.out.println(id);
            Quizs.add(new Quiz(id, Name, date));
        }
		return Quizs;
	}

	@Override
	public void delete(int id) throws SQLException {
		
					 String sql = "DELETE from quiz where id = "+id+";";
					 PreparedStatement statement = connection.prepareStatement(sql);
					 int rowsInserted = statement.executeUpdate();
		
	}
	
	
	@Override
	public void createQuiz(int QuizID, int UserID , String QuizName) throws SQLException {
		 String sql = "INSERT INTO quiz VALUES("+QuizID+", "+UserID+", '"+QuizName+"', now());";
		 PreparedStatement statement = connection.prepareStatement(sql);
		 int rowsInserted = statement.executeUpdate();
		
	}

	@Override
	public boolean findQuiz(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Quiz> getListeOfQuiz() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void AddQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		
	}

}
