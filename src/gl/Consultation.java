package gl;

import java.awt.Color;
/*************************************************************************************************************************** 
 *  @author INES
 *  je presente ici le jframe de la classe consultation                                                                    *
 *                                                                                                                         *
 *  ici le medecin fait une consultation pour chaque                                                                       *
 *                                                                                                                         *
 *  patient evedament les information peuvent entre enregistrer                                                            *
 *                                                                                                                         *   
 *  modifier mais la dupprission se fait juste pour un patient qu'on veut supprimé definitivement                          *
 *                                                                                                                         *
 *  car le faite que je fait une consultation pour un patient elle faut qu'on enregistre le tout                           *  
 *                                                                                                                         *   
 *  et n verra juste apres la suppression de tout les données au choix d'u patient qui n'est pas revenu depuis 5ans ;      *
 *                                                                                                                         *
 *  ************************************************************************************************************************
 */

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Consultation extends JFrame {

	private JPanel contentPane;
	private JTextField textNumCon;
	private JTextField textID_Patient;
	private JTextArea textFieldTraitement;
	private JTextArea textFieldMaladie;
	private JTextField textFieldMontant;
	private JTextField textFieldAnnee;
	private JTextArea textFieldOBS;
	private JLabel c ;
	/**
	 * Launch the application.
	 */
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consultation frame = new Consultation();
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
	Connection connection = null; 
	private JTable table;
	private JTextArea textFieldCHro;
	private JTextArea textFieldDose;
	private JTextField txtLaDonneeRechercher;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_2 ;	
	@SuppressWarnings("rawtypes")
	JComboBox comboBoxmin;
    @SuppressWarnings("rawtypes")
	JComboBox comboBoxheure;
    
    // c'est uune fonction pour afficher le contenu de la table  consultation a l'aide d'une commende sql "selsect from .."
	public void  refreshTable(){
		 try {
				String sql="select * from CONSULTATION";
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
  }
	  // c'est uune fonction pour afficher le contenu de la table  cosultation a l'aide d'une commende sql "selsect from .. mais avec coloration pour les dates >=5ans "
	public void  colorTable(){
		 try {
				String sql="select * from CONSULTATION";
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
 }
	
	
	@SuppressWarnings("deprecation")
	// recupere une annee d'une date "fonction personnelle"
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
	
	@SuppressWarnings("deprecation")
	
//************************************** recupere un mois d'une date "fonction personnelle"

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
	@SuppressWarnings("deprecation")
	
//**************************************  recupere un jour d'une date "fonction personnelle"

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Consultation() {
		setFocusTraversalPolicyProvider(true);
		setTitle("Consultation");
	
//**************************************  la connection  a la base de donnee grae a la classe sqlConnetion
		
		connection =sqlConnection.bdConnector();
		
//************************************** declaretion de contenu de l'interface
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1296, 708);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		JLabel lblConsultation = new JLabel("Consultation");
		lblConsultation.setBounds(411, 0, 194, 31);
		lblConsultation.setForeground(new Color(102, 51, 153));
		lblConsultation.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblConsultation);
		
		textNumCon = new JTextField();
		textNumCon.setBounds(379, 44, 194, 36);
		textNumCon.setColumns(10);
		contentPane.add(textNumCon);
		
		JLabel lblNumConsu = new JLabel("Nun Consultation");
		lblNumConsu.setBounds(245, 42, 121, 31);
		lblNumConsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNumConsu);
		
		JLabel lblNewLabel = new JLabel("ID Patient ");
		lblNewLabel.setBounds(599, 40, 100, 31);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		textID_Patient = new JTextField();
		textID_Patient.setBounds(709, 41, 181, 39);
		textID_Patient.setColumns(10);
		contentPane.add(textID_Patient);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(137, 342, 362, 67);
		contentPane.add(scrollPane_2);
		
		textFieldTraitement = new JTextArea();
		scrollPane_2.setViewportView(textFieldTraitement);
		textFieldTraitement.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("TRAITEMENT");
		lblNewLabel_2.setBounds(6, 365, 95, 16);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("MALADIE");
		lblNewLabel_3.setBounds(6, 285, 121, 29);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(137, 271, 362, 60);
		contentPane.add(scrollPane_4);
		
		textFieldMaladie = new JTextArea();
		scrollPane_4.setViewportView(textFieldMaladie);
		textFieldMaladie.setColumns(10);
		
		textFieldMontant = new JTextField();
		textFieldMontant.setBounds(137, 569, 265, 37);
		textFieldMontant.setColumns(10);
		contentPane.add(textFieldMontant);
		
		JLabel lblNewLabel_4 = new JLabel("MONTANT PAYEE");
		lblNewLabel_4.setBounds(6, 572, 134, 34);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_4);
		
		textFieldAnnee = new JTextField();
		textFieldAnnee.setBounds(302, 119, 100, 39);
		textFieldAnnee.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAnnee.setText("Ann\u00E9e");
		textFieldAnnee.setColumns(10);
		contentPane.add(textFieldAnnee);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(137, 491, 362, 67);
		contentPane.add(scrollPane_3);
		
		textFieldOBS = new JTextArea();
		scrollPane_3.setViewportView(textFieldOBS);
		textFieldOBS.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Observation");
		lblNewLabel_6.setBounds(10, 508, 95, 29);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_6);
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(220, 118, 72, 38);
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(138, 118, 72, 39);
  // ---------------------------------------------------------------la modification d'une consultation------------------------------------------------------------
		JButton btnNewButton_2 = new JButton("UPDATE");
		btnNewButton_2.setBounds(284, 617, 144, 49);
		btnNewButton_2.setBackground(new Color(153, 153, 204));
		btnNewButton_2.setIcon(new ImageIcon(Consultation.class.getResource("/edit-validated-icon.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				
				try {
//**************************************  recupere la date dans ecrit dans l'interface et concatiner jour avc mois et annee 
	
				String d = String.format("%s-%s-%s",comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textFieldAnnee.getText());
//***************************************meme chose pour l'heure heure H min
				String k  = String.format("%sH%s", comboBoxheure.getSelectedItem().toString(),comboBoxmin.getSelectedItem().toString());
// ***********************************une instanciation de la classe consultation afin de beneficier de la methode modifer consultation
				ConsultationTEST p=new ConsultationTEST();
				
				p.modifier(textNumCon.getText(),textID_Patient.getText() ,textFieldOBS.getText(), textFieldMaladie.getText(),textFieldTraitement.getText(),d,k, textFieldMontant.getText(),textFieldCHro.getText(),textFieldDose.getText());
				
//*************************************** motionner tout les patient qui on leur derniere consultation depuis 5ans ou plus grace a la class color 
				color l =new color();
				boolean b =l.checkdate(d) ;
				if (b) {
					
					table.setSelectionForeground(Color.RED);}
	
//*************************************** renitialiser la table aprs les modifications
				
				
				refreshTable();
				JOptionPane.showMessageDialog(null, "modified");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------------------------------ajouter une consultation-------------------------------------------------------------------------------------------

		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("AJOUTER");
		btnNewButton.setBounds(100, 617, 161, 49);
		btnNewButton.setBackground(new Color(153, 153, 204));
		btnNewButton.setIcon(new ImageIcon(Consultation.class.getResource("/Save-icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//****************************** recupere la date dans ecrit dans l'interface et concatiner jour avc mois et annee 

				String d = String.format("%s-%s-%s",comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textFieldAnnee.getText());
				
//********************************meme chose pour l'heure heure H min

				String k  = String.format("%sH%s", comboBoxheure.getSelectedItem().toString(),comboBoxmin.getSelectedItem().toString());
				
				ConsultationTEST p=new ConsultationTEST();
				p.ajouter(textID_Patient.getText() ,textFieldOBS.getText(), textFieldMaladie.getText(),textFieldTraitement.getText(),d,k, textFieldMontant.getText(),textFieldCHro.getText(),textFieldDose.getText());
				color l =new color();
				@SuppressWarnings("static-access")
				boolean b =l.checkdate(d) ;
				if (b) {
					
					table.setSelectionForeground(Color.RED);}
				
			
				
				refreshTable();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(536, 216, 716, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
//*************************c'est pour la selection d'une ligne dans la table afficher
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {

				
				int ligne =table.getSelectedRow();
				String id ,numcons,obs,date,heure,maladie,traitement,montant,chro, dose;
				
				
				numcons=table.getModel().getValueAt(ligne, 0).toString();
				id=table.getModel().getValueAt(ligne, 1).toString();
				obs=table.getModel().getValueAt(ligne, 2).toString();
				maladie=table.getModel().getValueAt(ligne, 3).toString();
				traitement=table.getModel().getValueAt(ligne, 4).toString();
				date=table.getModel().getValueAt(ligne, 5).toString();
				heure=table.getModel().getValueAt(ligne, 6).toString();
				montant=table.getModel().getValueAt(ligne, 7).toString();
				chro=table.getModel().getValueAt(ligne, 8).toString();
				dose=table.getModel().getValueAt(ligne, 9).toString();
				
				textNumCon.setText(numcons);
				textID_Patient.setText(id);
				textFieldOBS.setText(obs);
				textFieldMaladie.setText(maladie);

				textFieldAnnee.setText(Stringtoannee(date));
			
				textFieldMontant.setText(montant);
				textFieldTraitement.setText(traitement);
				textFieldCHro.setText(chro);
				textFieldDose.setText(dose);
				color l =new color();
				
				// Select Extract(year From To_date((SELECT MAX(date_rdv) FROM consultation WHERE id_patient ='00000001'), 'dd/mm/yy')) From Dual;
				
				String da=l.searchMaxDate(id);
				
				boolean b =l.checkdate(da );
				if (b) {
//********************************* montionner tout les patient qui on leur derniere consultation depuis 5ans ou plus grace a la class color 
//*********************************************mettre les textfield de id pateint et annee de rdv et le numero de consultation en rouge 
					table.setSelectionForeground(Color.RED);
					
					textFieldAnnee.setBackground(Color.RED);
					textID_Patient.setBackground(Color.RED);
					textNumCon.setBackground(Color.RED);
					int p=JOptionPane.showConfirmDialog(null, "ça fait 5 ans que le patient avec id "+id+"n'a pas fais une consultation  voulez vous le supprimée definitivement","supprimé",JOptionPane.YES_NO_OPTION);
//**************************************  la supprission est au choix
					
					if (p==0) 
						//si oui pour la suppression on supprime tout les donnees de patient sauf les statistique 
					
					{//	    supprimer dossier medical et tout l'historique
						ClassDossierMed d=new ClassDossierMed();
				    d.supprimerFiche(id);
				    // supprimer toute les analyses
				    d.supprimerana(id);
				    // supprimer touts les ordonnances
				    classOrdonnance ordo=new classOrdonnance ();
					ordo.supprimerOrd(id);	
					// supprimer les consultation
					ConsultationTEST c=new ConsultationTEST();
					c.supprimer(id);
					// supprimer les rdvs 
					ClassRv v = new ClassRv();
					v.supprimer(date,heure);
					// supprimer les inscription d'in patient
					Patient pat=new Patient();
					pat.supprimerPatient(id);	

				 refreshTable();
					
					
				}
					
					
				} 
				else if (!b) {
					
					table.setSelectionForeground(Color.black);
					textFieldAnnee.setBackground(Color.white);
				textID_Patient.setBackground(Color.white);
				textNumCon.setBackground(Color.white);}
				
				
				
				}
			
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("heure");
		lblNewLabel_1.setBounds(6, 177, 121, 23);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		JButton btnconsultation = new JButton("consultation");
		btnconsultation.setBounds(814, 519, 200, 39);
		btnconsultation.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnconsultation.setBackground(new Color(153, 153, 204));
		btnconsultation.setIcon(new ImageIcon(Consultation.class.getResource("/view-icon.png")));
		btnconsultation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			refreshTable();}
		});
		contentPane.add(btnconsultation);
		
		
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		// utilisation des combo pour limiter les valeurs e eviter tout type d'erreur comme la saisie d'un caractere par exemple 
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Jour", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		contentPane.add(comboBox_1);
		
//		JComboBox comboBox = new JComboBox();
		// utilisation des combo pour limiter les valeurs e eviter tout type d'erreur comme la saisie d'un caractere par exemple 

		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mois", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(comboBox);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(137, 216, 362, 44);
		contentPane.add(scrollPane_5);
		
		textFieldCHro = new JTextArea();
		scrollPane_5.setViewportView(textFieldCHro);
		textFieldCHro.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Maladie chronique");
		lblNewLabel_7.setBounds(6, 227, 121, 31);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel_7);
		
		JButton menu = new JButton("retour au menu");
		menu.setBounds(687, 617, 188, 49);
		menu.setBackground(new Color(153, 255, 153));
		menu.setIcon(new ImageIcon(Consultation.class.getResource("/home-next-icon.png")));
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu d= new Menu();
				d.setVisible(true);
				setVisible(false);
			}
		});
		menu.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(menu);
		
		JButton fermer = new JButton("Se Deconnecter");
		fermer.setBounds(935, 617, 179, 49);
		fermer.setBackground(new Color(255, 153, 153));
		fermer.setIcon(new ImageIcon(Consultation.class.getResource("/Apps-Dialog-Shutdown-icon.png")));
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();}
			}
		});
		fermer.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(fermer);
		
		JLabel lblNewLabel_5 = new JLabel("Doses");
		lblNewLabel_5.setBounds(10, 443, 117, 29);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(137, 420, 362, 60);
		contentPane.add(scrollPane_1);
		
		textFieldDose = new JTextArea();
		scrollPane_1.setViewportView(textFieldDose);
		textFieldDose.setColumns(10);
		

		
		txtLaDonneeRechercher = new JTextField();
		txtLaDonneeRechercher.setBounds(890, 144, 208, 44);
		txtLaDonneeRechercher.addKeyListener(new KeyAdapter() {
//************************************ effectuer une recherche avec tout type de disponable dans la table
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String selection = (String)comboBox_2.getSelectedItem();
				String sql="select *from consultation vous where "+selection+"=? ";
				
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, txtLaDonneeRechercher.getText());
                ResultSet rs= pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                

			} catch (Exception e3) {
				e3.printStackTrace();
			}
				
			
			}
		});
		txtLaDonneeRechercher.setHorizontalAlignment(SwingConstants.CENTER);
		txtLaDonneeRechercher.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtLaDonneeRechercher.setText("la donnee rechercher");
		contentPane.add(txtLaDonneeRechercher);
		txtLaDonneeRechercher.setColumns(10);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(659, 144, 221, 49);
		comboBox_2.setBackground(new Color(255, 255, 153));
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		// utilisation des combo pour limiter les valeurs e eviter tout type d'erreur comme la saisie d'un type qui n'existe pas 

		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"le type de recherche ", "numcons", "id_patient", "obs", "maladie", "traitmntpr ", "date_rdv", "heure", "montantp", "maladie_chronique", "dose"}));
		contentPane.add(comboBox_2);
		

		
		 comboBoxheure = new JComboBox();
		 comboBoxheure.setBounds(169, 174, 66, 31);
			// utilisation des combo pour limiter les valeurs e eviter tout type d'erreur comme la saisie d'un caractere par exemple 

		comboBoxheure.setModel(new DefaultComboBoxModel(new String[] {"H", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
		comboBoxheure.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(comboBoxheure);
		
		JLabel lblNewLabel_8 = new JLabel("H");
		lblNewLabel_8.setBounds(245, 174, 23, 31);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel_8);
		
		 comboBoxmin = new JComboBox();
		 comboBoxmin.setBounds(278, 174, 72, 31);
		comboBoxmin.setFont(new Font("Tahoma", Font.BOLD, 13));		// utilisation des combo pour limiter les valeurs e eviter tout type d'erreur comme la saisie d'un caractere par exemple 

		comboBoxmin.setModel(new DefaultComboBoxModel(new String[] {"Min", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "00"}));
		contentPane.add(comboBoxmin);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(544, 132, 78, 61);
		// ajouter une photo
		lblNewLabel_9.setIcon(new ImageIcon(Consultation.class.getResource("/Male-user-search-icon.png")));
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Date");
		lblNewLabel_10.setBounds(6, 119, 121, 39);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel_10);
		
		c = new JLabel("clock");
		c.setForeground(Color.BLUE);
		c.setFont(new Font("Tahoma", Font.BOLD, 13));
		c.setBounds(6, 11, 229, 49);
		contentPane.add(c);
		clock();
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(-358, 0, 1776, 946);
		contentPane.add(scrollPane_6);
		JLabel lblNewLabel_12 = new JLabel("");
		scrollPane_6.setViewportView(lblNewLabel_12);
		// ajouter un backgroud pour le jfram
		lblNewLabel_12.setIcon(new ImageIcon(Consultation.class.getResource("/749861.jpg")));
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 94, 46, 14);
		contentPane.add(label);
		
	   
		refreshTable();
	}
}