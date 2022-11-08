package gl;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;
/*****************************************************
 * 
 * 
 * dans cette jframe on presente 
 * 
 *  les differente  façcon pour 
 
 * manipuler la class rendez que 
 * 
 * c soit ajouter un rv ou 
 * 
 * le supprimé ou le modifier 
 * "' 
 *********************************************************************
 */

@SuppressWarnings("serial")
public class RendezVous extends JFrame {
	private JTextField textFieldNom;
	private JTextField textFieldEmail;
	private JTextField textFieldTel;
	private JTextField txtLaDonneeRechercher;
	private JTable table;
	private JTextField textFieldAnnee;
	@SuppressWarnings("unused")
	private JLabel lblNewLabel_7;
	private JLabel c ;
	JButton menu;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_2 ;
		@SuppressWarnings("rawtypes")
		JComboBox comboBoxmois;
		@SuppressWarnings("rawtypes")
		JComboBox comboBoxmin;
		@SuppressWarnings("rawtypes")
		JComboBox comboBoxheure;
		@SuppressWarnings("rawtypes")
		JComboBox comboBoxjour;
		@SuppressWarnings("rawtypes")
		JComboBox comboBox;
	/**
	 * Launch the application.
	 */
		///***************************** pour ajouter l'heur et la date en cours **************************************
		
		 public void clock()
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
//		 ******************************************************************************************************************
		@SuppressWarnings("deprecation")
		
//***********************************une methode pour extraire  une d'une date ******************
	
		public static String Stringtoannee (String d)
		{
			
			Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}  
		    return ""+(date1.getYear()+1900);  
		}
		// initialiser la conection a null ;
	Connection connection = null; 
	private JTextField textFieldPrenom;
	private JLabel lblNewLabel_8;
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					//UIManager.setLookAndFeel(new NimbusLookAndFeel());
					RendezVous window = new RendezVous();
					RendezVous frame  = new RendezVous();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 ************************************************** metode pour afficher la table de RDV*****************************************************
	 */
	 public void  refreshTable(){try {
			String rq = "SELECT * FROM RDV";
			PreparedStatement pp = connection.prepareStatement(rq);
			ResultSet rs = pp.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}
	//*************************************************************************************************************************************** 
	public RendezVous() {
		setResizable(false);
		setTitle("RENDEZ-VOUS");
		getContentPane().setBackground(new Color(204, 255, 255));
		connection =sqlConnection.bdConnector();
		initialize();
	}
//************************************************************************************************************************
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		setType(Type.POPUP);
		setForeground(Color.BLACK);
		setBackground(new Color(153, 255, 255));
		setBounds(100, 100, 1034, 744);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(92, 133, 206, 31);
		getContentPane().add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom");
		lblNewLabel_1.setBounds(10, 181, 80, 21);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date de RDV");
		lblNewLabel_2.setBounds(10, 240, 87, 16);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("H");
		lblNewLabel_3.setBounds(205, 302, 18, 13);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_3);
		txtLaDonneeRechercher = new JTextField();
		txtLaDonneeRechercher.setBounds(690, 38, 208, 44);
		// effectuer une recherche ave une commende sql le type de recheche despoible on les trouve dans le combobox de JFrame
		txtLaDonneeRechercher.addKeyListener(new KeyAdapter() {
			// effectuer une recherche avec tout type de disponable dans la table
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String selection = (String)comboBox_2.getSelectedItem();
				String sql="select *from rdv vous where "+selection+"=? ";
				
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, txtLaDonneeRechercher.getText());
                ResultSet rs= pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
//                while (rs.next()) {
//
//					
//				}
				
			} catch (Exception e3) {
				e3.printStackTrace();
			}
				
			
			}
		});
		txtLaDonneeRechercher.setHorizontalAlignment(SwingConstants.CENTER);
		txtLaDonneeRechercher.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtLaDonneeRechercher.setText("la donnee rechercher");
		getContentPane().add(txtLaDonneeRechercher);
		txtLaDonneeRechercher.setColumns(10);
		
		JButton btnEnregister = new JButton("Enregister  RDV");
		btnEnregister.setBounds(10, 534, 140, 39);
		btnEnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//*****************************concatiner le jour et mois et annes*************************************************************
			
				
				String s = String.format("%s-%s-%s",comboBoxjour.getSelectedItem().toString(),comboBoxmois.getSelectedItem().toString(),textFieldAnnee.getText());

//****************************************//
				
				String k  = String.format("%sH%s", comboBoxheure.getSelectedItem().toString(),comboBoxmin.getSelectedItem().toString());
				
