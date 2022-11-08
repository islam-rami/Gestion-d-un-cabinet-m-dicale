package gl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * 
 * ici c'est jfram de d'inscription de patient oon 
 *va la manipuler grace a la class patient qui
 * contient toutes les methodes qu'on aurra besoin dans cet jfram 
 *
 */

@SuppressWarnings({ "unused", "serial" })
public class Patient_ extends JFrame {

	
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JLabel lblSexe;
	private JTextField textFieldDateNai;
	private JTextField textFieldAdresse;
	private JTextField textFieldTelephone;
	private JTextField textFieldEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patient_ frame = new Patient_();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection connection = null; 
private JTextField textFieldAnnInscri;
private JTable table;
private JTextField textFieldID;
private JTextField textFieldSearch;
private JLabel c ;
@SuppressWarnings("rawtypes")
JComboBox combojourIn;
@SuppressWarnings("rawtypes")
JComboBox comboBox ;
@SuppressWarnings("rawtypes")
JComboBox comboBoxnai;
@SuppressWarnings("rawtypes")
JComboBox comboBoxnaiJ;
@SuppressWarnings("rawtypes")
JComboBox comboBoxSexe; 
private JLabel lblNewLabel_4;

	/**
	 * Create the frame.
	 */
// c'est uune fonction pour afficher le contenu de la table  patient a l'aide d'une commende sql "selsect from .."

	 public void  refreshTable(){
		 try {
				String sql="select * from PATIENT";
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
   }
//**************************** recupere une annee d'une date "fonction personnelle"
 
		@SuppressWarnings("deprecation")
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

// ***************recupere un mois d'une date "fonction personnelle"*********

		@SuppressWarnings("deprecation")
		public static String Stringtomois (String d)
		{
			 
			Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}  
		    return ""+(date1.getMonth()+1);  
		}
		
		// recupere un jour d'une date "fonction personnelle"

		@SuppressWarnings("deprecation")
		public static String Stringtojour (String d)
		{
			
			Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}  
		    return ""+date1.getDate();  
		}
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Patient_() {
		setTitle("Inscription d'un patient");
		connection =sqlConnection.bdConnector();
		  showDate(); 
//**************************************** declaration des variable de jframe ********************************************************************************************
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1290, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		JLabel lblNomDuPatient = new JLabel("Nom  ");
		lblNomDuPatient.setBounds(36, 189, 65, 26);
		lblNomDuPatient.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNomDuPatient);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(162, 184, 204, 31);
		textFieldNom.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Prenom");
		lblNewLabel.setBounds(36, 227, 65, 31);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(162, 227, 204, 31);
		textFieldPrenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		lblSexe = new JLabel("Sexe");
		lblSexe.setBounds(36, 273, 57, 23);
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblSexe);
		
		JLabel lblNewLabel_1 = new JLabel("Date de naissance");
		lblNewLabel_1.setBounds(35, 311, 132, 21);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		textFieldDateNai = new JTextField();
		textFieldDateNai.setText("Ann\u00E9e");
		textFieldDateNai.setBounds(301, 311, 65, 26);
		textFieldDateNai.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(textFieldDateNai);
		textFieldDateNai.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Adresse");
		lblNewLabel_2.setBounds(36, 359, 59, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		
		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(162, 356, 204, 31);
		textFieldAdresse.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(textFieldAdresse);
		textFieldAdresse.setColumns(10);
		
		JLabel lblNTelephone = new JLabel("NÂ° telephone");
		lblNTelephone.setBounds(35, 406, 92, 14);
		lblNTelephone.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNTelephone);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setBounds(161, 394, 205, 31);
		contentPane.add(textFieldTelephone);
		textFieldTelephone.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(37, 431, 46, 14);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(162, 436, 204, 31);
		textFieldEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lbljourDinscription = new JLabel("Date d'inscription");
		lbljourDinscription.setBounds(36, 479, 126, 19);
		lbljourDinscription.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbljourDinscription);
		
		JLabel lblAjouterUnNouveau = new JLabel("Ajouter un nouveau patient");
		lblAjouterUnNouveau.setBounds(252, 2, 240, 31);
		lblAjouterUnNouveau.setForeground(new Color(0, 128, 0));
		lblAjouterUnNouveau.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblAjouterUnNouveau);
//***********************************************************************************************************************************************************
		
// ----------------------------------------------------------la modification d'un patient ------------------------------------------------------------
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBackground(new Color(153, 153, 255));
		btnEnregistrer.setBounds(36, 547, 141, 40);
		btnEnregistrer.setIcon(new ImageIcon(Patient_.class.getResource("/Save-icon.png")));
		btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
