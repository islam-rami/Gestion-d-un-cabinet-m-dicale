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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*************************************************************************************************************
 * 
 * @author INES with HADJER
 *  dans cet JFram on va  creer le dossier medicale de chaque patient 
 *  
 *  y compris ttout les methodes : ajouter supprission , modification , la consultation et  recherche  
 *  
 *  en resspectant tout les contraites des cles primeres et des cles 
 *  
 *  etrangere generer dans le SGBD et sité dans le rapports 
 *  
 *   ainsi que les analyses de chaque patient on permettra 
 *   
 *   l'ajourt , la modification , la consultation et la recherche 
 *  
 *
 **************************************************************************************************************/
@SuppressWarnings({ "unused", "serial" })
public class DossierMedicale extends JFrame {

	
	private JPanel contentPane;
	private JTextField textFieldID;
	private JLabel lblSexe;
	private JTextField textFieldPoids;
	private JTextField textFieldTaille;
	private JTextArea textFieldRMQ;
	private JLabel c ;
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
	@SuppressWarnings("rawtypes")
	JComboBox comboBoxrecherche;
/**
 * Launch the application.
 */
	// come tout les Jfram deja fais il faut mettre l'heure avec la date de jour de dans 
	// donc on genere une methode qui affiche l'heure et la date en cours 
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
/*****************************************************************************************************************	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DossierMedicale frame = new DossierMedicale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection connection = null; 
private JTextField textFieldGS;
private JTable table;
private JTextField textFieldINumCons;
private JTextArea textFieldanalyse;
@SuppressWarnings("rawtypes")
JComboBox comboBoxrs;
private JTable table_2;
private JTextArea commentaire;
private JTextField recherche;

	/**
	 * Create the frame.
	 */
// methode pour afficher la table de dossier medicale
	 public void  refreshTable(){
		 try {
				String sql="select * from dossier_medicale";
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
   }
	// methode pour afficher la table de analyse 
	 
	 public void  refreshTable2(){
		 try {
				String sql="select * from analyse";
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				table_2.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
   }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DossierMedicale() {
		
		setTitle("Dossier m\u00E9dicale ");
		connection =sqlConnection.bdConnector();
		   
			  
		    
	//************************************************************************************************************	
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1347, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("Num\u00E9ro Consultation");
		lblNewLabel.setBounds(474, 67, 148, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		textFieldID = new JTextField();
		textFieldID.setBackground(Color.YELLOW);
		textFieldID.setBounds(657, 123, 193, 37);
		textFieldID.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		lblSexe = new JLabel("Groupe Sanguin");
		lblSexe.setBounds(44, 67, 132, 17);
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblSexe);
		
		JLabel lblNewLabel_1 = new JLabel("Poids");
		lblNewLabel_1.setBounds(43, 104, 116, 21);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		textFieldPoids = new JTextField();
		textFieldPoids.setBounds(175, 99, 166, 31);
		textFieldPoids.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(textFieldPoids);
		textFieldPoids.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Quelques remarques ");
		lblNewLabel_2.setBounds(10, 219, 166, 38);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		
		textFieldTaille = new JTextField();
		textFieldTaille.setBounds(175, 141, 166, 37);
		textFieldTaille.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(textFieldTaille);
		textFieldTaille.setColumns(10);
		
		JLabel lblNTelephone = new JLabel("Taille");
		lblNTelephone.setBounds(44, 146, 105, 26);
		lblNTelephone.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNTelephone);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(175, 189, 228, 116);
		contentPane.add(scrollPane_3);
		
		textFieldRMQ = new JTextArea();
		scrollPane_3.setViewportView(textFieldRMQ);
		textFieldRMQ.setColumns(10);
		
		JLabel lblAjouterUnNouveau = new JLabel("Dossier m\u00E9dicale");
		lblAjouterUnNouveau.setBounds(370, 11, 166, 38);
		lblAjouterUnNouveau.setForeground(new Color(51, 0, 153));
		lblAjouterUnNouveau.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblAjouterUnNouveau);
	// ici on va faire enregister uèn nouveau dossier medicale d'un nouveau patient 	on respectant bien sur tout les contraines de la classe dossier medicale 
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBackground(new Color(153, 153, 204));
		btnEnregistrer.setBounds(21, 324, 141, 48);
		btnEnregistrer.setIcon(new ImageIcon(DossierMedicale.class.getResource("/Save-icon.png")));
		btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
//				instansier un objet de la classe dossier medicale pour pouvoir manipuler la methode ajouter (enregistrer )
				ClassDossierMed p=new ClassDossierMed();
				
				p.ajouterFiche( textFieldINumCons.getText() , textFieldID.getText(),textFieldGS.getText(),textFieldPoids.getText(),textFieldTaille.getText(), textFieldRMQ.getText());
				refreshTable();
}
		});
		contentPane.add(btnEnregistrer);
		
		 c = new JLabel("Time");
		 c.setForeground(Color.BLUE);
		 c.setFont(new Font("Tahoma", Font.BOLD, 13));
		c.setBounds(10, 11, 274, 37);
		contentPane.add(c);
		clock();
		// en cas ou on veut faire desmodification sur le dossier medicale la methode modifer nous permier de realiser ça
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setIcon(new ImageIcon(DossierMedicale.class.getResource("/edit-validated-icon.png")));
		btnNewButton.setBackground(new Color(153, 153, 204));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				instansier un objet de la classe dossier medicale pour pouvoir manipuler la methode modifier

				
				ClassDossierMed p=new ClassDossierMed();
				p.modifierFiche(textFieldINumCons.getText() , textFieldID.getText(),textFieldGS.getText(),textFieldPoids.getText(),textFieldTaille.getText(), textFieldRMQ.getText());

				refreshTable();
			}
		});
		btnNewButton.setBounds(154, 324, 141, 48);
		contentPane.add(btnNewButton);