//*******************************************concatiner heure avec min ************************************************************				
		
				String rep=comboBox.getSelectedItem().toString();
				if(rep.equals("NON")) {

//***************************** pour supprimer le (x) pour un patient qui a fait ses inscription et le remplacer par (V) veut dire il les a fait bien avant **********
					
					String chaine = textFieldNom.getText().concat( " (V)");
					
					String chaine1 = textFieldPrenom.getText().concat(" (V)");
					 
					
				
				ClassRv v1 = new ClassRv(chaine,chaine1,s, k, textFieldEmail.getText(), textFieldTel.getText());
					v1.ajouterrdv();
					
// ******************************************mettre (X) pour un nouveau patient qui se presente pour la premiere fois ***********************************
				
				}else if(rep.equals("OUI"))  {
					// concatiner le symbole
				String N = textFieldNom.getText().concat(" (X)");
				String P=textFieldPrenom.getText().concat(" (X)");
		
				ClassRv v2 = new ClassRv(N,P,s, k, textFieldEmail.getText(), textFieldTel.getText());
 //****************************************** ajouter le nouveau patient ***************************************************************************
				v2.ajouterrdv();}
				else {JOptionPane.showMessageDialog(null, "il faut remplir la case réponse !!");}
	
			    refreshTable();
			}
		});
//*************************************************************************************************************************************************		
		btnEnregister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(btnEnregister);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(106, 348, 192, 31);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(393, 35, 221, 49);
		comboBox_2.setBackground(new Color(255, 255, 153));
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"le type de recherche ", "date_rdv", "heure", "nom", "prenom", "email", "tel"}));
		getContentPane().add(comboBox_2);
		
		textFieldTel = new JTextField();
		textFieldTel.setBounds(106, 390, 192, 31);
		getContentPane().add(textFieldTel);
		textFieldTel.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("EMAIL");
		lblNewLabel_4.setBounds(10, 354, 63, 21);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("TEL");
		lblNewLabel_5.setBounds(10, 400, 45, 21);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(335, 111, 636, 457);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			@Override
		//************************************	 afficher la ligne selectionner dans les textfeild existant **********************************************************
			public void mouseClicked(MouseEvent e) {

				
				int ligne =table.getSelectedRow();
				String nom,prenom,email,tel,jour,heu;
				
				
				nom=table.getModel().getValueAt(ligne, 2).toString();
				prenom=table.getModel().getValueAt(ligne, 3).toString();
				jour=table.getModel().getValueAt(ligne, 0).toString();
//				heu=table.getModel().getValueAt(ligne, 3).toString();
				tel=table.getModel().getValueAt(ligne, 5).toString();
				email=table.getModel().getValueAt(ligne, 4).toString();
				
				
				
				
				textFieldNom.setText(nom);
				textFieldPrenom.setText(prenom);
				textFieldAnnee.setText(Stringtoannee(jour));
				textFieldEmail.setText(email);
				textFieldTel.setText(tel);
				
				
				}
			
		});
		scrollPane.setViewportView(table);
