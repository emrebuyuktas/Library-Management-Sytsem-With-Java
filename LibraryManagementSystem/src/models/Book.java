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

public class Book implements Itransactions {
	private String nameString;
	private String author;
	private int id;
	
	private SqliteDb db=new SqliteDb();
	private PreparedStatement preparedStatement=null;
	public Book(String nameString, String author, int id)  {
		
		this.nameString = nameString;
		this.author = author;
		this.id = id;
	}
	public Book() {
		try(Connection connection= db.getConnection();Statement statement= connection.createStatement();) {
        	
			statement.execute("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY AUTOINCREMENT, bookname TEXT, authorname TEXT)");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Book> getBooks(){
		String getString="SELECT * FROM books";
		ArrayList<Book> books=new ArrayList<>();
		try(Connection connection=db.getConnection();Statement statement=connection.createStatement();) {
			ResultSet rs=statement.executeQuery(getString);
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("bookname");
				String email=rs.getString("authorname");
				
				books.add(new Book(name, email, id));
			}
			return books;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void add() {
		// TODO Auto-generated method stub
		try(Connection connection= db.getConnection();) {
        	
			
			String addString="INSERT INTO books VALUES(Null,?,?)";
			preparedStatement=connection.prepareStatement(addString);
			
			preparedStatement.setString(1, nameString);
			preparedStatement.setString(2, author);
			
			
			
			preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong");
			e.printStackTrace();
		}
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
		String delete="DELETE FROM books WHERE id =?";
		 try(Connection connection=db.getConnection();) {
				
				preparedStatement=connection.prepareStatement(delete);
				preparedStatement.setInt(1, id);
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
		String addString="UPDATE books SET bookname=?, authorname=? WHERE id=? ";
		
		try(Connection connection=db.getConnection();) {
			
			preparedStatement=connection.prepareStatement(addString);
			preparedStatement.setString(1, nameString);
			preparedStatement.setString(2, author);	
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Transaction successful");
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong");
		}
		
	}
	@Override
	public boolean alreadyAdded() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
