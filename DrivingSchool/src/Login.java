import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;

public class Login {

	private JFrame frame;
	public JLabel lblUsename=new JLabel("Username:");
	public JLabel lblPaswword=new JLabel("Password:");
	public JButton btnLogin=new JButton("Login:");
	public JButton btnSignup=new JButton("Signup:");
	private JTextField txtUsename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn=null;
	PreparedStatement pst= null;
	ResultSet rs=null;
	
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField password;
	
	
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 650, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Driving School");
		lblName.setBackground(Color.GRAY);
		lblName.setForeground(Color.BLACK);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Castellar", Font.BOLD, 14));
		lblName.setBounds(10, 11, 614, 49);
		frame.getContentPane().add(lblName);
		
		JLabel lblUsename = new JLabel("Username");
		lblUsename.setFont(new Font("Georgia", Font.BOLD, 14));
		lblUsename.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsename.setBounds(361, 155, 185, 20);
		frame.getContentPane().add(lblUsename);
		
		txtUsename = new JTextField();
		txtUsename.setBounds(343, 186, 241, 33);
		frame.getContentPane().add(txtUsename);
		txtUsename.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Georgia", Font.BOLD, 14));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(361, 230, 185, 20);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblForgotYouPassword = new JLabel("Forgot password?");
		lblForgotYouPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblForgotYouPassword.setBounds(449, 318, 185, 14);
		frame.getContentPane().add(lblForgotYouPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conn=SqlConnection.dbConnector();
				try{
					String query="select * from Users where Username=? and Password=?";
					pst = (PreparedStatement) conn.prepareStatement(query);
					//PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,txtUsename.getText());
					pst.setString(2, password.getText());
					rs=(ResultSet) pst.executeQuery();				
					if(rs.next())
					{
						
						JOptionPane.showMessageDialog(null, "Username and Password is correct");
						frame.dispose();
						frmClient c=new frmClient();
						c.setVisible(true);						
					}
					
					else{
						JOptionPane.showMessageDialog(null, "Username and Password is not correct try Again...");
						
					}
				}catch(Exception e){
					     
					JOptionPane.showMessageDialog(null, e);
				}
			}
		
				
				
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(402, 353, 124, 33);
		frame.getContentPane().add(btnNewButton);
		
		JCheckBox chckbxRememberMe = new JCheckBox("remember me");
		chckbxRememberMe.setBounds(343, 314, 124, 23);
		frame.getContentPane().add(chckbxRememberMe);
		
		JLabel lblNeedNewAcount = new JLabel("Need new account?");
		lblNeedNewAcount.setBackground(Color.GRAY);
		lblNeedNewAcount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNeedNewAcount.setHorizontalAlignment(SwingConstants.CENTER);
		lblNeedNewAcount.setBounds(150, 448, 172, 39);
		frame.getContentPane().add(lblNeedNewAcount);
		
		JButton btnSignUp1 = new JButton("Sign Up");
		btnSignUp1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSignUp1.setBounds(402, 452, 124, 33);
		frame.getContentPane().add(btnSignUp1);
		btnSignUp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				frame.dispose();
				SignUp signup=new SignUp();
				signup.setVisible(true);				
			
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(10, 11, 614, 49);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Mirjeta Bytyqi\\eclipse-workspace\\DrivingSchool\\Images\\malidrivingschool-300x300.jpeg"));
		lblNewLabel.setBounds(10, 142, 299, 295);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 444, 614, 49);
		frame.getContentPane().add(panel);
		
		password = new JPasswordField();
		password.setBounds(343, 258, 241, 33);
		frame.getContentPane().add(password);
	}
}