//********************************************************************************************************************************************************
		
		JButton btnNewButton_1 = new JButton("CONSULTER RV");
		btnNewButton_1.setBounds(497, 579, 192, 33);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//************************************* afficher la table en cliquant sur le boutton consulter rv ******************************************
				
				refreshTable();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(btnNewButton_1);
		
		textFieldAnnee = new JTextField();
		textFieldAnnee.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAnnee.setForeground(Color.BLUE);
		textFieldAnnee.setText("Année\r\n");
		textFieldAnnee.setBounds(242, 234, 73, 32);
		getContentPane().add(textFieldAnnee);
		textFieldAnnee.setColumns(10);
		// *************************************************la supression********************************************************
		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.setBounds(177, 535, 127, 38);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//*************************************** supprimer un rdv  *******************************************************
				
				//************************************ concatiner le type date *****************************************************
				String s = String.format("%s-%s-%s",comboBoxjour.getSelectedItem().toString(),comboBoxmois.getSelectedItem().toString(),textFieldAnnee.getText());
				
			// ***************************************** concatiner le type heure ******************************************************
				
				String k  = String.format("%sH%s", comboBoxheure.getSelectedItem().toString(),comboBoxmin.getSelectedItem().toString());
				
				
				ClassRv v = new ClassRv(textFieldNom.getText(),textFieldPrenom.getText(),s, k, textFieldEmail.getText(), textFieldTel.getText());
	              
				// methode suppression
				
				v.supprimer(s, k);
				
				
			// afficher la table apres les modification
			refreshTable();
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 12));
	// faire des modification sur un rdv 
		getContentPane().add(btnSupprimer);
		
		JButton btnMIseAjour = new JButton("Modifier");
		btnMIseAjour.setBounds(124, 603, 117, 39);
		
		btnMIseAjour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				// ***********************************************concatiner le type date ****************************************************************
				 
				String dat = String.format("%s-%s-%s",comboBoxjour.getSelectedItem().toString(),comboBoxmois.getSelectedItem().toString(),textFieldAnnee.getText());
			
				//***********************************************  concatiner le type heure ***********************************************************
				
				String heure  = String.format("%sH%s", comboBoxheure.getSelectedItem().toString(),comboBoxmin.getSelectedItem().toString());
				
				
				
				ClassRv v = new ClassRv(textFieldNom.getText(),textFieldPrenom.getText(),dat, heure, textFieldEmail.getText(), textFieldTel.getText());
			   
				// *******************************appelle de la methode modifier-m)*********************************************************************
				
				v.modifier1(dat,heure);
				
				// ***************************************** actualiser la table**************************************************
		
				refreshTable();
			}
		});
	
		//***********************************************************************************************************************************************
		
		btnMIseAjour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(btnMIseAjour);
		
		JButton fermer = new JButton("Se Deconnecter");
		fermer.setBackground(new Color(255, 102, 102));
		fermer.setHorizontalAlignment(SwingConstants.LEFT);
		fermer.setBounds(807, 639, 191, 44);
		fermer.setIcon(new ImageIcon(RendezVous.class.getResource("/Apps-Dialog-Shutdown-icon.png")));
		fermer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// *****************************************quitter le Jframe carrement ***************************************************
				
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();}
			}
		});
		fermer.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(fermer);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(92, 175, 206, 32);
		getContentPane().add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(10, 143, 80, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblNewLabel);
		/***************************************************************************************************************************
		 * 
		 * 		 * 	/* on utilise les combobox pour la date  et  heure pour respecter tout les contrainte et eviter tout type d'erreur consernant le type 
		 * */
		

		 comboBoxmois = new JComboBox();
		 comboBoxmois.setFont(new Font("Tahoma", Font.BOLD, 12));
		 comboBoxmois.setModel(new DefaultComboBoxModel(new String[] {"Mois", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		 comboBoxmois.setForeground(Color.BLUE);
		comboBoxmois.setBounds(170, 234, 62, 32);
		getContentPane().add(comboBoxmois);
		
		 comboBoxjour = new JComboBox();
		 comboBoxjour.setFont(new Font("Tahoma", Font.BOLD, 12));
		 comboBoxjour.setModel(new DefaultComboBoxModel(new String[] {"Jour", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		 comboBoxjour.setForeground(Color.BLUE);
		comboBoxjour.setBounds(97, 235, 63, 31);
		getContentPane().add(comboBoxjour);
		
		 comboBoxheure = new JComboBox();
		 comboBoxheure.setForeground(Color.BLUE);
		comboBoxheure.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBoxheure.setModel(new DefaultComboBoxModel(new String[] {"Heure", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
		comboBoxheure.setBounds(109, 295, 86, 31);
		getContentPane().add(comboBoxheure);
		
		 comboBoxmin = new JComboBox();
		 comboBoxmin.setForeground(Color.BLUE);
		comboBoxmin.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBoxmin.setModel(new DefaultComboBoxModel(new String[] {"Min", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "00"}));
		comboBoxmin.setBounds(233, 294, 80, 31);
		getContentPane().add(comboBoxmin);
		
			menu = new JButton("menu  secretaire");
			menu.setBackground(new Color(153, 255, 153));
			menu.setIcon(new ImageIcon(RendezVous.class.getResource("/home-next-icon.png")));
			menu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menu2 d= new Menu2();
					
					// permettre la visuabilité de Jfram menu
		
					d.setVisible(true);
					setVisible(false);
				}
			});
			menu.setHorizontalAlignment(SwingConstants.LEFT);
		menu.setFont(new Font("Tahoma", Font.BOLD, 13));
		menu.setBounds(591, 639, 206, 44);
		getContentPane().add(menu);
			clock();	

		c = new JLabel("clock");
		// **********************************************ajouter une heure au JFrame****************************************
		c.setFont(new Font("Tahoma", Font.BOLD, 13));
		c.setBounds(4, 0, 263, 51);
		getContentPane().add(c);
	////*************************** pour que le patientsera inscrit ou non il faut savoi si c'est un nouveau patient ou non ***************************
		JLabel lblNewLabel_6 = new JLabel("c'est le 1er RDV");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 466, 107, 21);
		getContentPane().add(lblNewLabel_6);
		
		 comboBox = new JComboBox();
		comboBox.setForeground(Color.RED);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"REPONSE", "OUI", "NON"}));
		comboBox.setBounds(143, 462, 117, 31);
		getContentPane().add(comboBox);
		
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(DossierMedicale.class.getResource("/rdv.png")));
		lblNewLabel_8.setBounds(0, 0, 1018, 715);
		getContentPane().add(lblNewLabel_8);
		
	

		// ***********************actualiser la table ******************************************************************

		refreshTable();
	
	}
}
