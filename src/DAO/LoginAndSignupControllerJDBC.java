package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Viewer.signup;

public class LoginAndSignupControllerJDBC implements ILoginAndSignupController {
	Connection connection;

	public LoginAndSignupControllerJDBC() {
		connection = Connect_db.connect();
	}

	public ResultSet VerifLogin(String emailText, String passwordText) throws SQLException {
		String sql = "select * from user where email like '" + emailText + "' and password like '" + passwordText
				+ "' ;";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rowsInserted = statement.executeQuery();
		return rowsInserted;
	}

	public int VerifSignup(String nom, String emailText, String passwordText) throws SQLException {
		String sql = "select * from user where email like '" + emailText + "' and password like '" + passwordText
				+ "' ;";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rowsInserted = statement.executeQuery();
		if (rowsInserted.next()) {
			return -1;
		} else {
			sql = "INSERT INTO user VALUES (null,'" + nom + "', '" + emailText + "', '" + passwordText + "', now())";
			statement = connection.prepareStatement(sql);
			int rowsInserte = statement.executeUpdate();
			return rowsInserte;
		}

	}

}
