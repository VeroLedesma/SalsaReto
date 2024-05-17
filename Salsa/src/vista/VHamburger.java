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

/**
 * La clase VHamburger representa la ventana principal con un menú de navegación
 * tipo "hamburger". Hereda de JDialog e implementa ActionListener para manejar
 * eventos de botones.
 */
public class VHamburger extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIndex, btnContact, btnAdministration, btnLogout;
	private JPanel panel, panel2;

	private VLogin login;
	private Persona persona;
	private boolean modal;

	/**
	 * Constructor de la clase VHamburger.
	 * 
	 * @param main  referencia a la ventana principal
	 * @param modal indica si el diálogo es modal
	 */
	public VHamburger(VMain main, boolean modal) {
		super(main);
		setModal(modal);
		Hamburguesa();
		btnLogout.addActionListener(this);
	}

	/**
	 * Inicializa los componentes de la interfaz gráfica.
	 */
	public void Hamburguesa() {
		setBounds(100, 100, 700, 709);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		// Paneles decorativos
		panel = new JPanel();
		panel.setBounds(337, 11, 6, 227);
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel);

		panel2 = new JPanel();
		panel2.setBounds(337, 499, 6, 158);
		panel2.setBackground(Color.BLACK);
		contentPane.add(panel2);

		// Botones del menú de navegación
		btnIndex = new JButton("Inicio");
		btnIndex.addActionListener(this);
		btnIndex.setBounds(254, 241, 176, 42);
		contentPane.add(btnIndex);

		btnContact = new JButton("Contacto");
		btnContact.addActionListener(this);
		btnContact.setBounds(254, 309, 176, 42);
		contentPane.add(btnContact);

		btnAdministration = new JButton("Administración");
		btnAdministration.addActionListener(this);
		btnAdministration.setBounds(254, 373, 177, 42);
		contentPane.add(btnAdministration);

		btnLogout = new JButton("Cerrar sesión");
		btnLogout.setBounds(254, 437, 176, 42);
		contentPane.add(btnLogout);
	}

	/**
	 * Maneja los eventos de los botones en la ventana.
	 * 
	 * @param e el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnIndex)) {
			inicio();
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

	/**
	 * Cierra la sesión del usuario y muestra la ventana de login.
	 */
	public void logout() {
		this.dispose();
		VLogin log = new VLogin(persona);
		log.setLocationRelativeTo(this);
		log.setVisible(true);
	}

	/**
	 * Muestra la ventana de administración.
	 */
	public void administracion() {
		VAdministracion admin = new VAdministracion(this, true);
		this.dispose();
		admin.setLocationRelativeTo(this);
		admin.setVisible(true);
	}

	/**
	 * Muestra la ventana de contacto.
	 */
	public void contacto() {
		VContacto contact = new VContacto(this, true);
		this.dispose();
		contact.setLocationRelativeTo(this);
		contact.setVisible(true);
	}

	/**
	 * Muestra la ventana principal.
	 */
	public void inicio() {
		VMain index = new VMain(login, modal, "");
		this.dispose();
		index.setLocationRelativeTo(this);
		index.setVisible(true);
	}
}
