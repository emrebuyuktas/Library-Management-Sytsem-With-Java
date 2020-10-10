package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Log.SingInUp;

import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Authentication extends JFrame {

	private JPanel contentPane;
	private JTextField emailText;
	private JTextField surnameText;
	private JTextField nameText;
	private JTextField upIdNumber;
	private JTextField inIdNumber;
	private JPasswordField inPassword;
	private JPasswordField upPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentication frame = new Authentication();
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
	public Authentication() {
		setBackground(Color.WHITE);
		setTitle("Library Mamagement System\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 669);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(339, 23, 75, 64);
		lblNewLabel.setIcon(new ImageIcon(Authentication.class.getResource("/view/libraryicon.png")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to the library management system");
		lblNewLabel_1.setBounds(187, 108, 378, 44);
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		contentPane.add(lblNewLabel_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		tabbedPane.setBounds(0, 151, 753, 481);
		contentPane.add(tabbedPane);
		
		JPanel signingPanel = new JPanel();
		signingPanel.setBackground(new Color(51, 204, 255));
		tabbedPane.addTab("Sign In", null, signingPanel, null);
		signingPanel.setLayout(null);
		
		JLabel lblNewLabel_2_3 = new JLabel("Identification number:\r\n");
		lblNewLabel_2_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_2_3.setBounds(66, 134, 157, 44);
		signingPanel.add(lblNewLabel_2_3);
		
		inIdNumber = new JTextField();
		inIdNumber.setColumns(10);
		inIdNumber.setBounds(265, 134, 218, 44);
		signingPanel.add(inIdNumber);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Password:\r\n");
		lblNewLabel_2_3_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_2_3_1.setBounds(66, 206, 157, 44);
		signingPanel.add(lblNewLabel_2_3_1);
		
		inPassword = new JPasswordField();
		inPassword.setBounds(265, 222, 218, 44);
		signingPanel.add(inPassword);
		
		JButton signInButton = new JButton("Sıgn In\r\n");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=inIdNumber.getText();
				String password=new String(inPassword.getPassword());
				
				SingInUp autInUp=new SingInUp();
				if(autInUp.signIn(id, password)) {
					Mainscreen mainscreen=new Mainscreen();
					mainscreen.setVisible(true);
					dispose();
				
				}
			}
		});
		signInButton.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		signInButton.setBounds(331, 334, 95, 52);
		signingPanel.add(signInButton);
		
		JPanel signupPanel = new JPanel();
		signupPanel.setBackground(new Color(51, 204, 255));
		tabbedPane.addTab("Sign Up", null, signupPanel, null);
		signupPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Identification number:\r\n");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_2.setBounds(64, 0, 157, 44);
		signupPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Name:");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(64, 72, 157, 44);
		signupPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Surname");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(64, 144, 157, 44);
		signupPanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("E-mail:");
		lblNewLabel_2_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_2_2_1.setBounds(64, 216, 157, 44);
		signupPanel.add(lblNewLabel_2_2_1);
		
		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(265, 220, 218, 44);
		signupPanel.add(emailText);
		
		surnameText = new JTextField();
		surnameText.setColumns(10);
		surnameText.setBounds(265, 148, 218, 44);
		signupPanel.add(surnameText);
		
		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBounds(265, 76, 218, 44);
		signupPanel.add(nameText);
		
		upIdNumber = new JTextField();
		upIdNumber.setColumns(10);
		upIdNumber.setBounds(265, 4, 218, 44);
		signupPanel.add(upIdNumber);
		
		JButton signUpButton = new JButton("Sıgn Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SingInUp authInUp= new SingInUp();
				String id=upIdNumber.getText();
				String name=nameText.getText();
				String surname=surnameText.getText();
				String email=emailText.getText();
				String password=new String(upPassword.getPassword());
				
				authInUp.signUp(id, name, surname, email, password);
				Mainscreen mainscreen=new Mainscreen();
				mainscreen.setVisible(true);
				dispose();
			}
		});
		signUpButton.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		signUpButton.setBounds(322, 372, 95, 52);
		signupPanel.add(signUpButton);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Password\r\n");
		lblNewLabel_2_2_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_2_2_1_1.setBounds(64, 291, 157, 44);
		signupPanel.add(lblNewLabel_2_2_1_1);
		
		upPassword = new JPasswordField();
		upPassword.setBounds(265, 291, 218, 44);
		signupPanel.add(upPassword);
	}
	
}
