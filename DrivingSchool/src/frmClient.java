import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class frmClient extends JFrame{

	private JPanel contentPane;
	Connection conn=null;
	PreparedStatement pst= null;
	ResultSet rs=null;
	private JTable table_1;
	private JTextField txtEmri;
	private JTextField txtMbiemri;
	private JTextField txtInstruktori;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmClient frame = new frmClient();
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
	public frmClient() {
		conn=SqlConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 504);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAutomjetet = new JMenu("Automjetet");
		menuBar.add(mnAutomjetet);
		
		JButton btnKategorite = new JButton("Kategorite");
		btnKategorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTable table=new JTable();
				Object[] columns= {"Kategoria", "Pershkrimi","Vetura","Ngjyra"};
				DefaultTableModel model=new DefaultTableModel();
				
				
				JFrame frame =new JFrame("Automjetet");
				frame.setBounds(100,100,610,500);
				
				model.setColumnIdentifiers(columns);
				table.setModel(model);
				
				table.setBackground(Color.white);
				table.setRowHeight(30);
				table.setAutoCreateRowSorter(true);
				
				
				JScrollPane pane= new JScrollPane(table);
				pane.setBackground(Color.RED);
				pane.setBounds(50,50,729,354);
				frame.getContentPane().add(pane);
				
				Object[] row= new Object[4];
				
				frame.setVisible(true);
				
				
				
				try 
				{
					//ketu shkruhet pyetesori per insert ne DB
					String sql=" select k.emriKategorise \"Kategoria\" , k.pershkrimiKategorise \"Pershkrimi\" ,\r\n" + 
							" v.emri \"Vetura\" , v.ngjyra \"Ngjyra\" from Kategorite k, TABLE (k.veturat) v order by k.emriKategorise ";
					pst=(PreparedStatement) conn.prepareStatement(sql);
					ResultSet rs= pst.executeQuery();						
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "Erro gjate insertit ne DB");
				}
				
				
				
			}
		});
		mnAutomjetet.add(btnKategorite);
		
		JMenu mnOrari = new JMenu("Orari");
		menuBar.add(mnOrari);
		
		JButton btnOrari = new JButton("Orari");
		mnOrari.add(btnOrari);
		
		JMenu mnInstruktoret = new JMenu("Instruktoret");
		menuBar.add(mnInstruktoret);
		
		JButton btnTeDhenat = new JButton("Te dhenat");
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
					String sql=" select * from Instruktoret" ;
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
		mnInstruktoret.add(btnTeDhenat);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(10, 31, 756, 203);
		contentPane.add(table_1);
		
		JButton btnShto = new JButton("SHTO");
		btnShto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			
				
				
				
				
			}
		});
		btnShto.setBounds(22, 384, 89, 23);
		contentPane.add(btnShto);
		
		JButton btnFshij = new JButton("FSHIJ");
		btnFshij.setBounds(159, 384, 89, 23);
		contentPane.add(btnFshij);
		
		JButton btnPerditeso = new JButton("Perditeso");
		btnPerditeso.setBounds(304, 384, 89, 23);
		contentPane.add(btnPerditeso);
		
		txtEmri = new JTextField();
		txtEmri.setBounds(22, 271, 159, 29);
		contentPane.add(txtEmri);
		txtEmri.setColumns(10);
		
		txtMbiemri = new JTextField();
		txtMbiemri.setBounds(22, 324, 159, 29);
		contentPane.add(txtMbiemri);
		txtMbiemri.setColumns(10);
		
		txtInstruktori = new JTextField();
		txtInstruktori.setBounds(242, 271, 151, 29);
		contentPane.add(txtInstruktori);
		txtInstruktori.setColumns(10);
	}
}
