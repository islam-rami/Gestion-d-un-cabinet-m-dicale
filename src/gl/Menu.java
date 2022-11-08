package gl;
/**
 * dans cet JFrame on va crrer un menu propre a un medecin qui va organiser tout ses fonctionnalité
 * 
 *  pour cela on va creer des boutton qui vont permettre d'ouvrir les autres fenetres ainsi a chaque 
 *  
 *  ouverture d'une fenetre l'autre sera fermer automatiquement pour nr pas encomprer  
 *  
 * 
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

@SuppressWarnings({ "unused", "serial" })
public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("Menu medecin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1147, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("M\u00E9decins notre engagement c'est pour la vie ");
		lblNewLabel.setForeground(new Color(51, 0, 153));
		lblNewLabel.setBounds(417, 476, 687, 105);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblNewLabel);
		// fenetre consultation 
		JButton btnListeRendrezVous = new JButton("Consultation d'un Patient\r\n");
		btnListeRendrezVous.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeRendrezVous  .setIcon(new ImageIcon(Patient_.class.getResource("/aaa-no-sh-icon.png")));
		btnListeRendrezVous.setBackground(new Color(102, 153, 255));
		btnListeRendrezVous.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnListeRendrezVous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Consultation l= new Consultation();
				l.setVisible(true);
				

				setVisible(false);

			}
		});
		btnListeRendrezVous.setBounds(0, 157, 352, 80);
		contentPane.add(btnListeRendrezVous);
	// la fenetre agenda
		JButton btnNewButton = new JButton("Consulter l'Agenda des RDV");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		 btnNewButton .setIcon(new ImageIcon(Patient_.class.getResource("/Actions-view-calendar-timeline-icon.png")));

		btnNewButton.setBackground(new Color(102, 153, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agenda d= new Agenda();
				
				
				d.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(0, 233, 352, 80);
		contentPane.add(btnNewButton);
		// fenetre ordonnance 
		JButton btnListeRendrezVous_1 = new JButton("Ordonnance");
		btnListeRendrezVous_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeRendrezVous_1.setBackground(new Color(102, 153, 255));
		btnListeRendrezVous_1 .setIcon(new ImageIcon(Patient_.class.getResource("/medical-report-icon.png")));

		btnListeRendrezVous_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ordonance d= new Ordonance();
				d.setVisible(true);
				setVisible(false);
			}
		});
		btnListeRendrezVous_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnListeRendrezVous_1.setBounds(0, 386, 352, 73);
		contentPane.add(btnListeRendrezVous_1);
	  // fenetre patient	
		JButton btnAjouterPatient = new JButton("Ajouter Patient");
		btnAjouterPatient.setHorizontalAlignment(SwingConstants.LEFT);
		btnAjouterPatient .setIcon(new ImageIcon(Patient_.class.getResource("/People-Patient-Male-icon.png")));

		btnAjouterPatient.setBackground(new Color(102, 153, 255));
		btnAjouterPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient_ p= new Patient_();
				p.setVisible(true);
				setVisible(false);
			}
		});
		btnAjouterPatient.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAjouterPatient.setBounds(0, 0, 352, 80);
		contentPane.add(btnAjouterPatient);
		
		JButton btnDossierMdicale = new JButton("Dossier M\u00E9dicale");
		btnDossierMdicale.setHorizontalAlignment(SwingConstants.LEFT);
		btnDossierMdicale .setIcon(new ImageIcon(Patient_.class.getResource("/Actions-contact-new-icon.png")));
		btnDossierMdicale.setBackground(new Color(102, 153, 255));
		btnDossierMdicale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DossierMedicale d= new DossierMedicale();
				d.setVisible(true);
				setVisible(false);
			}
		});
		btnDossierMdicale.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDossierMdicale.setBounds(0, 80, 352, 78);
		contentPane.add(btnDossierMdicale);
		// fenetre statistique
		JButton btnListeRendrezVous_2_1 = new JButton("Consulter Les Statistiques");
		btnListeRendrezVous_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeRendrezVous_2_1 .setIcon(new ImageIcon(Patient_.class.getResource("/SEO-icon.png")));
		btnListeRendrezVous_2_1.setBackground(new Color(102, 153, 255));
		btnListeRendrezVous_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statistique d= new Statistique();
				d.setVisible(true);
				setVisible(false);
			}
		});
		btnListeRendrezVous_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnListeRendrezVous_2_1.setBounds(0, 309, 352, 80);
		contentPane.add(btnListeRendrezVous_2_1);
		// deconnexion
		JButton btnNewButton_1 = new JButton("Se Deconnecter");
		btnNewButton_1.setBackground(new Color(102, 153, 255));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon(Patient_.class.getResource("/Logout-icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "voulez-vous Sortir ?","Se Deconnecter",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();
				}	}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(0, 534, 352, 80);
		contentPane.add(btnNewButton_1);
		// paramettre de comptre cette fenetre c'es juste si on vuet ajouter un medecin ou une secritaire
		JButton btnNewButton_2 = new JButton("Parametre de compte");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setIcon(new ImageIcon(Patient_.class.getResource("/user-info-icon.png")));

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user us= new user();
				us.setVisible(true);
				setVisible(false);
				
			}
		});
		btnNewButton_2.setBackground(new Color(102, 153, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.setBounds(0, 456, 352, 80);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Patient_.class.getResource("/sante.jpg")));
		lblNewLabel_1.setBounds(295, -37, 887, 651);
		contentPane.add(lblNewLabel_1);
	}
}
