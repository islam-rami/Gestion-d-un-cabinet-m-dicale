package gl;

/*
 * dans cet Jframe on va s'intersser au statistique de nombre de patient 
 * 
 * avec peroide 
 * 
 * avec peroide et maladie diagnostique
 * 
 * avec peroide et maladie chronique
 * 
 * en fesant une instantiation de la class statistique 
 * 
 */


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

@SuppressWarnings("serial")
public class Statistique extends JFrame {
  
	Connection connection = null; 
	PreparedStatement ps = null;
	private JPanel contentPane;
	private JTextField debut;
	private JTextField fin;
	private JTextField nbTemps;
	private JTextField maladie;
	private JTextField nbMaladie;
	private JTable table;
	private JTextField chro;
	private JTextField resultat_chro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistique frame = new Statistique();
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
	public Statistique() {
		setResizable(false);
		connection =sqlConnection.bdConnector();
		setTitle("statistique \r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 947, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Choisissez un intervalle de temps ");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(48, 34, 296, 50);
		contentPane.add(lblNewLabel);
		
		debut = new JTextField();
		debut.setText("Annee\r\n");
		debut.setBounds(270, 95, 59, 28);
		contentPane.add(debut);
		debut.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("La date de");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 95, 84, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Au");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 157, 84, 28);
		contentPane.add(lblNewLabel_2);
		
		fin = new JTextField();
		fin.setText("Annee\r\n");
		fin.setBounds(270, 157, 59, 28);
		contentPane.add(fin);
		fin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Le nombre de malade pour cette periode est ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 219, 296, 28);
		contentPane.add(lblNewLabel_3);
		
		nbTemps = new JTextField();
		nbTemps.setBounds(328, 216, 115, 36);
		contentPane.add(nbTemps);
		nbTemps.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Choisissez la maladie ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(89, 283, 240, 31);
		contentPane.add(lblNewLabel_4);
		
		maladie = new JTextField();
		maladie.setBounds(143, 338, 140, 31);
		contentPane.add(maladie);
		maladie.setColumns(10);
		
