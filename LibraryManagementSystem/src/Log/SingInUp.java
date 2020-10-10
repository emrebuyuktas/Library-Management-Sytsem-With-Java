package Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.SqliteDb;

public class SingInUp {
	private SqliteDb db=new SqliteDb();
	private PreparedStatement preparedStatement=null;
	
	public SingInUp() {
		
	}
	
	public boolean signIn(String id,String password) {
		
		try(Connection connection= db.getConnection();Statement statement= connection.createStatement();) {
			String controlString="SELECT * FROM librarian where id=? and password=?";
			preparedStatement=connection.prepareStatement(controlString);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			ResultSet rs=preparedStatement.executeQuery();
			
			return rs.next();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			
		}
		return false;
		
		
		
	}
	public void signUp(String id,String name,String surname,String email, String password) {
        try(Connection connection= db.getConnection();Statement statement= connection.createStatement();) {
        	
			statement.execute("CREATE TABLE IF NOT EXISTS librarian (id TEXT, name TEXT, surname TEXT, email TEXT, password TEXT)");
			String addString="INSERT INTO librarian VALUES(?,?,?,?,?)";
			preparedStatement=connection.prepareStatement(addString);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, surname);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, password);
			preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
