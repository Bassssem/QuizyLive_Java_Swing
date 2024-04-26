package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ILoginAndSignupController {
	public ResultSet VerifLogin(String emailText, String passwordText) throws SQLException;
	public int VerifSignup(String nom, String emailText, String passwordText) throws SQLException;
}