		nbMaladie = new JTextField();
		nbMaladie.setBounds(328, 400, 115, 36);
		contentPane.add(nbMaladie);
		nbMaladie.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Le nombre de patient affecter par cette maladie");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 405, 308, 25);
		contentPane.add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(481, 69, 446, 413);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		JComboBox comboBoxj2 = new JComboBox();
		JComboBox comboBoxj1 = new JComboBox();
		JComboBox comboBoxm1 = new JComboBox();
		JComboBox comboBoxm2 = new JComboBox();
		JButton Calcule1 = new JButton("Calculer");
		Calcule1.setFont(new Font("Tahoma", Font.BOLD, 11));
		Calcule1.setIcon(new ImageIcon(Statistique.class.getResource("/Apps-libreoffice-calc-icon.png")));
		Calcule1.setBackground(Color.GREEN);
		Calcule1.addActionListener(new ActionListener() {
			
			// statistique par peroide 
			public void actionPerformed(ActionEvent e) {
				connection =sqlConnection.bdConnector();
				
				try {
					
					// concatination pour les deux  date 	
				
					
					String d1 = String.format("%s-%s-%s",comboBoxj1.getSelectedItem().toString(),comboBoxm1.getSelectedItem().toString(),debut.getText());
				String d2= String.format("%s-%s-%s",comboBoxj2.getSelectedItem().toString(),comboBoxm2.getSelectedItem().toString(),fin.getText());
				String sql="select count(*)from STATISTIQUEPA where DATE_RDV between '"+d1+"' and'"+d2+"'";
				ps=connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(); 
				
				// affichage de la table statistique 
				if(rs.next()) {
					
					String add = rs.getString("count(*)");
					nbTemps.setText(add);}
				
				String sql1="select * from STATISTIQUEPA  where DATE_RDV between '"+d1+"' and'"+d2+"'";
				PreparedStatement pst=connection.prepareStatement(sql1);
				ResultSet rs1=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs1));
				
				
			   // nbTemps.setText(String.valueOf(rs));
			    
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, " statiques :faites attentions il faut remplire les deux dates");
					e1.printStackTrace();
				}
			
	}	});
		Calcule1.setBounds(349, 134, 122, 36);
		contentPane.add(Calcule1);
		
		
		// statistique par peroide et maladie diagnostique 
		JButton calcule2 = new JButton("Calculer");
		calcule2.setFont(new Font("Tahoma", Font.BOLD, 11));
		calcule2.setBackground(new Color(153, 50, 204));
		calcule2.setIcon(new ImageIcon(Statistique.class.getResource("/Apps-libreoffice-calc-icon.png")));
		calcule2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection =sqlConnection.bdConnector();
					try {
					
						// concatination pour les deux  date 	
				String d1 = String.format("%s-%s-%s",comboBoxj1.getSelectedItem().toString(),comboBoxm1.getSelectedItem().toString(),debut.getText());
				String d2= String.format("%s-%s-%s",comboBoxj2.getSelectedItem().toString(),comboBoxm2.getSelectedItem().toString(),fin.getText());
				String mal=maladie.getText(); 
				String sql="select count(*)from STATISTIQUE where MALADIE = '"+mal+"' and DATE_RDV between '"+d1+"'and '"+ d2+"'";
				ps=connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(); 
				// affichage de la table statistique 
				if(rs.next()) {
					String add = rs.getString("count(*)");
					nbMaladie.setText(add);}
				
				String sql1="select * from STATISTIQUE where MALADIE = '"+mal+"' and DATE_RDV between '"+d1+"'and '"+ d2+"'";
				PreparedStatement pst=connection.prepareStatement(sql1);
				ResultSet rs1=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs1));
				
			   
			    
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, " statiques :faites attentions il faut remplire les deux dates et le nom de la maladie dianostique");
					e1.printStackTrace();
				}

			}
		});
		calcule2.setBounds(358, 333, 116, 36);
		contentPane.add(calcule2);
		
		
		comboBoxj1.setModel(new DefaultComboBoxModel(new String[] {"jour ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxj1.setBounds(101, 95, 59, 28);
		contentPane.add(comboBoxj1);
		
		
		comboBoxm1.setModel(new DefaultComboBoxModel(new String[] {"mois", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxm1.setToolTipText("mois\r\n");
		comboBoxm1.setBounds(180, 95, 65, 28);
		contentPane.add(comboBoxm1);

		comboBoxm2.setModel(new DefaultComboBoxModel(new String[] {"mois", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxm2.setBounds(180, 157, 65, 28);
		contentPane.add(comboBoxm2);
		
		
		comboBoxj2.setModel(new DefaultComboBoxModel(new String[] {"jour ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxj2.setBounds(101, 157, 59, 28);
		contentPane.add(comboBoxj2);
		
		JLabel lblNewLabel_7 = new JLabel("Par maladie chronique");
		lblNewLabel_7.setBackground(Color.ORANGE);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(63, 485, 235, 28);
		contentPane.add(lblNewLabel_7);
		
		chro = new JTextField();
		chro.setBounds(158, 534, 140, 36);
		contentPane.add(chro);
		chro.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("La maladie");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(25, 534, 90, 36);
		contentPane.add(lblNewLabel_8);
		
		resultat_chro = new JTextField();
		resultat_chro.setBounds(343, 581, 115, 36);
		contentPane.add(resultat_chro);
		resultat_chro.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Le nombre de patient affecter par cette maladie");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(35, 592, 298, 23);
		contentPane.add(lblNewLabel_9);
		
		
		// statistique par peroide et maladie chronique 
		JButton calcule3 = new JButton("Calculer");
		calcule3.setFont(new Font("Tahoma", Font.BOLD, 11));
		calcule3.setIcon(new ImageIcon(Statistique.class.getResource("/Apps-libreoffice-calc-icon.png")));
		calcule3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection =sqlConnection.bdConnector();
				
				try {
					
				// concatination pour les deux  date 
				String d1 = String.format("%s-%s-%s",comboBoxj1.getSelectedItem().toString(),comboBoxm1.getSelectedItem().toString(),debut.getText());
				String d2= String.format("%s-%s-%s",comboBoxj2.getSelectedItem().toString(),comboBoxm2.getSelectedItem().toString(),fin.getText());
				String mal= chro.getText(); 
				String sql="select count(*)from STATISTIQUECHRO where maladie_chronique = '"+mal+"' and DATE_RDV between '"+d1+"'and '"+ d2+"'";
				ps=connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(); 
				// affichage da la table statistique par peroide et maladie chronique 
				if(rs.next()) {
					String add = rs.getString("count(*)");
					resultat_chro.setText(add);}
				String sql1="select * from STATISTIQUECHRO where maladie_chronique = '"+mal+"' and DATE_RDV between '"+d1+"'and '"+ d2+"'";
				PreparedStatement pst=connection.prepareStatement(sql1);
				ResultSet rs1=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs1));
				
			   // nbTemps.setText(String.valueOf(rs));
			    
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, " statiques :faites attentions il faut remplire les deux dates et le nom de la maladie chronique ");
					e1.printStackTrace();
				}
			}
		});
		calcule3.setBackground(new Color(255, 0, 0));
		calcule3.setBounds(358, 528, 116, 36);
		contentPane.add(calcule3);
		
		JButton menu = new JButton("retour au menu");
		menu.setBackground(new Color(153, 255, 153));
		menu.setIcon(new ImageIcon(Statistique.class.getResource("/home-next-icon.png")));
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu d= new Menu();
				d.setVisible(true);
				setVisible(false);
			}
		});
		menu.setFont(new Font("Tahoma", Font.BOLD, 13));
		menu.setBounds(545, 601, 175, 36);
		contentPane.add(menu);
		
		JButton fermer = new JButton("Se Deconnecter");
		fermer.setBackground(new Color(255, 153, 153));
		fermer.setIcon(new ImageIcon(Statistique.class.getResource("/Apps-Dialog-Shutdown-icon.png")));
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "etes-vous sure?","EXIT",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();}
			}
		});
		fermer.setFont(new Font("Tahoma", Font.BOLD, 13));
		fermer.setBounds(730, 601, 182, 39);
		contentPane.add(fermer);
		@SuppressWarnings("unused")
		Image img= new ImageIcon(this.getClass().getResource("")).getImage();
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5 .setIcon(new ImageIcon(Statistique.class.getResource("/statistique.png")));
		lblNewLabel_5.setBounds(0, 0, 942, 651);
		contentPane.add(lblNewLabel_5);
	}
}