//**************************************  recupere la date dans ecrit dans l'interface et concatiner jour avc mois et annee 


				String d = String.format("%s-%s-%s",combojourIn.getSelectedItem().toString() ,comboBox.getSelectedItem().toString(),textFieldAnnInscri.getText());

//***************************************meme chose pour l'heure heure H min

				
				String dnais = String.format("%s-%s-%s",comboBoxnaiJ.getSelectedItem().toString() ,comboBoxnai.getSelectedItem().toString(),textFieldDateNai.getText());
			
// ***********************************une instanciation de la classe patient  afin de beneficier de la methode modifer consultation

				Patient p=new Patient();
					
				
				p.ajouterPatient( textFieldID.getText() ,textFieldNom.getText(), textFieldPrenom.getText(),comboBoxSexe.getSelectedItem().toString(),dnais,textFieldAdresse.getText(), textFieldTelephone.getText(),textFieldEmail.getText(),d);
				
//*************************************** renitialiser la table aprs les modifications

				
				refreshTable();
}
		});
		contentPane.add(btnEnregistrer);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBackground(new Color(153, 153, 255));
		btnNewButton.setIcon(new ImageIcon(Patient_.class.getResource("/edit-validated-icon.png")));
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient p=new Patient();
//**************************************  recupere la date dans ecrit dans l'interface et concatiner jour avc mois et annee 
				
				String d = String.format("%s-%s-%s",combojourIn.getSelectedItem().toString() ,comboBox.getSelectedItem().toString(),textFieldAnnInscri.getText());
				
//***************************************meme chose pour l'heure heure H min

				
				String dnais = String.format("%s-%s-%s",comboBoxnaiJ.getSelectedItem().toString() ,comboBoxnai.getSelectedItem().toString(),textFieldDateNai.getText());
				
				p.modifierPatient(textFieldID.getText() , textFieldNom.getText(), textFieldPrenom.getText(),comboBoxSexe.getSelectedItem().toString(),dnais,textFieldAdresse.getText(), textFieldTelephone.getText(),textFieldEmail.getText(),d);
				
//*************************************** renitialiser la table aprs les modifications

		
				refreshTable();
			}
		});
		btnNewButton.setBounds(207, 547, 159, 40);
		contentPane.add(btnNewButton);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(153, 153, 255));
		btnSupprimer .setIcon(new ImageIcon(DossierMedicale.class.getResource("/Recycle-Bin-Full-icon.png")));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Patient p=new Patient();
				p.supprimerPatient(textFieldID.getText());
				refreshTable();
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSupprimer.setBounds(151, 607, 141, 40);
		contentPane.add(btnSupprimer);
		
		textFieldAnnInscri = new JTextField();
		textFieldAnnInscri.setText("Ann\u00E9e");
		textFieldAnnInscri.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAnnInscri.setColumns(10);
		textFieldAnnInscri.setBounds(302, 478, 65, 26);
		contentPane.add(textFieldAnnInscri);
		
		JButton btnConsulter = new JButton("consulter");
		btnConsulter.setBackground(new Color(153, 153, 255));
		btnConsulter .setIcon(new ImageIcon(DossierMedicale.class.getResource("/view-icon.png")));
		btnConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshTable();
			}
		});
		btnConsulter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConsulter.setBounds(702, 479, 177, 40);
		contentPane.add(btnConsulter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {


		});
		scrollPane.setBounds(407, 141, 862, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
//*************************c'est pour la selection d'une ligne dans la table afficher

				
				int ligne =table.getSelectedRow();
				String id ,nom,prenom,sexe,datenais,adresse,numtel,email,datein;
				
				
				id=table.getModel().getValueAt(ligne, 0).toString();
				nom=table.getModel().getValueAt(ligne, 1).toString();
				prenom=table.getModel().getValueAt(ligne, 2).toString();
				sexe=table.getModel().getValueAt(ligne, 3).toString();
				datenais=table.getModel().getValueAt(ligne, 4).toString();
				adresse=table.getModel().getValueAt(ligne, 5).toString();
				numtel=table.getModel().getValueAt(ligne, 6).toString();
				email=table.getModel().getValueAt(ligne, 7).toString();
				datein=table.getModel().getValueAt(ligne, 8).toString();
				
				
				textFieldID.setText(id);
				textFieldNom.setText(nom);
				textFieldPrenom.setText(prenom);
				
				textFieldDateNai.setText(Stringtoannee(datenais));
				textFieldAdresse.setText(adresse);
				textFieldTelephone.setText(numtel);
				textFieldEmail.setText(email);
				textFieldAnnInscri.setText(Stringtoannee(datein));
				
	
			}
		});
		scrollPane.setViewportView(table);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(162, 141, 204, 32);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Id Patient");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(36, 148, 118, 21);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBoxSearch = new JComboBox();
		comboBoxSearch.setBackground(new Color(255, 255, 204));
		comboBoxSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"ID_PATIENT", "NOM", "PRENOM", "SEXE", "DATE_DE_NAISSANCE", "ADRESSE", "TELEPHONE", "EMAIL", "DATEDINSCRIPTION"}));
		comboBoxSearch.setBounds(627, 56, 177, 51);
		contentPane.add(comboBoxSearch);
	
		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
