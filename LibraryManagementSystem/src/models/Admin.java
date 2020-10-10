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

public class Admin extends Base implements Itransactions{
	private String passwordString;
	private SqliteDb db=new SqliteDb();
	private PreparedStatement preparedStatement=null;
	public Admin(String idString,String nameString, String surnameString,String email,String password) {
		super(idString,nameString, surnameString,email);
		this.passwordString=password;
		// TODO Auto-generated constructor stub
	}
	
	public Admin() {
		super("", "", "", "");
	}

	
	

	public ArrayList<Admin> getLibrarians(){
		String getString="SELECT * FROM librarian";
		ArrayList<Admin> librarians=new ArrayList<>();
		try(Connection connection=db.getConnection();Statement statement=connection.createStatement();) {
			ResultSet rs=statement.executeQuery(getString);
			while(rs.next()) {
				String id=rs.getString("id");
				String name=rs.getString("name");
				String surname=rs.getString("surname");
				String email=rs.getString("email");
				String password=rs.getString("password");
				librarians.add(new Admin( id,name, surname, email, password));
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
			JOptionPane.showMessageDialog(null, "Librarian is already added");
		}else {
			String addString="INSERT INTO librarian VALUES(?,?,?,?,?)";
			try(Connection connection=db.getConnection();) {
				
				preparedStatement=connection.prepareStatement(addString);
				preparedStatement.setString(1, super.getIdString());
				preparedStatement.setString(2, super.getNameString());
				preparedStatement.setString(3, super.getSurnameString());
				preparedStatement.setString(4, super.getEmailString());
				preparedStatement.setString(5, passwordString);
				preparedStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Transaction successful");
				
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}
		}
		
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		 String delete="DELETE FROM librarian WHERE id =?";
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
		String addString="UPDATE librarian set id=?, name=?, surname=?,email=?,password=? WHERE id=? ";
		try(Connection connection=db.getConnection();) {
			
			preparedStatement=connection.prepareStatement(addString);
			preparedStatement.setString(1, super.getIdString());
			preparedStatement.setString(2, super.getNameString());
			preparedStatement.setString(3, super.getSurnameString());
			preparedStatement.setString(4, super.getEmailString());
			preparedStatement.setString(5, passwordString);
			preparedStatement.setString(6, idNumber);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Transaction successful");
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong");
		}
		
	}

	public String getPasswordString() {
		return passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	@Override
	public boolean alreadyAdded() {
		// TODO Auto-generated method stub
		try(Connection connection= db.getConnection();) {
			String controlString="SELECT * FROM librarian where id=?";
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