//****************************** afin de supprimer un dossier un patient la methode de la classe dossier medicale nous permis de faire c 
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer .setIcon(new ImageIcon(DossierMedicale.class.getResource("/Recycle-Bin-Full-icon.png")));
		btnSupprimer.setBackground(new Color(153, 153, 204));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
// ***********instansier un objet de la classe dossier medicale pour pouvoir manipuler la methode supprimer  				
				
				ClassDossierMed p=new ClassDossierMed();
				p.supprimerFiche(textFieldINumCons.getText(),textFieldID.getText());
				
// ***************************************actualiser la table apres l'affectation des modification 
				refreshTable();
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSupprimer.setBounds(292, 324, 166, 48);
		contentPane.add(btnSupprimer);
		
		textFieldGS = new JTextField();
		textFieldGS.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldGS.setColumns(10);
		textFieldGS.setBounds(175, 64, 166, 31);
		contentPane.add(textFieldGS);
		
		JButton btnConsulter = new JButton("consulter");
		btnConsulter .setIcon(new ImageIcon(DossierMedicale.class.getResource("/view-icon.png")));
		btnConsulter.setBackground(new Color(153, 153, 204));
		btnConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//*********************************************** actualiser la table ou l'afficher
				refreshTable();
			}
		});
		btnConsulter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConsulter.setBounds(455, 324, 148, 48);
		contentPane.add(btnConsulter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 383, 634, 229);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne =table.getSelectedRow();
				String id ,numcons,groupe,poid,taille,rmq,rs;
