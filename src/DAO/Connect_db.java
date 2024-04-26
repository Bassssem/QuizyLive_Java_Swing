package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_db {
	
	static String jdbcUrl = "jdbc:mysql://localhost:3306/Quizy?useSSl=false";
    static String username = "root";
    static String password = "";
    static Connection connection;
	private Connect_db() {
		try {
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public static Connection connect() {
		if(connection==null)
			new Connect_db();
		return connection;
	}
}
