package gl;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Menu2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu2 frame = new Menu2();
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
	public Menu2() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("g\u00E9rer les rendez vous");
		btnNewButton.setBackground(new Color(51, 102, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon(Menu2.class.getResource("/add-event-icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RendezVous d= new RendezVous ();
				d.setVisible(true);
				setVisible(false);
				
			}
		});
		btnNewButton.setBounds(0, -2, 259, 83);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agenda des RDV");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agenda d= new Agenda();
				d.menu.setEnabled(false);
				d.setVisible(true);
				setVisible(false);
				
			}
		});
		btnNewButton_1.setBackground(new Color(51, 102, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon(Menu2.class.getResource("/calendar.png")));
		btnNewButton_1.setBounds(0, 80, 259, 73);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Se deconnecter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Clickedbutton=	JOptionPane.showConfirmDialog(null, "voulez-vous Sortir ?","Se Deconnecter",JOptionPane.YES_NO_OPTION);
				if(Clickedbutton==JOptionPane.YES_OPTION) {
					
					dispose();
				}
			}
		});
		btnNewButton_2.setBackground(new Color(51, 102, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setIcon(new ImageIcon(Menu2.class.getResource("/Logout-icon.png")));
		btnNewButton_2.setBounds(0, 226, 259, 73);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Menu medecin");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autentifier d= new autentifier();
//				setVisible(true);
				d.frmAutentification.setVisible(true);
				setVisible(false);
				
			}
		});
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setIcon(new ImageIcon(Menu2.class.getResource("/doctor-icon.png")));
		btnNewButton_3.setBackground(new Color(51, 102, 255));
		btnNewButton_3.setBounds(0, 150, 259, 77);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(user.class.getResource("/secretaire.jpg")));
		lblNewLabel.setBounds(-67, 0, 609, 311);
		contentPane.add(lblNewLabel);
	}
}
