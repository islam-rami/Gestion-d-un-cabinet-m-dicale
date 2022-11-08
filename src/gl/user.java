package gl;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class user extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField user;
	private JPasswordField passwordField;
	@SuppressWarnings("rawtypes")
	JComboBox comboBoxsp;
	Connection connection = null; 
	PreparedStatement ps = null;
	/**
	 * Launch the application.
	 */
	
	@SuppressWarnings("deprecation")
	void afficherLeCompte() 
	{  // affichage de la table de user concerner 
		connection =sqlConnection.bdConnector();
		try {
			String selection = (String) comboBoxsp.getSelectedItem();
		String sql="select *from UTILISATEUR where USERNAME = ? and PASSWORDS = ? and SPE = ? ";
		
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setString(1, user.getText());
		pst.setString(2,passwordField.getText());
		pst.setString(3, selection);
		
        ResultSet rs= pst.executeQuery();
        table.setModel(DbUtils.resultSetToTableModel(rs));
        
//        while (rs.next()) {
//
//			
//		}
		
	} catch (Exception e4) {
		e4.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user frame = new user();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public user() {
		setTitle("USER");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 216, 297, 128);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		user = new JTextField();
		user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				afficherLeCompte() ;
			}
		});
		user.setBounds(224, 18, 194, 32);
		contentPane.add(user);
		user.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				afficherLeCompte() ;}
		});
		passwordField.setBounds(224, 61, 194, 32);
		contentPane.add(passwordField);
		
		JButton Ajouter = new JButton("Ajouter un compte");
		Ajouter.setFont(new Font("Tahoma", Font.BOLD, 12));
		Ajouter.setHorizontalAlignment(SwingConstants.LEFT);
		Ajouter.setIcon(new ImageIcon(user.class.getResource("/Actions-user-group-new-icon.png")));

		Ajouter.setBackground(new Color(204, 153, 255));
		Ajouter.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unused", "deprecation" })
			public void actionPerformed(ActionEvent e) {
				try {
					// ajouter un compte d'un user par un medecin 
					connection=sqlConnection.bdConnector();
							
						Statement stt =connection.createStatement();
						

				
					String sql="INSERT INTO UTILISATEUR VALUES(?,?,?)";
					PreparedStatement	rv = connection.prepareStatement(sql);
					
			//******************************* affecter le resultat des colones vers les variables de parametres ********************************************
					String selection = (String) comboBoxsp.getSelectedItem();

					rv.setString(1, user.getText());
					rv.setString(2, passwordField.getText());
					rv.setString(3,selection);
					
					ResultSet rss= rv.executeQuery();
					afficherLeCompte() ;
					JOptionPane.showMessageDialog(null, "le compte de "+user.getText()+"");
					}
					catch (Exception e1) {
						
						JOptionPane.showMessageDialog(null, "faite attention il faut tout remplir ");
						
						
					}
					
				
			}
		});

		Ajouter.setBounds(9, 214, 297, 43);
		contentPane.add(Ajouter);
		
		JButton btnNewButton_1 = new JButton("changer le mot de passe  un compte");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setBackground(new Color(204, 153, 255));
		btnNewButton_1.setIcon(new ImageIcon(user.class.getResource("/modifier.png")));

		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				connection=sqlConnection.bdConnector();
				// changer le mot de pas d'un compte  comme suit 
				String selection = (String) comboBoxsp.getSelectedItem();

				String sql = "UPDATE UTILISATEUR SET  PASSWORDS='"+passwordField.getText()+ "' where USERNAME='"+user.getText()+"' and SPE='"+selection+"' ";

			try {
			   ps=connection.prepareStatement(sql);
			    ps.execute();
			    afficherLeCompte() ;
			   
			     
		        JOptionPane.showMessageDialog(null, "le compte a ete modifier Updated");
		    } catch (SQLException e2) {
		    	JOptionPane.showMessageDialog(null, "non modifier faites atentions au containtes");
		        JOptionPane.showMessageDialog(null, e2);
		    }
		
			}
		});
		btnNewButton_1.setBounds(9, 255, 297, 45);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("supprimer un compte");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setBackground(new Color(204, 153, 255));
		btnNewButton_2.setIcon(new ImageIcon(user.class.getResource("/Button-Remove-icon.png")));

		btnNewButton_2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// supprimer d'un compte qui existe deja
				connection=sqlConnection.bdConnector();
				String selection = (String) comboBoxsp.getSelectedItem();

				String sql = "DELETE FROM UTILISATEUR WHERE USERNAME='"+user.getText()+ "' and PASSWORDS='"+passwordField.getText()+ "' and SPE='"+selection+ "'";
				try {
					ps = connection.prepareStatement(sql);
					ps.execute();
			        JOptionPane.showMessageDialog(null, "compte du user  : "+user.getText()+ " a ete supprimé ");
			        
			        connection.close();
			        afficherLeCompte() ;
				} catch (SQLException e3) {
					JOptionPane.showMessageDialog(null, "non supprimé faites atentions au containtes");
					e3.printStackTrace();
					
				}
			}
		});
		btnNewButton_2.setBounds(9, 299, 297, 43);
		contentPane.add(btnNewButton_2);
		
		comboBoxsp = new JComboBox();
		comboBoxsp.setModel(new DefaultComboBoxModel(new String[] {"Medecin", "Secretaire"}));
		comboBoxsp.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBoxsp.setBounds(224, 104, 194, 32);
		contentPane.add(comboBoxsp);
		
		JLabel lblNewLabel = new JLabel("Utilisateur");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(94, 11, 83, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(94, 59, 103, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Sp\u00E9cialit\u00E9");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(94, 106, 103, 29);
		contentPane.add(lblNewLabel_3);
		 // quitter 
		JButton fermer = new JButton("Se deconnecter");
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();}
				
			}
		});
		fermer.setBackground(new Color(255, 102, 102));
		fermer.setHorizontalAlignment(SwingConstants.LEFT);
		
		fermer.setFont(new Font("Tahoma", Font.BOLD, 13));
    	fermer.setIcon(new ImageIcon(user.class.getResource("/Apps-Dialog-Shutdown-icon.png")));

		fermer.setBounds(437, 405, 170, 40);
		contentPane.add(fermer);
		//  reenir au menu medecin 
		JButton menu = new JButton("Menu medecin");
		menu.setBackground(new Color(153, 255, 153));
		menu.setIcon(new ImageIcon(user.class.getResource("/home-next-icon.png")));
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu d= new Menu();
				d.setVisible(true);
				setVisible(false);
			}
		});
		// acces au menu secrétaire (le medeci herite de secretaire)
		menu.setHorizontalAlignment(SwingConstants.LEFT);
		menu.setFont(new Font("Tahoma", Font.BOLD, 13));
		menu.setIcon(new ImageIcon(user.class.getResource("/home-next-icon.png")));

		menu.setBounds(224, 405, 194, 40);
		contentPane.add(menu);
		
		JButton btnNewButton = new JButton("Menu secretaire");
		btnNewButton.setBackground(new Color(255, 51, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu2 d= new Menu2();
				d.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(user.class.getResource("/home-next-icon.png")));
		btnNewButton.setBounds(9, 405, 188, 40);
		contentPane.add(btnNewButton);
		

		// ajouter un backgroud de jfram
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(user.class.getResource("/compte.png")));

		lblNewLabel_2.setBounds(0, 0, 625, 456);
		contentPane.add(lblNewLabel_2);
	}
}
