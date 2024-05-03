package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Persona;

public class Hamburger extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIndex, btnSettings, btnContact, btnAdministration, btnLogout;
	private JPanel panel, panel2;

	// Controlador
	private Controlador controladorRutas;
	private Login login;
	private Persona persona;
	private boolean oscuro;

	/**
	 * Create the frame.
	 * 
	 * @param b
	 */
	public Hamburger(Main main, boolean b) {
		super(main);
		setModal(b);
		Hamburguesa(controladorRutas, false);
	}

	public void Hamburguesa(Controlador controladorRutas, boolean oscuro) {
		this.oscuro = oscuro;
		setBounds(100, 100, 700, 709);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(337, 11, 6, 144);
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel);

		panel2 = new JPanel();
		panel2.setBounds(337, 499, 6, 158);
		panel2.setBackground(Color.BLACK);
		contentPane.add(panel2);

		btnIndex = createButton("Inicio");
		btnIndex.addActionListener(this);
		btnIndex.setBounds(254, 183, 176, 42);
		contentPane.add(btnIndex);

		btnSettings = createButton("Ajustes");
		btnSettings.addActionListener(this);
		btnSettings.setBounds(254, 247, 176, 42);
		contentPane.add(btnSettings);

		btnContact = createButton("Contacto");
		btnContact.addActionListener(this);
		btnContact.setBounds(254, 309, 176, 42);
		contentPane.add(btnContact);

		btnAdministration = createButton("Administración");
		btnAdministration.addActionListener(this);
		btnAdministration.setBounds(240, 373, 202, 42);
		contentPane.add(btnAdministration);

		btnLogout = createButton("Cerrar sesión");
		btnLogout.setBounds(254, 437, 176, 42);
		contentPane.add(btnLogout);
		if (oscuro) {
			cambioFondo();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnIndex)) {
			inicio(oscuro);
		}
		if (e.getSource().equals(btnSettings)) {
			ajustes(oscuro);
		}
		if (e.getSource().equals(btnContact)) {
			contacto();
		}
		if (e.getSource().equals(btnAdministration)) {
			administracion(controladorRutas, oscuro);
		}
		if (e.getSource().equals(btnLogout)) {
			logout(oscuro);
		}
	}

	private JButton createButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn.setContentAreaFilled(false); // Establece el área de contenido del botón como no relleno
		btn.setOpaque(false); // Hace que el botón sea transparente
		btn.setBorderPainted(false); // Oculta el borde del botón
		btn.setForeground(Color.BLACK); // Establece el color del texto del botón
		return btn;
	}

	protected void logout(boolean oscuro) {
		Login log = new Login(controladorRutas, persona, oscuro);
		log.setVisible(true);
		this.dispose();
	}

	protected void administracion(Controlador controladorRutas, boolean oscuro) {
		Administracion admin = new Administracion(controladorRutas, oscuro);
		admin.setVisible(true);
		this.dispose();

	}

	protected void contacto() {
		Contacto contact = new Contacto();
		contact.setVisible(true);
		this.dispose();
	}

	protected void ajustes(boolean oscuro) {
		Ajustes settings = new Ajustes(oscuro, this, true);
		settings.setVisible(true);
		this.dispose();
	}

	protected void inicio(boolean oscuro) {
		Main index = new Main(login, oscuro, controladorRutas);
		index.setVisible(true);
		this.dispose();
	}

	private void cambioFondo() {
		panel.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		btnIndex.setForeground(Color.WHITE);
		btnAdministration.setForeground(Color.WHITE);
		btnContact.setForeground(Color.WHITE);
		btnLogout.setForeground(Color.WHITE);
		btnSettings.setForeground(Color.WHITE);
		contentPane.setBackground(Color.DARK_GRAY);

	}

}