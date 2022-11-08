package gl;

/**
 * 
 * dans cette fenetre on s'intersse a enregister

 * 
 *  l'ordonnance d'un patient d'une consultation
 *   
 *   donnee ou bien la modifier et bien sur 
 *   
 *   le choix de l'imprimé si elle sera existante 
 * 
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

@SuppressWarnings({ "unused", "serial" })
public class Ordonance extends JFrame {

	private JPanel contentPane;
	private JTextArea textFieldord;
	private JTable table;
	private JTextField recherche;
	Connection connection=null;
	PreparedStatement prp = null;
	private JTextField txtIdPatient;
	private JTextField txtNumeroDeConsultation;
	@SuppressWarnings("rawtypes")
	JComboBox comboBoxrecherche ;

	/**
	 * Launch the application.
	 */
	//*************************************** afficher la liste des ordonnance deja prescris 
	public void  refreshTable(){
		 try {    connection=sqlConnection.bdConnector();
				String sql="select * from ORDONNANCE";
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
 }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ordonance frame = new Ordonance();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Ordonance() {
		setTitle("Odonnance");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		// le boutton qui permis l'impression de l'ordonnance prescrite 
		
		JButton btnNewButton = new JButton("Imprimer\r\n");
		btnNewButton.setBackground(new Color(204, 153, 255));
		btnNewButton .setIcon(new ImageIcon(Ordonance.class.getResource("/print-icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//boolean complete = txtpnOrdonance.print();
					boolean complete2=textFieldord.print();
					if(complete2) JOptionPane.showMessageDialog(null, "l'impression en cours", "Imprimer", JOptionPane.INFORMATION_MESSAGE);

					else 	JOptionPane.showMessageDialog(null, "imprimer !!!! ", "Imprimer", JOptionPane.ERROR_MESSAGE);
					
					
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			  
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(254, 569, 174, 39);
		contentPane.add(btnNewButton);
		
		// fermeture de la fenetre 
		
		JButton fermer = new JButton("Se Deconnecter");
		fermer.setBackground(new Color(255, 102, 102));
		fermer.setIcon(new ImageIcon(Ordonance.class.getResource("/Apps-Dialog-Shutdown-icon.png")));
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();}
				
			}
		});
		fermer.setFont(new Font("Tahoma", Font.BOLD, 13));
		fermer.setBounds(864, 595, 178, 38);
		contentPane.add(fermer);
		
		// fermeture de la fenetre et l'ouverture celle de menu 
		JButton menu = new JButton("retour au menu");
		menu.setBackground(new Color(153, 255, 102));
		menu.setIcon(new ImageIcon(Ordonance.class.getResource("/home-next-icon.png")));
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu d= new Menu();
				d.setVisible(true);
				setVisible(false);
			}
		});
		menu.setFont(new Font("Tahoma", Font.BOLD, 13));
		menu.setBounds(667, 595, 173, 39);
		contentPane.add(menu);
		
		// enregister l'ordonnanace prescrit 
		
		JButton btnNewButton_1 = new JButton("Enregistrer");
		btnNewButton_1.setBackground(new Color(204, 153, 255));
		btnNewButton_1.setIcon(new ImageIcon(Ordonance.class.getResource("/Save-icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection=sqlConnection.bdConnector();
		  
		    	
		        
		    	
		        
		        String Id=txtIdPatient.getText()  ;
		       
		        String consu =txtNumeroDeConsultation.getText() ;
		       
		        String ord =textFieldord.getText() ; 
		        classOrdonnance o=new classOrdonnance();
		        o.ajouterOrd(Id, consu, ord);
		        refreshTable();
		   
		      
		        
			    
		     
		    
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(61, 39, 156, 39);
		contentPane.add(btnNewButton_1);
		
		textFieldord = new JTextArea();
		textFieldord.setBounds(22, 185, 596, 373);
		contentPane.add(textFieldord);
		textFieldord.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldord.setText("                                                                              Ordonance\r\n   Cabinet medical :\r\n   Nom patient :\r\n   Prenom  patient :\r\n \r\nVotre Traitement :");
		textFieldord.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(648, 182, 394, 341);
		contentPane.add(scrollPane);
		
		
		// slectionner une ligne dan sla table est envoyer ses donne
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne =table.getSelectedRow();
				String id ,numcons,ord;
				
				
				numcons=table.getModel().getValueAt(ligne, 0).toString();
				id=table.getModel().getValueAt(ligne, 1).toString();
				ord=table.getModel().getValueAt(ligne, 2).toString();
			
				
				txtNumeroDeConsultation.setText(numcons);
				txtIdPatient.setText(id);
				textFieldord.setText(ord);

			}
		});
		scrollPane.setViewportView(table);
		// consulter la table des ordonances
		
		JButton Consulter = new JButton("consulter");
		Consulter.setBackground(new Color(204, 153, 255));
		Consulter  .setIcon(new ImageIcon(Ordonance.class.getResource("/view-icon.png")));
		Consulter.setFont(new Font("Tahoma", Font.BOLD, 12));
		Consulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();}
		});
		Consulter.setBounds(810, 528, 144, 39);
		contentPane.add(Consulter);
		
		 comboBoxrecherche = new JComboBox();
		comboBoxrecherche.setForeground(Color.GRAY);
		comboBoxrecherche.setFont(new Font("Tahoma", Font.BOLD, 12));
		// les types de recherches qu'on pourra effectuer 
		comboBoxrecherche.setModel(new DefaultComboBoxModel(new String[] {"Recherche", "ID_PATIENT", "NUMCONS", "ODR"}));
		comboBoxrecherche.setBounds(684, 97, 156, 39);
		contentPane.add(comboBoxrecherche);
		
		
		// electuer une recherche 
		recherche = new JTextField();
		recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String selection = (String)comboBoxrecherche.getSelectedItem();
				String sql="select *from ORDONNANCE vous where "+selection+"=? ";
				
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1, recherche.getText());
                ResultSet rs= pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
                setBackground(Color.blue);
                setForeground(Color.WHITE);
            
                setBackground(table.getBackground());
                setForeground(table.getForeground());
                

				
			} catch (Exception e3) {
				e3.printStackTrace();
			}
				
			}
			
		});
		recherche.setBounds(850, 98, 177, 39);
		contentPane.add(recherche);
		recherche.setColumns(10);
		
		txtIdPatient = new JTextField();
		txtIdPatient.setBackground(Color.YELLOW);
		txtIdPatient.setForeground(Color.GRAY);
		txtIdPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtIdPatient.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdPatient.setText("Id patient \r\n");
		txtIdPatient.setBounds(81, 122, 190, 34);
		contentPane.add(txtIdPatient);
		txtIdPatient.setColumns(10);
		
		txtNumeroDeConsultation = new JTextField();
		txtNumeroDeConsultation.setBackground(Color.YELLOW);
		txtNumeroDeConsultation.setForeground(Color.GRAY);
		txtNumeroDeConsultation.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNumeroDeConsultation.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumeroDeConsultation.setText("numero de consultation");
		txtNumeroDeConsultation.setBounds(335, 123, 190, 31);
		contentPane.add(txtNumeroDeConsultation);
		txtNumeroDeConsultation.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Modifier");
		btnNewButton_2.setBackground(new Color(204, 153, 255));
		btnNewButton_2 .setIcon(new ImageIcon(Ordonance.class.getResource("/edit-validated-icon.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				connection=sqlConnection.bdConnector();
				classOrdonnance o1=new classOrdonnance();
		        
	
				o1.modifierOrd(txtIdPatient.getText(), txtNumeroDeConsultation.getText(), textFieldord.getText() );
				 refreshTable();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setBounds(241, 39, 144, 39);
		contentPane.add(btnNewButton_2);
		                                         
		// la methode supprimer une ordonnance en cas ou le malade ne veut plus utiliser l'ordonnance prescrit  
		JButton btnNewButton_3 = new JButton("Supprimer");
		btnNewButton_3.setBackground(new Color(204, 153, 255));
		btnNewButton_3 .setIcon(new ImageIcon(Ordonance.class.getResource("/Recycle-Bin-Full-icon.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection=sqlConnection.bdConnector();
	           classOrdonnance o2=new classOrdonnance();
	       	    o2.supprimerOrd(txtIdPatient.getText(), txtNumeroDeConsultation.getText());
		        refreshTable();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setBounds(407, 39, 144, 39);
		contentPane.add(btnNewButton_3);
		// ajouter une image pour la recherche une loup
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Ordonance.class.getResource("/Start-Menu-Search-icon (1).png")));
		lblNewLabel.setBounds(634, 88, 57, 48);
		contentPane.add(lblNewLabel);
		// ajouter une image at backgroud de jfram
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Ordonance.class.getResource("/Odronance.png")));
		lblNewLabel_1.setBounds(0, 0, 1051, 650);
		contentPane.add(lblNewLabel_1);
		 refreshTable();
	}
}