// *******************************afficher la ligne selectionhner dans et efectuer ses donnees vers les textfeild exixtant 
				
				numcons=table.getModel().getValueAt(ligne, 0).toString();
				id=table.getModel().getValueAt(ligne, 1).toString();
				groupe=table.getModel().getValueAt(ligne, 2).toString();
				poid=table.getModel().getValueAt(ligne, 3).toString();
				taille=table.getModel().getValueAt(ligne, 4).toString();
				rmq=table.getModel().getValueAt(ligne, 5).toString();

				
				
			
				textFieldINumCons.setText(numcons);
				textFieldID.setText(id);
				textFieldGS.setText(groupe);
				textFieldPoids.setText(poid);
                textFieldTaille.setText(taille);
                textFieldRMQ.setText(rmq);


			}
		});
		scrollPane.setViewportView(table);
		
		textFieldINumCons = new JTextField();
		textFieldINumCons.setBackground(Color.YELLOW);
		textFieldINumCons.setBounds(657, 66, 193, 38);
		contentPane.add(textFieldINumCons);
		textFieldINumCons.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Id Patient");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(474, 126, 142, 34);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(893, 63, 200, 62);
		contentPane.add(scrollPane_4);
		
		textFieldanalyse = new JTextArea();
		scrollPane_4.setViewportView(textFieldanalyse);
		textFieldanalyse.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("les analyses demand\u00E9es");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(920, 42, 173, 21);
		contentPane.add(lblNewLabel_4);
		
		comboBoxrs = new JComboBox();
		comboBoxrs.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBoxrs.setModel(new DefaultComboBoxModel(new String[] {"positifs ", "n\u00E9gatifs"}));
		comboBoxrs.setForeground(Color.RED);
		comboBoxrs.setBounds(1114, 67, 200, 58);
		contentPane.add(comboBoxrs);
		
		JLabel lblNewLabel_6 = new JLabel("Les resultats des analyses ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(1104, 39, 199, 26);
		contentPane.add(lblNewLabel_6);
		// fermer le jfram dossier medicale :
		JButton fermer = new JButton("Se Deconnecter");
		fermer.setBackground(new Color(255, 102, 102));
		fermer.setIcon(new ImageIcon(DossierMedicale.class.getResource("/Apps-Dialog-Shutdown-icon.png")));
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();}
			}
		});
		fermer.setFont(new Font("Tahoma", Font.BOLD, 13));
		fermer.setBounds(1141, 624, 173, 37);
		contentPane.add(fermer);

		
// un boutton qui permis de retoutner vers le menu une fois cliqué sur lui
		
		JButton menu = new JButton("retour au menu");
		menu.setBackground(new Color(102, 255, 102));
		menu.setIcon(new ImageIcon(DossierMedicale.class.getResource("/home-next-icon.png")));

		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu d= new Menu();
				
				// permettre la visuabilité de Jfram menu
	
				d.setVisible(true);
				setVisible(false);
			}
		});
		menu.setFont(new Font("Tahoma", Font.BOLD, 13));
		menu.setBounds(946, 623, 182, 38);
		contentPane.add(menu);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {

		});
		scrollPane_1.setBounds(709, 383, 625, 229);
		contentPane.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne =table_2.getSelectedRow();
				String id ,numcons,anal,rs,com;
				
				// *******************************afficher la ligne selectionhner dans et efectuer ses donnees vers les textfeild exixtant 
			
				numcons=table.getModel().getValueAt(ligne, 0).toString();
				id=table.getModel().getValueAt(ligne, 1).toString();

				anal=table.getModel().getValueAt(ligne, 2).toString();
			
				com=table.getModel().getValueAt(ligne, 3).toString();
			
				textFieldINumCons.setText(numcons);
				textFieldID.setText(id);
                textFieldanalyse.setText(anal);
                commentaire.setText(com);
			}
		});
		scrollPane_1.setViewportView(table_2);
		
		JButton btnConsulter_1 = new JButton("consulter");
		btnConsulter_1 .setIcon(new ImageIcon(DossierMedicale.class.getResource("/view-icon.png")));
		btnConsulter_1.setBackground(new Color(153, 153, 204));
		btnConsulter_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// actualiser la table
				refreshTable2();}
		});
		btnConsulter_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConsulter_1.setBounds(1003, 324, 148, 48);
		contentPane.add(btnConsulter_1);