//************************************ effectuer une recherche avec tout type de disponable dans la table

			public void keyReleased(KeyEvent e) {		 		
				try {
					String selection = (String)comboBoxSearch.getSelectedItem();
				String sql="select *from patient vous where "+selection+"=? ";
				
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, textFieldSearch.getText());
                ResultSet rs= pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));

				
			} catch (Exception e3) {
				e3.printStackTrace();
			}
				
			}
		});
		textFieldSearch.setBounds(826, 57, 195, 50);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		c = new JLabel("clock");
		c.setFont(new Font("Tahoma", Font.BOLD, 13));
		c.setBounds(4, 0, 263, 51);
		contentPane.add(c);
		// fermer le Jframe avec le boutton deconnecter 
		JButton fermer = new JButton("Se Deconnecter");
		fermer.setBackground(new Color(255, 153, 153));
		fermer.setIcon(new ImageIcon(DossierMedicale.class.getResource("/Apps-Dialog-Shutdown-icon.png")));
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();}
			}
		});
		fermer.setFont(new Font("Tahoma", Font.BOLD, 13));
		fermer.setBounds(1079, 619, 190, 41);
		contentPane.add(fermer);
		
		JButton menu = new JButton("retour au menu");
		menu.setBackground(new Color(153, 255, 153));
		menu.setIcon(new ImageIcon(DossierMedicale.class.getResource("/home-next-icon.png")));
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu d= new Menu();
				d.setVisible(true);
				setVisible(false);
			}
		});
		menu.setFont(new Font("Tahoma", Font.BOLD, 13));
		menu.setBounds(876, 620, 177, 40);
		contentPane.add(menu);
	
		 combojourIn = new JComboBox();
		 combojourIn.setFont(new Font("Tahoma", Font.BOLD, 11));
		//*****************utiliser un combo pour limité l'utilisation  et pour respecter les contraintre de jour 

		combojourIn.setModel(new DefaultComboBoxModel(new String[] {"Jour ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		combojourIn.setBounds(162, 479, 55, 25);
		contentPane.add(combojourIn);
		
		 comboBox = new JComboBox();
		 comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		 
		//******************utiliser un combo pour limité l'utilisation  et pour respecter les contraintre de mois 

		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mois", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox.setBounds(227, 479, 65, 25);
		contentPane.add(comboBox);
		
		 comboBoxnai = new JComboBox();
		 comboBoxnai.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxnai.setModel(new DefaultComboBoxModel(new String[] {"Mois", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxnai.setBounds(226, 312, 65, 26);
		contentPane.add(comboBoxnai);
		
		 comboBoxnaiJ = new JComboBox();
		 comboBoxnaiJ.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxnaiJ.setModel(new DefaultComboBoxModel(new String[] {"Jour ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		comboBoxnaiJ.setBounds(161, 312, 56, 26);
		contentPane.add(comboBoxnaiJ);
		
		 comboBoxSexe = new JComboBox();
		comboBoxSexe.setFont(new Font("Tahoma", Font.BOLD, 13));
//*****************************utiliser un combo pour limité l'utilisation  et pour respecter les contraintre de sexe soit homme ou femme 

		comboBoxSexe.setModel(new DefaultComboBoxModel(new String[] {"Homme", "Femme"}));
		comboBoxSexe.setBounds(162, 269, 204, 31);
		contentPane.add(comboBoxSexe);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Patient_.class.getResource("/Male-user-search-icon.png")));
		lblNewLabel_4.setBounds(539, 43, 72, 64);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Patient_.class.getResource("/663696.jpg")));
		
		lblNewLabel_5.setBounds(-47, -328, 1423, 1061);
		contentPane.add(lblNewLabel_5);
		refreshTable(); 
		clock();
	}
  void showTime() {
	  
  }
 void showDate() {
	 Date date= new Date();
	 SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
 } 
}
