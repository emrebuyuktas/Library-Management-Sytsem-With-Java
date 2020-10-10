package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.SqliteDb;
import interfaces.Itransactions;

public class Member extends Base implements Itransactions {
	private SqliteDb db=new SqliteDb();
	private PreparedStatement preparedStatement=null;

	

	public Member(String idString,String nameString, String surnameString,String email) {
		super(idString,nameString, surnameString, email);
			
}
	
	
	public  Member() {
		
		super("", "", "", "");
			try(Connection connection= db.getConnection();Statement statement= connection.createStatement();) {
        	
			statement.execute("CREATE TABLE IF NOT EXISTS members (id TEXT, name TEXT, surname TEXT, email TEXT)");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public ArrayList<Member> getMembers(){
		String getString="SELECT * FROM members";
		ArrayList<Member> librarians=new ArrayList<>();
		try(Connection connection=db.getConnection();Statement statement=connection.createStatement();) {
			ResultSet rs=statement.executeQuery(getString);
			while(rs.next()) {
				String id=rs.getString("id");
				String name=rs.getString("name");
				String surname=rs.getString("surname");
				String email=rs.getString("email");
				
				librarians.add(new Member( id,name, surname, email));
			}
			return librarians;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		if(alreadyAdded()) {
			JOptionPane.showMessageDialog(null, "Member is already added");
		}else {
			
				try(Connection connection= db.getConnection();) {
	        	
				
				String addString="INSERT INTO members VALUES(?,?,?,?)";
				preparedStatement=connection.prepareStatement(addString);
				preparedStatement.setString(1, super.getIdString());
				preparedStatement.setString(2, super.getNameString());
				preparedStatement.setString(3, super.getSurnameString());
				preparedStatement.setString(4, super.getEmailString());
				
				preparedStatement.executeUpdate();
				
				
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong");
				e.printStackTrace();
			}
		}
		 
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		String delete="DELETE FROM members WHERE id =?";
		 try(Connection connection=db.getConnection();) {
				
				preparedStatement=connection.prepareStatement(delete);
				preparedStatement.setString(1, super.getIdString());
		     	preparedStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Transaction successful");
				
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}
	}

	@Override
	public void update(String idNumber) {
		// TODO Auto-generated method stub
		String addString="UPDATE members set id=?, name=?, surname=?,email=? WHERE id=? ";
		try(Connection connection=db.getConnection();) {
			
			preparedStatement=connection.prepareStatement(addString);
			preparedStatement.setString(1, super.getIdString());
			preparedStatement.setString(2, super.getNameString());
			preparedStatement.setString(3, super.getSurnameString());
			preparedStatement.setString(4, super.getEmailString());
			preparedStatement.setString(5, idNumber);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Transaction successful");
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong");
		}
		
	}


	@Override
	public boolean alreadyAdded() {
		try(Connection connection= db.getConnection();) {
			String controlString="SELECT * FROM members where id=?";
			preparedStatement=connection.prepareStatement(controlString);
			preparedStatement.setString(1, super.getIdString());
			
			ResultSet rs=preparedStatement.executeQuery();
			
			return rs.next();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			
		}
		return false;
	}
	

}
