import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import net.proteanit.sql.DbUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField txtAddress;
	private JTextField txtSurname;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtState;
	private JTextField txtCity;
	private JTextField txtPostCode;
	private JTextField txtUsername;
	private JTextField txtPesonalNr;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn=null;
	PreparedStatement pst= null;
	ResultSet rs=null;
	private JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 646);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri", Font.BOLD, 14));
		lblName.setBackground(new Color(240, 240, 240));
		lblName.setBounds(181, 82, 46, 14);
		contentPane.add(lblName);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(306, 288, 257, 30);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Calibri", Font.BOLD, 14));
		lblSurname.setBackground(SystemColor.menu);
		lblSurname.setBounds(177, 126, 80, 14);
		contentPane.add(lblSurname);
		
		JLabel lblAddress = new JLabel("Street");
		lblAddress.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAddress.setBackground(SystemColor.menu);
		lblAddress.setBounds(177, 378, 64, 14);
		contentPane.add(lblAddress);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(306, 118, 257, 30);
		contentPane.add(txtSurname);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(306, 329, 257, 30);
		contentPane.add(txtPhone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(306, 496, 257, 30);
		contentPane.add(txtEmail);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTelephone.setBackground(SystemColor.menu);
		lblTelephone.setBounds(177, 334, 80, 20);
		contentPane.add(lblTelephone);
		
		JLabel lblEmaili = new JLabel("Email");
		lblEmaili.setFont(new Font("Calibri", Font.BOLD, 14));
		lblEmaili.setBackground(SystemColor.menu);
		lblEmaili.setBounds(181, 504, 46, 14);
		contentPane.add(lblEmaili);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(306, 74, 257, 30);
		contentPane.add(txtName);
		
		JLabel lblRegistration = new JLabel("Registration");
		lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistration.setFont(new Font("Calibri", Font.BOLD, 20));
		lblRegistration.setBounds(145, 11, 327, 45);
		contentPane.add(lblRegistration);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
					
				JTextComponent lblInfo = null;
				if(!txtName.getText().equals("") && !txtSurname.getText().equals(""))
				{
					try 
					{
						//ketu shkruhet pyetesori per insert ne DB
						String sql1="insert into Kandidatet (id,emri,mbiemri) values"
								+ "(default,'"+txtName.getText()+"','"+txtSurname.getText()+"')";
						
						
						
						String sql="INSERT INTO Kandidatet\r\n" + 
								"VALUES(kandidatet_seq.NEXTVAL,'Mirjeta','Zijadin','Bytyqi','1170955761','049362999','mirjetabytyqi@gmail.com',\r\n" + 
								"       adresa('Sylejman Vokshi','Prishtine','10000'),20,'28-Sep-1997',\r\n" + 
								"       '01-Jan-2018',Testime(LlojiTestimit('10-Jan-2018','Provues','95'),\r\n" + 
								"                              LlojiTestimit('20-Jan-2018','Real','100')),'Aktiv');";
						
						
						pst=(PreparedStatement) conn.prepareStatement(sql);
						pst.execute();	
						//perdoret per me be update tablen kur regjistrohet nje student
						updateTable();
						//per pastrimin e fushave emri dhe mbiemri
						txtName.setText("");						
						txtSurname.setText("");
						//njofton kur nje student u regjistru ne DB
						lblInfo.setText("Studenti u regjistru me sukses!");
						
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, "Erro gjate insertit ne DB");
					}
				}
				else
				{
					lblInfo.setText("Kontrollo te dhenat hyrese");
				}
				
			}
		});
				
		btnSignUp.setBounds(372, 551, 123, 45);
		contentPane.add(btnSignUp);
		
		
		
		
		
		JLabel lblState = new JLabel("Address");
		lblState.setFont(new Font("Calibri", Font.BOLD, 14));
		lblState.setBackground(SystemColor.menu);
		lblState.setBounds(181, 288, 57, 30);
		contentPane.add(lblState);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCity.setBackground(SystemColor.menu);
		lblCity.setBounds(181, 422, 80, 14);
		contentPane.add(lblCity);
		
		JLabel lblPostcode = new JLabel("PostCode");
		lblPostcode.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPostcode.setBackground(SystemColor.menu);
		lblPostcode.setBounds(177, 463, 80, 14);
		contentPane.add(lblPostcode);
		
		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(306, 370, 257, 30);
		contentPane.add(txtState);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(306, 414, 257, 30);
		contentPane.add(txtCity);
		
		txtPostCode = new JTextField();
		txtPostCode.setColumns(10);
		txtPostCode.setBounds(306, 455, 257, 30);
		contentPane.add(txtPostCode);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 14));
		lblUsername.setBounds(181, 163, 73, 14);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(306, 163, 257, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPassword.setBounds(181, 212, 80, 14);
		contentPane.add(lblPassword);
		
		JLabel lblPersonalnr = new JLabel("PersonalNR");
		lblPersonalnr.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPersonalnr.setBounds(181, 253, 73, 14);
		contentPane.add(lblPersonalnr);
		
		txtPesonalNr = new JTextField();
		txtPesonalNr.setBounds(306, 245, 257, 31);
		contentPane.add(txtPesonalNr);
		txtPesonalNr.setColumns(10);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(412, 537, 46, 14);
		contentPane.add(lblInfo);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(306, 209, 257, 25);
		contentPane.add(txtPassword);
	}
	public void updateTable()
	{
		try 
		{
			//pyetesori per me dergu ne database
			String sql="select * from Client";
			//pergatitet deklarata per me dergu ne database
			pst=(PreparedStatement) conn.prepareStatement(sql);
			//ekzekutohet query ne database dhe mirret rezultati
			//i cili vendoset ne res
			rs=(ResultSet) pst.executeQuery();
			//perdoret me rs2xml.jar per me i vendos rezultatet
			//ne jTable me emrin tblstudente
			//mbyllja e lidhjes me db
			pst.close();			
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Erro ne update te tabeles"+e.getMessage());
		}
		
	}
}
