package gl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
@SuppressWarnings({ "unused" })
/************************************************************************************
 * 
 * 
 * 
 * ici  l'utilsateur doir d'abors s'antentifier 
 * pour pourvoir acceder au menu que c soit 
 * celui de medecin ou celui de la secetaire 
 * grace a une table user cree dans la base
 * de  donnee oracle sql 
 * 
 **************************************************************************************
 */
public class autentifier {

	JFrame frmAutentification;
	private JTextField user;
	private JPasswordField passwordField;
	Connection connection = null; 
	PreparedStatement ps = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					autentifier window = new autentifier();
					window.frmAutentification.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public autentifier() {
		initialize();
		connection =sqlConnection.bdConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmAutentification = new JFrame();
		frmAutentification.setTitle("Autentification");
		frmAutentification.setResizable(false);
		frmAutentification.setBounds(100, 100, 938, 619);
 
		frmAutentification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAutentification.getContentPane().setLayout(null);
		frmAutentification.setLocationRelativeTo(null);
		
		user = new JTextField();
		
		
		
		user.setBounds(284, 130, 284, 45);
		frmAutentification.getContentPane().add(user);
		user.setColumns(10);
		
		JComboBox comboBoxsp = new JComboBox();
		comboBoxsp.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBoxsp.setModel(new DefaultComboBoxModel(new String[] {"Medecin", "Secretaire"}));
		comboBoxsp.setBounds(284, 308, 284, 45);
		frmAutentification.getContentPane().add(comboBoxsp);
		
		passwordField = new JPasswordField();
		passwordField.setAlignmentY(Component.TOP_ALIGNMENT);
		passwordField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		passwordField.setBounds(284, 220, 284, 45);
		frmAutentification.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Utilisateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(179, 129, 83, 45);
		frmAutentification.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(179, 224, 103, 34);
		frmAutentification.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Soyez Les Bienvenu");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(188, 28, 502, 45);
		frmAutentification.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.setForeground(new Color(204, 255, 255));
		btnNewButton.setBackground(new Color(0, 255, 102));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings({ "deprecation" })
			public void actionPerformed(ActionEvent e) {
				
					try {
						// recuperer la specialité 
						String choix= comboBoxsp.getSelectedItem().toString();
						
						// utiliser la user pour selectioner l'utilisateur et son mot de passe ainsi que se specialité
						
						String sql="SELECT * FROM utilisateur where username=? and passwords=? and spe=?";
						
						ps = connection.prepareStatement(sql);
						
						ResultSet rs = null;

						String M="Medecin";
						
						String secr= "Secretaire";
						ps.setString(1, user.getText());
						ps.setString(2, passwordField.getText());
						ps.setString(3, choix);
						
						rs=ps.executeQuery();
						int count =0;
						
					// verifier si c'est une secretaire ou un medecin 	
						while(rs.next()) {
							count ++;
									
								}
							if (count==1) 
							{	
								if(M.equals(choix)) 
								 { //si c'est medecin on va ouvrir le menu propre au medecin sinon on va ouvrir celui de secretaire 
									// ça va dependre de specialité de user 
								JOptionPane.showMessageDialog(null, "vous etes connectés");
								
									Menu window = new Menu();
									window.setVisible(true);
									frmAutentification.dispose();
									rs.close();
									ps.close();
								}
								else if(secr.equals(choix)) {
									JOptionPane.showMessageDialog(null, "vous etes connectés");
									rs=ps.executeQuery(sql);
									Menu2 window = new Menu2();
									window.setVisible(true);
									frmAutentification.dispose();
									rs.close();
									ps.close();
								}
							}
							else {JOptionPane.showMessageDialog(null, "reesayer");}
						 
						rs.close();
						ps.close();} 
					catch(Exception ee) {
							ee.printStackTrace();
						}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(263, 402, 207, 34);
		frmAutentification.getContentPane().add(btnNewButton);
	// afficher un message de sortir soit avec oui ou avec non 
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					frmAutentification.dispose();}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(783, 497, 103, 34);
		frmAutentification.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Sp\u00E9cialit\u00E9");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(179, 316, 103, 29);
		frmAutentification.getContentPane().add(lblNewLabel_3);
	
		// ajouter une photo pour medecin  le jFRam
		JLabel lblNewLabel_5 = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("/People-Doctor-Male-icon.png")).getImage();
		lblNewLabel_5.setIcon(new ImageIcon(img));
		lblNewLabel_5.setBounds(27, 130, 115, 151);
		frmAutentification.getContentPane().add(lblNewLabel_5);
		// ajouter une photo pour le jFRam
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		Image img1= new ImageIcon(this.getClass().getResource("/login_on.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(img1));
		lblNewLabel_4.setBounds(-305, 0, 1227, 580);
		frmAutentification.getContentPane().add(lblNewLabel_4);
	}
		
}