// creer un boutton avec le quel on va appeler la methode enregister de type analyse 		
		JButton btnEnregistrer_1 = new JButton("Enregistrer");
		btnEnregistrer_1.setBackground(new Color(153, 153, 204));
		btnEnregistrer_1.setIcon(new ImageIcon(DossierMedicale.class.getResource("/Save-icon.png")));
		btnEnregistrer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	ClassDossierMed p=new ClassDossierMed();
				
				p.ajouterana( textFieldINumCons.getText() , textFieldID.getText(),textFieldanalyse.getText(),comboBoxrs.getSelectedItem().toString(),commentaire.getText());
				
				
			// actualiser la table analyse 	
				refreshTable2();}
		});
		btnEnregistrer_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEnregistrer_1.setBounds(865, 324, 141, 48);
		contentPane.add(btnEnregistrer_1);
		// creer un boutton avec le quel on va appeler la methode modifier de type analyse 				
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.setIcon(new ImageIcon(DossierMedicale.class.getResource("/edit-validated-icon.png")));
		btnNewButton_1.setBackground(new Color(153, 153, 204));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	ClassDossierMed p=new ClassDossierMed();
				
				p.modifierana( textFieldINumCons.getText() , textFieldID.getText(),textFieldanalyse.getText(),comboBoxrs.getSelectedItem().toString(),commentaire.getText());
				refreshTable2();
			}
		});
		//*********************************remarque ********************************
		// bien sur que la table analyse ne 
		//contient pas une supprission car on voudra jamais 
		//supprimer les analyse d'un patient sauf si on veut
		//le supprimer caremment de la base de donnes
		//*********************************************************
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(1150, 324, 141, 48);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(1129, 169, 200, 121);
		contentPane.add(scrollPane_2);
		
		commentaire = new JTextArea();
		scrollPane_2.setViewportView(commentaire);
		
		JLabel lblNewLabel_5 = new JLabel("Quelque remarques et commentaires");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(835, 171, 284, 37);
		contentPane.add(lblNewLabel_5);
		
		recherche = new JTextField();
		
		recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try { 
				//effectuer une recherche a l'aide d'une recherche dans la base de donnee sql dansla table dossier medical

					String selection = (String)comboBoxrecherche.getSelectedItem();
				String sql="select *from dossier_medicale where "+selection+"=? ";
				
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, recherche.getText());
                ResultSet rs= pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
                setBackground(Color.blue);
                setForeground(Color.WHITE);
            
                setBackground(table.getBackground());
                setForeground(table.getForeground());
                
                
//                while (rs.next()) {
//
//					
//				}
				
			} catch (Exception e3) {
				e3.printStackTrace();
			}
				//effectuer une recherche a l'aide d'une recherche dans la base de donnee sql dansla table analyse 
				try {
					String selection = (String)comboBoxrecherche.getSelectedItem();
				String sql="select *from analyse where "+selection+"=? ";
				
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, recherche.getText());
                ResultSet rs= pst.executeQuery();
                table_2.setModel(DbUtils.resultSetToTableModel(rs));
                
                setBackground(Color.blue);
           
            
                setBackground(table_2.getBackground());
                setForeground(table_2.getForeground());
                
                
//                while (rs.next()) {
//
//					
//				}
				
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			}
		});
		recherche.setBounds(763, 250, 148, 40);
		contentPane.add(recherche);
		recherche.setColumns(10);
		
		 comboBoxrecherche = new JComboBox();
		comboBoxrecherche.setModel(new DefaultComboBoxModel(new String[] {"recherche", "numcons", "id_patient"}));
		comboBoxrecherche.setBounds(605, 250, 148, 40);
		contentPane.add(comboBoxrecherche);
		
		// ajouter une photo
		JLabel pic = new JLabel("");
		pic.setIcon(new ImageIcon(DossierMedicale.class.getResource("/Start-Menu-Search-icon (1).png")));
		pic.setBounds(555, 250, 48, 48);
		contentPane.add(pic);
		
		// ajpuetr un backgroud pour le jfram
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Patient_.class.getResource("/Annotation 2020-06-30 005940.png")));
		lblNewLabel_7.setBounds(0, -2, 1364, 711);
		contentPane.add(lblNewLabel_7);
		refreshTable(); 
		refreshTable2();
	}


}
