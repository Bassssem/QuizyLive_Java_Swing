package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Question;

public class QuestionControllerJDBC implements IQuestionController {
	
	Connection connection;
	
	public QuestionControllerJDBC() {
		connection=Connect_db.connect();
	}
	
	@Override
	public List<Question> getListeOfQuestion(int RoomID) {
		List<Question> listOfQuestions = new ArrayList<Question>();
		try {
            String sql = "select q.* from room r, question q where r.id_quiz=q.id_quiz and r.id = "+RoomID+" ;";
  			 PreparedStatement statement = connection.prepareStatement(sql);
  			 ResultSet rowsInserted = statement.executeQuery();
  			 
                  while (rowsInserted.next()) {
               	   listOfQuestions.add(new Question(rowsInserted.getString(3),rowsInserted.getString(4) , rowsInserted.getString(5),rowsInserted.getString(6) , rowsInserted.getString(7)));
                      
                  }
            
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return listOfQuestions;
	}
	
	@Override
	public List<Question> getListeOfQuestionWithID(int QuizID) throws SQLException {
		List<Question> listOfQuestions = new ArrayList<Question>();
	String sql = "SELECT * FROM question where id_quiz = "+QuizID+";";
    
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
    	int id = resultSet.getInt(1);
    	String qt = resultSet.getString(3);
        String correctInswer = resultSet.getString(4);
        String otherInswer1 = resultSet.getString(5);
        String otherInswer2 = resultSet.getString(6);
        String otherInswer3 = resultSet.getString(7);

        listOfQuestions.add(new Question(id, qt, correctInswer, otherInswer1, otherInswer2, otherInswer3));
    }
    return listOfQuestions;
	}

	@Override
	public void ajouterQuestionToDB(int QuizID , String Qt, String correct_inswer ,  String other_inswer1, String other_inswer2 , String other_inswer3) throws SQLException {
		 String sql = "INSERT INTO question VALUES(null,"+QuizID+", '"+Qt+"', '"+correct_inswer+"', '"+other_inswer1+"', '"+other_inswer2+"', '"+other_inswer3+"', now());";
		 PreparedStatement statement = connection.prepareStatement(sql);
		 int rowsInserted = statement.executeUpdate();
		
	}

	@Override
	public void deleteQuestion(int id) throws SQLException {
		String sql = "DELETE from question where id = "+id+";";
		PreparedStatement statement = connection.prepareStatement(sql);
		int rowsInserted = statement.executeUpdate();

		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Question> getListeOfQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}
	
	

}
