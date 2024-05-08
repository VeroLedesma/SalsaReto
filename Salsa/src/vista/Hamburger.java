package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Persona;

public class Hamburger extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIndex, btnSettings, btnContact, btnAdministration, btnLogout;
	private JPanel panel, panel2;

	// Controlador
	// private Controlador controladorRutas;
	private Login login;
	private Persona persona;
	private boolean modal;
	/**
	 * Create the frame.
	 * 
	 * @param b
	 * @param main
	 */
	public Hamburger(Main main, boolean b) {
		super(main);
		setModal(b);
		Hamburguesa();
	}
	

	public void Hamburguesa() {
		setBounds(100, 100, 700, 709);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(337, 11, 6, 144);
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel);

		panel2 = new JPanel();
		panel2.setBounds(337, 499, 6, 158);
		panel2.setBackground(Color.BLACK);
		contentPane.add(panel2);

		btnIndex = new JButton("Inicio");
		btnIndex.addActionListener(this);
		btnIndex.setBounds(254, 183, 176, 42);
		contentPane.add(btnIndex);

		btnSettings = new JButton("Ajustes");
		btnSettings.addActionListener(this);
		btnSettings.setBounds(254, 247, 176, 42);
		contentPane.add(btnSettings);

		btnContact = new JButton("Contacto");
		btnContact.addActionListener(this);
		btnContact.setBounds(254, 309, 176, 42);
		contentPane.add(btnContact);

		btnAdministration = new JButton("Administración");
		btnAdministration.addActionListener(this);
		btnAdministration.setBounds(240, 373, 202, 42);
		contentPane.add(btnAdministration);

		btnLogout = new JButton("Cerrar sesión");
		btnLogout.addActionListener(this);
		btnLogout.setBounds(254, 437, 176, 42);
		contentPane.add(btnLogout);
//		if (oscuro) {
//			cambioFondo();
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnIndex)) {
			inicio();
		}
		if (e.getSource().equals(btnSettings)) {
			ajustes();
		}
		if (e.getSource().equals(btnContact)) {
			contacto();
		}
		if (e.getSource().equals(btnAdministration)) {
			administracion();
		}
		if (e.getSource().equals(btnLogout)) {
			logout();
		}
	}

	public void logout() {
		Login log = new Login(persona);
		log.setVisible(true);
		this.dispose();
	}

	public void administracion() {
		Administracion admin = new Administracion(true,this);
		admin.setVisible(true);
		this.dispose();

	}

	public void contacto() {
		Contacto contact = new Contacto(this, true);
		contact.setVisible(true);
		this.dispose();
	}

	public void ajustes() {
		Ajustes settings = new Ajustes(this, true);
		settings.setVisible(true);
		this.dispose();
	}

	public void inicio() {
		Main index = new Main(login, modal);
		index.setVisible(true);
		this.dispose();
	}

//	public void cambioFondo() {
//		panel.setBackground(Color.WHITE);
//		panel2.setBackground(Color.WHITE);
//		btnIndex.setForeground(Color.WHITE);
//		btnAdministration.setForeground(Color.WHITE);
//		btnContact.setForeground(Color.WHITE);
//		btnLogout.setForeground(Color.WHITE);
//		btnSettings.setForeground(Color.WHITE);
//		contentPane.setBackground(Color.DARK_GRAY);
//
//	}

}