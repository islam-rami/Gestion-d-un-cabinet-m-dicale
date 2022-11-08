package gl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
/*********************************************************************************************************************************
 * @author INES
 * voila l'agenda de medecin
 * ou il va consulter tout les rendez vous  de ses patients
 * que ça soit les nouveau paient que ça soit les abonées 
 * il peut les consulter 
 * 
 * soit par peroide de une date a une autre 
 * 
 * ou
 * 
 * par journee de semaine il suffit de choisir une journee 
 * pour mettre tout les rendez vous qu'il a  
 * 
 ******************************************************************************************************************************/

@SuppressWarnings({ "unused", "serial" })
public class Agenda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtAnnedebut;
	private JTextField txtAnnfin;
	Connection connection = null; 
	PreparedStatement ps = null;
	private JLabel c ;
	JButton menu ;
	JButton menu_1;
	/**
	 * Launch the application.
	 */
	 public void clock()
		///***************************** pour ajouter l'heur et la date en cours **************************************
	   {   Thread clock=new Thread() 
	          {
		          public void run() 
		          {	 
		   try { 
			   for(;;){
			   Calendar cal = new GregorianCalendar();
		    int day=cal.get(Calendar.DAY_OF_MONTH);
		    int mouth=cal.get(Calendar.MONTH);
		    int year=cal.get(Calendar.YEAR);
		   
		    int second=cal.get(Calendar.SECOND);
		    int minute=cal.get(Calendar.MINUTE);
		    int hour=cal.get(Calendar.HOUR);
		    
		    c.setText("l'heure "+hour+":"+minute+":"+second+" Date "+day+"/"+mouth+"/"+year);
		        	  
						sleep(1000);}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		          }
	          };
	            clock.start();
	   }
//	 ******************************************************************************************************************

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Agenda() {
		setResizable(false);
		setTitle("Agenda\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(420, 64, 608, 432);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Agenda des rendez _vous ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(394, 11, 203, 42);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBoxdebut = new JComboBox();
		comboBoxdebut.setFont(new Font("Tahoma", Font.BOLD, 13));
		
// *************************utiliser un combo pour limité l'utilisation  et pour respecter les contraintre de jour 
		
		comboBoxdebut.setModel(new DefaultComboBoxModel(new String[] {"Jour", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxdebut.setBounds(10, 154, 170, 42);
		contentPane.add(comboBoxdebut);
		
		JComboBox comboBoxmdebut = new JComboBox();
		comboBoxmdebut.setFont(new Font("Tahoma", Font.BOLD, 13));
		
//***********************		 utiliser un combo pour limité l'utilisation  et pour respecter les contraintre de mois
		
		comboBoxmdebut.setModel(new DefaultComboBoxModel(new String[] {"Mois", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxmdebut.setBounds(10, 224, 170, 42);
		contentPane.add(comboBoxmdebut);
		
		txtAnnedebut = new JTextField();
		txtAnnedebut.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAnnedebut.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnnedebut.setText("Ann\u00E9e");
		txtAnnedebut.setBounds(10, 294, 170, 42);
		contentPane.add(txtAnnedebut);
		txtAnnedebut.setColumns(10);
		
		JComboBox comboBoxfin = new JComboBox();
		comboBoxfin.setFont(new Font("Tahoma", Font.BOLD, 13));
		
//*****************utiliser un combo pour limité l'utilisation  et pour respecter les contraintre de jour 
		
		comboBoxfin.setModel(new DefaultComboBoxModel(new String[] {"Jour", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxfin.setBounds(247, 154, 170, 42);
		contentPane.add(comboBoxfin);
		
		JComboBox comboBoxmfin = new JComboBox();
		comboBoxmfin.setFont(new Font("Tahoma", Font.BOLD, 13));
		
//******************utiliser un combo pour limité l'utilisation  et pour respecter les contraintre de mois 
		

		comboBoxmfin.setModel(new DefaultComboBoxModel(new String[] {"Mois", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxmfin.setBounds(246, 224, 171, 42);
		contentPane.add(comboBoxmfin);
		
		txtAnnfin = new JTextField();
		txtAnnfin.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAnnfin.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnnfin.setText("Ann\u00E9e");
		txtAnnfin.setColumns(10);
		txtAnnfin.setBounds(247, 294, 170, 42);
		contentPane.add(txtAnnfin);
		
		JLabel lblNewLabel_1 = new JLabel("Consulter vous rendez_vous dans la periode que vous voulez");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 87, 390, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("au");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(210, 169, 27, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("au");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(209, 239, 27, 27);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("au");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_2.setBounds(213, 309, 27, 27);
		contentPane.add(lblNewLabel_2_2);
//********************ici c'est le bouton  responsable d'afficher tout les rendez vous pour une peroide donneé
		JButton btnpariode = new JButton("Consulter pour une periode");
		btnpariode.setIcon(new ImageIcon(Agenda.class.getResource("/Actions-view-calendar-month-icon.png")));
		btnpariode.setBackground(new Color(153, 153, 255));
		btnpariode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                    connection =sqlConnection.bdConnector();
				
				try {
					
//************************ recupere la date dans ecrit dans l'interface et concatiner jour avc mois et annee pour la 1 ere date 
				String d1 = String.format("%s-%s-%s",comboBoxdebut.getSelectedItem().toString(),comboBoxmdebut.getSelectedItem().toString(),txtAnnedebut.getText());
				
//*************************** recupere la date dans ecrit dans l'interface et concatiner jour avc mois et annee pour la 2eme date 
		
				String d2= String.format("%s-%s-%s",comboBoxfin.getSelectedItem().toString(),comboBoxmfin.getSelectedItem().toString(),txtAnnfin.getText());
				
//**************************** selectionner cette peroid dans la table rendez vous : 
				
				String sql="select * from RDV where DATE_RDV between '"+d1+"' and'"+d2+"'";
				ps=connection.prepareStatement(sql);
//******************************* execcute la commende sql ; 
				ResultSet rs = ps.executeQuery(); 
//***************************** afficher le resultat dans la table créé 
					table.setModel(DbUtils.resultSetToTableModel(rs));
				

			    
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnpariode.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnpariode.setBounds(117, 402, 255, 42);
		contentPane.add(btnpariode);
		
		JButton btnjour = new JButton("Consulter pour une journee");
		btnjour.setIcon(new ImageIcon(Agenda.class.getResource("/Actions-view-calendar-day-icon.png")));
		btnjour.setBackground(new Color(153, 153, 255));
		btnjour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	               connection =sqlConnection.bdConnector();
					
					try {
						
//**************************** recupere la date dans ecrit dans l'interface et concatiner jour avc mois et annee pour un jour donneé

					String d1 = String.format("%s-%s-%s",comboBoxdebut.getSelectedItem().toString(),comboBoxmdebut.getSelectedItem().toString(),txtAnnedebut.getText());
					
//**************************** selectionner cette journee dans la table rendez vous : 
					
					String sql="select * from RDV where DATE_RDV = '"+d1+"'";
					ps=connection.prepareStatement(sql);
					
//****************************** execcute la commende sql ; 
					
					ResultSet rs = ps.executeQuery();
					
// ******************************afficher le resultat dans la table créé
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					

				    
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnjour.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnjour.setBounds(117, 530, 255, 42);
		contentPane.add(btnjour);
		
		menu = new JButton("menu medecin");
		menu.setBackground(new Color(153, 255, 153));
//**************************** ajouter une icon***********************************
		menu.setIcon(new ImageIcon(Agenda.class.getResource("/home-next-icon.png")));
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu d= new Menu();
				d.setVisible(true);
				setVisible(false);
			}
		});
		menu.setFont(new Font("Tahoma", Font.BOLD, 13));
		menu.setBounds(628, 584, 181, 41);
		contentPane.add(menu);
		
		JButton fermer = new JButton("Se Deconnecter");
		fermer.setBackground(new Color(255, 153, 153));
		
		// *****************************ajouter une icon ************************
		fermer.setIcon(new ImageIcon(Agenda.class.getResource("/Apps-Dialog-Shutdown-icon.png")));
		fermer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();}
			}
		});
		fermer.setFont(new Font("Tahoma", Font.BOLD, 13));
		fermer.setBounds(848, 584, 181, 41);
		contentPane.add(fermer);
		
		c = new JLabel("clock");
		c.setForeground(Color.BLUE);
		c.setFont(new Font("Tahoma", Font.BOLD, 14));
		c.setBounds(10, 0, 332, 53);
		contentPane.add(c);
		
		 menu_1 = new JButton("menu secretaire");
		menu_1.setIcon(new ImageIcon(Agenda.class.getResource("/home-next-icon.png")));
		menu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu2 d= new Menu2();
				d.setVisible(true);
				setVisible(false);
			}});
		menu_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		menu_1.setBackground(new Color(153, 255, 153));
		menu_1.setBounds(397, 584, 181, 41);
		contentPane.add(menu_1);	
		
		JLabel lblNewLabel_4 = new JLabel("");
//********************************** ajouter une icon******************************
		
		lblNewLabel_4.setIcon(new ImageIcon(Agenda.class.getResource("/rrr.png")));
		lblNewLabel_4.setBounds(0, 0, 1028, 641);
		contentPane.add(lblNewLabel_4);
		

		clock();
	}
}
