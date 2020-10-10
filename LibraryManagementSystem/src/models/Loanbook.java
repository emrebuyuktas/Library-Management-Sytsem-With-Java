package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.SqliteDb;

public class Loanbook {
	private SqliteDb db=new SqliteDb();
	private PreparedStatement preparedStatement=null;
	
	private Book book;
	private Member member;
	
	
	
	public Loanbook(Book book, Member member) {
		//composition
		super();
		this.book = book;
		this.member = member;
	}
	public Loanbook() {
			try(Connection connection= db.getConnection();Statement statement= connection.createStatement();) {
        	
			statement.execute("CREATE TABLE IF NOT EXISTS loan (memberid TEXT, membername TEXT,membersurname TEXT,memberemail TEXT, "
					+ "bookid INTEGER, bookname TEXT, authorname TEXT)");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Loanbook> getTable(){
		String getString="SELECT * FROM loan";
		ArrayList<Loanbook> loan=new ArrayList<>();
		try(Connection connection=db.getConnection();Statement statement=connection.createStatement();) {
			ResultSet rs=statement.executeQuery(getString);
			while(rs.next()) {
			
				String memberid=rs.getString("memberid");
				String membername=rs.getString("membername");
				String membersurname=rs.getString("membersurname");
				String memberemail=rs.getString("memberemail");
				
				int bookid=rs.getInt("bookid") ;
				String bookname=rs.getString("bookname");
				String authorname=rs.getString("authorname");
				
				loan.add(new Loanbook(new Book(bookname, authorname, bookid), new Member(memberid, membername, membersurname, memberemail)));
				
				
			}
			return loan;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void loan(Member member,Book book) {
		if(alreadyOnLoan(book.getId())) {
			JOptionPane.showMessageDialog(null, "The book is already on loan");
		}else {
				try(Connection connection= db.getConnection();) {
	        	
				
				String addString="INSERT INTO loan VALUES(?,?,?,?,?,?,?)";
				preparedStatement=connection.prepareStatement(addString);
				preparedStatement.setString(1, member.getIdString());
				preparedStatement.setString(2, member.getNameString());
				preparedStatement.setString(3, member.getSurnameString());
				preparedStatement.setString(4, member.getEmailString());
				preparedStatement.setInt(5, book.getId());
				preparedStatement.setString(6, book.getNameString());
				preparedStatement.setString(7, book.getAuthor());
				
				preparedStatement.executeUpdate();
				
				
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong");
				e.printStackTrace();
			}
		}
		
	}
	public void delete(String idString) {
		String deleteString="DELETE FROM loan WHERE memberid=?";
		try(Connection connection= db.getConnection();){
			preparedStatement=connection.prepareStatement(deleteString);
			preparedStatement.setString(1, idString);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public boolean alreadyOnLoan(int id) {
		try(Connection connection= db.getConnection();) {
			String controlString="SELECT * FROM loan where bookid=?";
			preparedStatement=connection.prepareStatement(controlString);
			preparedStatement.setInt(1, id);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			return rs.next();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			
		}
		return false;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	

}
