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

public class VHamburger extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIndex, btnSettings, btnContact, btnAdministration, btnLogout;
	private JPanel panel, panel2;

	// Controlador
	// private Controlador controladorRutas;
	private VLogin login;
	private Persona persona;
	private boolean modal;

	/**
	 * Create the frame.
	 * 
	 * @param b
	 * @param main
	 */
	public VHamburger(VMain main, boolean b) {
		super(main);
		setModal(b);
		Hamburguesa();
		btnLogout.addActionListener(this);
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

		this.dispose();
		VLogin log = new VLogin(persona);
		log.setVisible(true);
	}

	public void administracion() {
		VAdministracion admin = new VAdministracion(this, true);
		admin.setVisible(true);
		this.dispose();

	}

	public void contacto() {
		VContacto contact = new VContacto(this, true);
		contact.setVisible(true);
		this.dispose();
	}

	public void ajustes() {
		VAjustes settings = new VAjustes(this, true);
		settings.setVisible(true);
		this.dispose();
	}

	public void inicio() {
		VMain index = new VMain(login, modal);
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