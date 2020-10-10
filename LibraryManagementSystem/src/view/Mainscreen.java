package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Admin;
import models.Book;
import models.Loanbook;
import models.Member;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mainscreen extends JFrame {
	

	private JPanel contentPane;
	private JTable librarianTable;
	
	DefaultTableModel model;
	DefaultTableModel modelMember;
	DefaultTableModel modelBook;
	DefaultTableModel modelLoanBook;
	DefaultTableModel modelLaonMember;
	DefaultTableModel modelLoanTbale;
	
	private JTextField libIdText;
	private JTextField libNameTex;
	private JTextField libSurnameText;
	private JTextField libEmailText;
	private JTextField libPasswordText;
	private JTextField memberIdText;
	private JTextField memberNameText;
	private JTextField memberSurnameText;
	private JTextField memberEmailText;
	private JTable memberTable;
	private JTable bookTable;
	private JTextField bookIdText;
	private JTextField bookNameText;
	private JTextField bookAuthorText;
	private JTable loanMemberTable;
	private JTable loanBookTable;
	private JTextField loanMemberId;
	private JTextField loanMemberName;
	private JTextField loanMemberSurname;
	private JTextField loanMemberEmail;
	private JTextField loanBookId;
	private JTextField loanBookName;
	private JTextField loanBookAuthor;
	private JTable LoanTable;

	/**
	 * Launch the application.
	 */
	public void clearLibText() {
		
		libIdText.setText("");
		libNameTex.setText("");
		libSurnameText.setText("");
		libEmailText.setText("");
		libPasswordText.setText("");
		
	}
	public void clearMemberText() {
		memberIdText.setText("");
		memberNameText.setText("");
		memberSurnameText.setText("");
		memberEmailText.setText("");
		
	}
	public void clearBookText() {
		bookIdText.setText("");
		bookNameText.setText("");
		bookAuthorText.setText("");
	}

	public void showLibrarian() {
		Admin admin=new Admin();
		model.setRowCount(0);
		ArrayList<Admin> librariansArrayList=admin.getLibrarians();
		if(librariansArrayList==null) {
			JOptionPane.showMessageDialog(this, "No librarian found");
		}else {
			for(Admin a:librariansArrayList) {
				Object[] containerObjects= {a.getIdString(),a.getNameString(),a.getSurnameString(),a.getEmailString(),a.getPasswordString()};
				model.addRow(containerObjects);
			}
		}
		
	}
	public void showMember() {
		Member member=new Member();
		modelMember.setRowCount(0);
		ArrayList<Member> membersArrayList=member.getMembers();
		if(membersArrayList==null) {
			JOptionPane.showMessageDialog(this, "No librarian found");
		}else {
			for(Member a:membersArrayList) {
				Object[] containerObjects= {a.getIdString(),a.getNameString(),a.getSurnameString(),a.getEmailString()};
				modelMember.addRow(containerObjects);
			}
		}
		
	}
	public void showBook() {
		Book book=new Book();
		modelBook.setRowCount(0);
		ArrayList<Book> books=book.getBooks();
		if(books==null) {
			JOptionPane.showMessageDialog(this, "No books found");
		}else {
			for(Book b:books) {
				Object[] containerObjects= {b.getId(),b.getNameString(),b.getAuthor()};
				modelBook.addRow(containerObjects);
			}
		}
	}
	public void showLoanTable() {
		Loanbook loanbook=new Loanbook();
		modelLoanTbale.setRowCount(0);
		
		ArrayList<Loanbook> loanbooks=loanbook.getTable();
		if(loanbooks==null) {
			JOptionPane.showMessageDialog(this, "Loan table is empty");
		}else {
			for(Loanbook l:loanbooks) {
				Object[] containerObjects= {l.getMember().getIdString(),l.getMember().getNameString(),l.getMember().getSurnameString(),l.getMember().getEmailString(),
						l.getBook().getId(),l.getBook().getNameString(),l.getBook().getAuthor()};
				
				modelLoanTbale.addRow(containerObjects);
				
			}
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainscreen frame = new Mainscreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainscreen() {
		setTitle("Library Mangement System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1304, 769);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		tabbedPane.setBounds(10, 10, 1270, 712);
		contentPane.add(tabbedPane);
		
		JPanel librarianPanel = new JPanel();
		librarianPanel.setBackground(new Color(51, 204, 255));
		tabbedPane.addTab(" Librarian operations", new ImageIcon(Mainscreen.class.getResource("/view/librarian.png")), librarianPanel, null);
		librarianPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 104, 535, 519);
		librarianPanel.add(scrollPane);
		
		librarianTable = new JTable();
		librarianTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=librarianTable.getSelectedRow();
				libIdText.setText(model.getValueAt(selectedRow, 0).toString());
				libNameTex.setText(model.getValueAt(selectedRow, 1).toString());
				libSurnameText.setText(model.getValueAt(selectedRow, 2).toString());
				libEmailText.setText(model.getValueAt(selectedRow, 3).toString());
				libPasswordText.setText(model.getValueAt(selectedRow, 4).toString());
				librarianTable.setSelectionBackground(Color.green);
				
			}
		});
		librarianTable.setBackground(new Color(0, 153, 255));
		librarianTable.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		librarianTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Surname", "E-mail", "Password"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(librarianTable);
		
		JLabel lblNewLabel = new JLabel("Id:\r\n");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel.setBounds(580, 128, 136, 32);
		librarianPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(580, 200, 136, 32);
		librarianPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surname");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(580, 269, 136, 32);
		librarianPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("E-mail:");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(580, 334, 136, 32);
		librarianPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Password:");
		lblNewLabel_1_1_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(580, 399, 136, 32);
		librarianPanel.add(lblNewLabel_1_1_2);
		
		libIdText = new JTextField();
		libIdText.setBounds(739, 127, 219, 32);
		librarianPanel.add(libIdText);
		libIdText.setColumns(10);
		
		libNameTex = new JTextField();
		libNameTex.setColumns(10);
		libNameTex.setBounds(739, 205, 219, 32);
		librarianPanel.add(libNameTex);
		
		libSurnameText = new JTextField();
		libSurnameText.setColumns(10);
		libSurnameText.setBounds(739, 274, 219, 32);
		librarianPanel.add(libSurnameText);
		
		libEmailText = new JTextField();
		libEmailText.setColumns(10);
		libEmailText.setBounds(739, 339, 219, 32);
		librarianPanel.add(libEmailText);
		
		libPasswordText = new JTextField();
		libPasswordText.setColumns(10);
		libPasswordText.setBounds(739, 404, 219, 32);
		librarianPanel.add(libPasswordText);
		
		JButton libAddButton = new JButton("Add");
		libAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idString=libIdText.getText();
				String nameString=libNameTex.getText();
				String surnameString=libSurnameText.getText();
				String emailString=libEmailText.getText();
				String passwordString=libPasswordText.getText();
				Admin ad=new Admin(idString, nameString, surnameString, emailString, passwordString);
				ad.add();
				clearLibText();
				showLibrarian();
				
			}
		});
		libAddButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		libAddButton.setBounds(555, 541, 110, 53);
		librarianPanel.add(libAddButton);
		
		JButton libDeleteButton = new JButton("Delete");
		libDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=librarianTable.getSelectedRow();
				if(selectedRow==-1) {
					if(selectedRow==0) {
						JOptionPane.showMessageDialog(null, "No librarians found");
					}else {
						JOptionPane.showMessageDialog(null, "Please select a member from the table");
					}
				}else {
					String idString=libIdText.getText();
					String nameString=libNameTex.getText();
					String surnameString=libSurnameText.getText();
					String emailString=libEmailText.getText();
					String passwordString=libPasswordText.getText();
					Admin ad=new Admin(idString, nameString, surnameString, emailString, passwordString);
					ad.delete();
					clearLibText();
					showLibrarian();
				}
			}
		});
		libDeleteButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		libDeleteButton.setBounds(709, 541, 110, 53);
		librarianPanel.add(libDeleteButton);
		
		JButton libUpdateButton = new JButton("Update");
		libUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=librarianTable.getSelectedRow();
				if(selectedRow==-1) {
					if(selectedRow==0) {
						JOptionPane.showMessageDialog(null, "No librarians found");
					}else {
						JOptionPane.showMessageDialog(null, "Please select a member from the table");
					}
				}else {
					String idString=libIdText.getText();
					String nameString=libNameTex.getText();
					String surnameString=libSurnameText.getText();
					String emailString=libEmailText.getText();
					String passwordString=libPasswordText.getText();
					Admin ad=new Admin(idString, nameString, surnameString, emailString, passwordString);
					String idNumberString=model.getValueAt(selectedRow, 0).toString();
					ad.update(idNumberString);
					clearLibText();
					showLibrarian();
				}
			}
		});
		libUpdateButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		libUpdateButton.setBounds(858, 541, 110, 53);
		librarianPanel.add(libUpdateButton);
		
		model=(DefaultTableModel) librarianTable.getModel();
		showLibrarian();
		
		JPanel memberPanel = new JPanel();
		memberPanel.setBackground(new Color(51, 204, 255));
		tabbedPane.addTab("Member operations", new ImageIcon(Mainscreen.class.getResource("/view/membership.png")), memberPanel, null);
		memberPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Id:\r\n");
		lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_2.setBounds(570, 149, 136, 32);
		memberPanel.add(lblNewLabel_2);
		
		memberIdText = new JTextField();
		memberIdText.setColumns(10);
		memberIdText.setBounds(729, 148, 219, 32);
		memberPanel.add(memberIdText);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name:");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(570, 214, 136, 32);
		memberPanel.add(lblNewLabel_1_2);
		
		memberNameText = new JTextField();
		memberNameText.setColumns(10);
		memberNameText.setBounds(729, 213, 219, 32);
		memberPanel.add(memberNameText);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Surname");
		lblNewLabel_1_1_3.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_3.setBounds(570, 279, 136, 32);
		memberPanel.add(lblNewLabel_1_1_3);
		
		memberSurnameText = new JTextField();
		memberSurnameText.setColumns(10);
		memberSurnameText.setBounds(729, 278, 219, 32);
		memberPanel.add(memberSurnameText);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("E-mail:");
		lblNewLabel_1_1_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(570, 344, 136, 32);
		memberPanel.add(lblNewLabel_1_1_1_1);
		
		memberEmailText = new JTextField();
		memberEmailText.setColumns(10);
		memberEmailText.setBounds(729, 343, 219, 32);
		memberPanel.add(memberEmailText);
		
		JButton memberAddButton = new JButton("Add");
		memberAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idString=memberIdText.getText();
				String nameString=memberNameText.getText();
				String surnameString=memberSurnameText.getText();
				String emailString=memberEmailText.getText();
				
				Member member=new Member(idString, nameString, surnameString, emailString);
				member.add();
				clearMemberText();
				showMember();
				
			}
		});
		memberAddButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		memberAddButton.setBounds(555, 551, 110, 53);
		memberPanel.add(memberAddButton);
		
		JButton memberDeleteButton = new JButton("Delete");
		memberDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idString=memberIdText.getText();
				String nameString=memberNameText.getText();
				String surnameString=memberSurnameText.getText();
				String emailString=memberEmailText.getText();
				
				Member member=new Member(idString, nameString, surnameString, emailString);
				member.delete();
				clearMemberText();
				showMember();
			}
		});
		memberDeleteButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		memberDeleteButton.setBounds(695, 551, 110, 53);
		memberPanel.add(memberDeleteButton);
		
		JButton memberUpdateButton = new JButton("Update");
		memberUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=memberTable.getSelectedRow();
				if(selectedRow==-1) {
					if(selectedRow==0) {
						JOptionPane.showMessageDialog(null, "No librarians found");
					}else {
						JOptionPane.showMessageDialog(null, "Please select a member from the table");
					}
				}else {
					String idString=memberIdText.getText();
					String nameString=memberNameText.getText();
					String surnameString=memberSurnameText.getText();
					String emailString=memberEmailText.getText();
					
					Member member=new Member(idString, nameString, surnameString, emailString);
					String idNumberString=modelMember.getValueAt(selectedRow, 0).toString();
					member.update(idNumberString);
					clearMemberText();
					showMember();
				}
				
			}
		});
		memberUpdateButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		memberUpdateButton.setBounds(838, 551, 110, 53);
		memberPanel.add(memberUpdateButton);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		scrollPane_1.setBounds(10, 113, 535, 519);
		memberPanel.add(scrollPane_1);
		
		memberTable = new JTable();
		memberTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=memberTable.getSelectedRow();
				memberIdText.setText(modelMember.getValueAt(selectedRow, 0).toString());
				memberNameText.setText(modelMember.getValueAt(selectedRow, 1).toString());
				memberSurnameText.setText(modelMember.getValueAt(selectedRow, 2).toString());
				memberEmailText.setText(modelMember.getValueAt(selectedRow, 3).toString());
				
				memberTable.setSelectionBackground(Color.green);
				
			}
		});
		
		memberTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Surname", "E-Mail"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		memberTable.setBackground(new Color(0, 255, 255));
		memberTable.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		scrollPane_1.setViewportView(memberTable);
		modelMember=(DefaultTableModel) memberTable.getModel();
		showMember();
		
		JPanel bookPanel = new JPanel();
		bookPanel.setBackground(new Color(51, 204, 255));
		tabbedPane.addTab("Book operations", new ImageIcon(Mainscreen.class.getResource("/view/book.png")), bookPanel, null);
		bookPanel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 113, 535, 519);
		bookPanel.add(scrollPane_2);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=bookTable.getSelectedRow();
				bookIdText.setText(modelBook.getValueAt(selectedRow, 0).toString());
				bookNameText.setText(modelBook.getValueAt(selectedRow, 1).toString());
				bookAuthorText.setText(modelBook.getValueAt(selectedRow, 2).toString());
				
				
				bookTable.setSelectionBackground(Color.green);
				
			}
		});
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Book Name", "Author Name"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		bookTable.setBackground(new Color(0, 255, 255));
		modelBook=(DefaultTableModel)bookTable.getModel();
		showBook();
		scrollPane_2.setViewportView(bookTable);
		
		JLabel lblNewLabel_2_1 = new JLabel("Id:\r\n");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(569, 239, 136, 32);
		bookPanel.add(lblNewLabel_2_1);
		
		bookIdText = new JTextField();
		bookIdText.setEditable(false);
		bookIdText.setColumns(10);
		bookIdText.setBounds(728, 238, 219, 32);
		bookPanel.add(bookIdText);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Book name:");
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(569, 293, 136, 32);
		bookPanel.add(lblNewLabel_1_2_1);
		
		bookNameText = new JTextField();
		bookNameText.setColumns(10);
		bookNameText.setBounds(728, 292, 219, 32);
		bookPanel.add(bookNameText);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Author name");
		lblNewLabel_1_1_3_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_3_1.setBounds(569, 352, 136, 32);
		bookPanel.add(lblNewLabel_1_1_3_1);
		
		bookAuthorText = new JTextField();
		bookAuthorText.setColumns(10);
		bookAuthorText.setBounds(728, 351, 219, 32);
		bookPanel.add(bookAuthorText);
		
		JButton bookAddButton = new JButton("Add");
		bookAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String booknameString=bookNameText.getText();
				String authorString=bookAuthorText.getText();
				
				Book book=new Book(booknameString, authorString, 0);
				book.add();
				clearBookText();
				showBook();
			}
		});
		bookAddButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		bookAddButton.setBounds(554, 542, 110, 53);
		bookPanel.add(bookAddButton);
		
		JButton bookDeleteButton = new JButton("Delete");
		bookDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String booknameString=bookNameText.getText();
				String authorNameString=bookAuthorText.getText();
				int id=Integer.valueOf(bookIdText.getText());
				
				Book book=new Book(booknameString, authorNameString, id);
				book.delete();
				clearBookText();
				showBook();
			}
		});
		bookDeleteButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		bookDeleteButton.setBounds(694, 542, 110, 53);
		bookPanel.add(bookDeleteButton);
		
		JButton bookUpdateButton = new JButton("Update");
		bookUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String booknameString=bookNameText.getText();
				String authorNameString=bookAuthorText.getText();
				int id=Integer.valueOf(bookIdText.getText());
				
				Book book=new Book(booknameString, authorNameString, id);
				book.update("");
				clearBookText();
				showBook();
			}
		});
		bookUpdateButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		bookUpdateButton.setBounds(837, 542, 110, 53);
		bookPanel.add(bookUpdateButton);
		
		JPanel loanbookPanel = new JPanel();
		loanbookPanel.setBackground(new Color(51, 204, 255));
		tabbedPane.addTab(" Loan book", new ImageIcon(Mainscreen.class.getResource("/view/bookloan.png")), loanbookPanel, null);
		loanbookPanel.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(32, 29, 405, 278);
		loanbookPanel.add(scrollPane_3);
		
		loanMemberTable = new JTable(modelMember);
		modelLaonMember=(DefaultTableModel) loanMemberTable.getModel();
		loanMemberTable.setBackground(new Color(0, 255, 255));
		loanMemberTable.setFont(new Font("Yu Gothic", Font.BOLD, 10));
		scrollPane_3.setViewportView(loanMemberTable);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(32, 352, 405, 278);
		loanbookPanel.add(scrollPane_4);
		
		loanBookTable = new JTable(modelBook);
		modelLoanBook=(DefaultTableModel) loanBookTable.getModel();
		loanBookTable.setBackground(new Color(0, 255, 255));
		loanBookTable.setFont(new Font("Yu Gothic", Font.BOLD, 10));
		scrollPane_4.setViewportView(loanBookTable);
		
		JLabel lblNewLabel_2_2 = new JLabel("Member id:");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		lblNewLabel_2_2.setBounds(447, 48, 77, 32);
		loanbookPanel.add(lblNewLabel_2_2);
		
		loanMemberId = new JTextField();
		loanMemberId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loanMemberId.setColumns(10);
		loanMemberId.setBounds(545, 47, 139, 32);
		loanbookPanel.add(loanMemberId);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Name:");
		lblNewLabel_1_2_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_2_2.setBounds(447, 113, 77, 32);
		loanbookPanel.add(lblNewLabel_1_2_2);
		
		loanMemberName = new JTextField();
		loanMemberName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loanMemberName.setColumns(10);
		loanMemberName.setBounds(545, 112, 139, 32);
		loanbookPanel.add(loanMemberName);
		
		JLabel lblNewLabel_1_1_3_2 = new JLabel("Surname");
		lblNewLabel_1_1_3_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_3_2.setBounds(447, 178, 77, 32);
		loanbookPanel.add(lblNewLabel_1_1_3_2);
		
		loanMemberSurname = new JTextField();
		loanMemberSurname.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loanMemberSurname.setColumns(10);
		loanMemberSurname.setBounds(545, 177, 139, 32);
		loanbookPanel.add(loanMemberSurname);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("E-mail:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_1.setBounds(447, 243, 68, 32);
		loanbookPanel.add(lblNewLabel_1_1_1_1_1);
		
		loanMemberEmail = new JTextField();
		loanMemberEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loanMemberEmail.setColumns(10);
		loanMemberEmail.setBounds(545, 242, 139, 32);
		loanbookPanel.add(loanMemberEmail);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Book id:");
		lblNewLabel_2_2_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_2_2_1.setBounds(447, 353, 77, 32);
		loanbookPanel.add(lblNewLabel_2_2_1);
		
		loanBookId = new JTextField();
		loanBookId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loanBookId.setColumns(10);
		loanBookId.setBounds(545, 352, 139, 32);
		loanbookPanel.add(loanBookId);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Book Name:");
		lblNewLabel_1_2_2_1.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		lblNewLabel_1_2_2_1.setBounds(447, 418, 77, 32);
		loanbookPanel.add(lblNewLabel_1_2_2_1);
		
		loanBookName = new JTextField();
		loanBookName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loanBookName.setColumns(10);
		loanBookName.setBounds(545, 417, 139, 32);
		loanbookPanel.add(loanBookName);
		
		JLabel lblNewLabel_1_1_3_2_1 = new JLabel("Author:");
		lblNewLabel_1_1_3_2_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_3_2_1.setBounds(447, 483, 77, 32);
		loanbookPanel.add(lblNewLabel_1_1_3_2_1);
		
		loanBookAuthor = new JTextField();
		loanBookAuthor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		loanBookAuthor.setColumns(10);
		loanBookAuthor.setBounds(545, 482, 139, 32);
		loanbookPanel.add(loanBookAuthor);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memberidString=loanMemberId.getText();
				String membernameString=loanMemberName.getText();
				String membersurnameString=loanMemberSurname.getText();
				String memberemailString=loanMemberEmail.getText();
				
				Member member=new Member(memberidString, membernameString, membersurnameString, memberemailString);
				
				int bookId=Integer.valueOf(loanBookId.getText());
				String bookNameString=loanBookName.getText();
				String authorString=loanBookAuthor.getText();
				
				Book book=new Book(bookNameString, authorString, bookId);
				
				Loanbook loanbook=new Loanbook();
				loanbook.loan(member, book);
				
				showLoanTable();
				
			}
		});
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveButton.setBounds(558, 562, 110, 47);
		loanbookPanel.add(saveButton);
		
		JScrollPane loanScroolPane = new JScrollPane();
		loanScroolPane.setBounds(707, 82, 548, 421);
		loanbookPanel.add(loanScroolPane);
		
		LoanTable = new JTable();
		LoanTable.setFont(new Font("Tahoma", Font.PLAIN, 8));
		LoanTable.setBackground(new Color(0, 255, 255));
		
		loanScroolPane.setViewportView(LoanTable);
		LoanTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Surname", "E-mail", "Book id", "Book name", "Author"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=LoanTable.getSelectedRow();
				String idString=modelLoanTbale.getValueAt(selectedRow, 0).toString();
				Loanbook loanbook=new Loanbook();
				loanbook.delete(idString);
				showLoanTable();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(930, 562, 110, 47);
		loanbookPanel.add(btnDelete);
		loanBookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=loanBookTable.getSelectedRow();
				loanBookId.setText(modelLoanBook.getValueAt(selectedRow, 0).toString());
				loanBookName.setText(modelLoanBook.getValueAt(selectedRow, 1).toString());
				loanBookAuthor.setText(modelLoanBook.getValueAt(selectedRow, 2).toString());
				
				
				loanBookTable.setSelectionBackground(Color.green);
				
			}
		});
		loanMemberTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=loanMemberTable.getSelectedRow();
				loanMemberId.setText(modelLaonMember.getValueAt(selectedRow, 0).toString());
				loanMemberName.setText(modelLaonMember.getValueAt(selectedRow, 1).toString());
				loanMemberSurname.setText(modelLaonMember.getValueAt(selectedRow, 2).toString());
				loanMemberEmail.setText(modelLaonMember.getValueAt(selectedRow, 3).toString());
				
				
				loanMemberTable.setSelectionBackground(Color.green);
				
			}
		});
        modelLoanTbale=(DefaultTableModel) LoanTable.getModel();
		
		showLoanTable();
		
		
	}
}
