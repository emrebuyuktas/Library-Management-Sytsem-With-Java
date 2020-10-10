package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDb {
	private Connection connection=null;
	private String dbUrl="jdbc:sqlite:librarymangementsystem.db";
	
	public SqliteDb(Connection connection, String dbUrl) {
		
		this.connection = connection;
		this.dbUrl = dbUrl;
	}
	public SqliteDb() {
		
	}
	public Connection getConnection() {
		try {
			connection=DriverManager.getConnection(dbUrl);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	

}
