import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class frmInstructor extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtKandidati;
	private JTextField txtMbiemri;
	private JTextField txtTelefoni;
	private JTextField txtEmail;
	private JTextField txtAdresa;
	Connection conn=null;
	PreparedStatement pst= null;
	ResultSet rs=null;
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmInstructor frame = new frmInstructor();
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
	public frmInstructor() {
		conn=SqlConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 553);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClients = new JMenu("Kandidatet");
		menuBar.add(mnClients);
		
		JButton btnTeDhenat = new JButton("Te Dhenat");
		btnTeDhenat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTable table=new JTable();
				Object[] columns= {"ID", "Emri","Mbiemri","Telefoni","Email","nePersonal","adresa","ditelindja"};
				DefaultTableModel model=new DefaultTableModel();
				
				
				JFrame frame =new JFrame("Instruktoret");
				frame.setBounds(100,100,700,610);
				
				model.setColumnIdentifiers(columns);
				table.setModel(model);
				
				table.setBackground(Color.white);
				table.setRowHeight(30);
				table.setAutoCreateRowSorter(true);
				
				
				JScrollPane pane= new JScrollPane(table);
				pane.setBackground(Color.RED);
				pane.setBounds(50,50,729,354);
				frame.getContentPane().add(pane);
				
				
				frame.setVisible(true);
				
				
				try 
				{
					//ketu shkruhet pyetesori per insert ne DB
					String sql=" select * from Kandidatet" ;
					PreparedStatement pst=conn.prepareStatement(sql);
					ResultSet rs= pst.executeQuery();						
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "Erro gjate insertit ne DB");
				}
				
				
				
				
				
			}
		});
		mnClients.add(btnTeDhenat);
		
		JMenu mnSchedule = new JMenu("Oret");
		menuBar.add(mnSchedule);
		
		JButton btnNewButton = new JButton("Oret");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				JTable table=new JTable();
				Object[] columns= {"ID", "Emri dhe mbiemri","kategoria","Oret","Emri i Instruktorit"};
				DefaultTableModel model=new DefaultTableModel();
				
				
				JFrame frame =new JFrame("Oret");
				frame.setBounds(100,100,700,610);
				
				model.setColumnIdentifiers(columns);
				table.setModel(model);
				
				table.setBackground(Color.white);
				table.setRowHeight(30);
				table.setAutoCreateRowSorter(true);
				
				
				JScrollPane pane= new JScrollPane(table);
				pane.setBackground(Color.RED);
				pane.setBounds(50,50,729,354);
				frame.getContentPane().add(pane);
				
				
				frame.setVisible(true);
				
				
				try 
				{
					//ketu shkruhet pyetesori per insert ne DB
					String sql="SELECT k.emri ||' '|| k.mbiemri \"Emri dhe Mbiemri\", c.emriKategorise \"Kategoria\", o.nrOreve \"oret\", i.emri.emri \"Emri Instruktorit/es\", l.emri.emri \"Emri Ligjeruesit/es\", o.dataFillimit, o.dataMbarimit\r\n" + 
							"FROM Kandidatet k, Oret o, Instruktoret i, Kategorite c, Ligjeruesit l\r\n" + 
							"where i.iId=o.instruktoriId and k.kId=o.kandidatiId and c.kategoriaId=o.kategoriaId and o.ligjeruesiId=l.lId" ;
					PreparedStatement pst=conn.prepareStatement(sql);
					ResultSet rs= pst.executeQuery();						
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "Erro gjate insertit ne DB");
				}
				
				
				
				
			}
		});
		mnSchedule.add(btnNewButton);
		
		JMenu mnTransaction = new JMenu("Transaksionet");
		menuBar.add(mnTransaction);
		
		JButton btnNewButton_1 = new JButton("Faturat");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTable table=new JTable();
				Object[] columns= {"ID e kandidatit", "Emri dhe mbiemri","Numri Faturave","vlera minimale","Vlera maksimale","Vlera mesatare","Totali"};
				DefaultTableModel model=new DefaultTableModel();
				
				
				JFrame frame =new JFrame("Faturat");
				frame.setBounds(100,100,700,610);
				
				model.setColumnIdentifiers(columns);
				table.setModel(model);
				
				table.setBackground(Color.white);
				table.setRowHeight(30);
				table.setAutoCreateRowSorter(true);
				
				
				JScrollPane pane= new JScrollPane(table);
				pane.setBackground(Color.RED);
				pane.setBounds(50,50,729,354);
				frame.getContentPane().add(pane);
				
				
				frame.setVisible(true);
				
				
				try 
				{
					//ketu shkruhet pyetesori per insert ne DB
					String sql="SELECT k.kid as \"ID e kandidatit\",\r\n" + 
							"k.emri ||' '||k.mbiemri as \"Emri dhe mbiemri\",\r\n" + 
							"count(*) as \"Numri i faturave\",\r\n" + 
							"MIN(f.shumaTotale) as \"Vlera minimale\",\r\n" + 
							"MAX(f.shumaTotale) as \"Vlera maksimale\",\r\n" + 
							"AVG(f.shumaTotale) as \"Vlera mesatare\",\r\n" + 
							"SUM(f.shumaTotale) as \"Totali\"\r\n" + 
							"FROM Kandidatet k\r\n" + 
							"inner join Faturat f on k.kid=f.kandidatiId\r\n" + 
							"GROUP BY k.kId, k.emri, k.mbiemri\r\n" + 
							"having count(*)>0" ;
					PreparedStatement pst=conn.prepareStatement(sql);
					ResultSet rs= pst.executeQuery();						
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "Erro gjate insertit ne DB");
				}
				
				
			}
		});
		mnTransaction.add(btnNewButton_1);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 36, 751, 194);
		contentPane.add(table);
		
		JButton btnShto = new JButton("Shto");
		btnShto.setBounds(50, 415, 89, 23);
		contentPane.add(btnShto);
		
		JButton btnFshij = new JButton("Fshij");
		btnFshij.setBounds(187, 415, 89, 23);
		contentPane.add(btnFshij);
		
		JButton btnPerditeso = new JButton("Perditeso");
		btnPerditeso.setBounds(320, 415, 89, 23);
		contentPane.add(btnPerditeso);
		
		txtKandidati = new JTextField();
		txtKandidati.setBounds(6, 266, 133, 30);
		contentPane.add(txtKandidati);
		txtKandidati.setColumns(10);
		
		txtMbiemri = new JTextField();
		txtMbiemri.setBounds(37, 340, 133, 30);
		contentPane.add(txtMbiemri);
		txtMbiemri.setColumns(10);
		
		txtTelefoni = new JTextField();
		txtTelefoni.setBounds(523, 266, 133, 30);
		contentPane.add(txtTelefoni);
		txtTelefoni.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(170, 266, 144, 30);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtAdresa = new JTextField();
		txtAdresa.setBounds(350, 266, 133, 30);
		contentPane.add(txtAdresa);
		txtAdresa.setColumns(10);
		
		JButton btnTedhenat = new JButton("TeDhenat");
		btnTedhenat.setBounds(593, 404, 89, 44);
		contentPane.add(btnTedhenat);
		
		JLabel lblIdEKandidatit = new JLabel("ID e Kandidatit");
		lblIdEKandidatit.setBounds(20, 241, 129, 14);
		contentPane.add(lblIdEKandidatit);
		
		JLabel lblIdEKategorise = new JLabel("ID e Kategorise");
		lblIdEKategorise.setBounds(170, 241, 133, 14);
		contentPane.add(lblIdEKategorise);
		
		JLabel lblNumriOreve = new JLabel("Numri Oreve");
		lblNumriOreve.setBounds(50, 315, 132, 14);
		contentPane.add(lblNumriOreve);
		
		JLabel lblIdEInstruktorit = new JLabel("ID e Instruktorit");
		lblIdEInstruktorit.setBounds(340, 241, 133, 14);
		contentPane.add(lblIdEInstruktorit);
		
		JLabel lblIdELigjeruesit = new JLabel("ID e Ligjeruesit");
		lblIdELigjeruesit.setBounds(527, 241, 129, 14);
		contentPane.add(lblIdELigjeruesit);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(233, 340, 133, 30);
		contentPane.add(textField_1);
		
		JLabel lblDataEFillimit = new JLabel("Data e Fillimit");
		lblDataEFillimit.setBounds(233, 315, 133, 14);
		contentPane.add(lblDataEFillimit);
		
		JLabel lblDataEMbarimit = new JLabel("Data e Mbarimit");
		lblDataEMbarimit.setBounds(427, 315, 130, 14);
		contentPane.add(lblDataEMbarimit);
		
		textField = new JTextField();
		textField.setBounds(402, 340, 133, 30);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
