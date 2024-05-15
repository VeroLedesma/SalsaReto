package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Persona;

/**
 * La clase VLogin representa una ventana de inicio de sesión.
 * Permite a los usuarios ingresar su correo electrónico y contraseña para acceder al sistema.
 * Implementa ActionListener y MouseListener para manejar eventos de acción y de ratón.
 */
public class VLogin extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel BodyLayout, panelLeft, panelRight;
	private JLabel lblLogo, labelEmail, labelPassword, labelNoRegister, linkRegister;
	private JTextField inputEmail;
	private JPasswordField inputPassword;
	private JButton toggleButton, btnLogin;
	private Persona persona = new Persona();
	private JLabel lblImagenLogin;

	/**
	 * Constructor de la clase VLogin.
	 * 
	 * @param persona objeto Persona para manejar los datos del usuario
	 */
	public VLogin(Persona persona) {
		this.persona = persona;
		setBounds(100, 100, 931, 574);
		BodyLayout = new JPanel();
		BodyLayout.setBackground(new Color(255, 255, 255));
		BodyLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BodyLayout);
		this.setLocationRelativeTo(null);
		BodyLayout.setLayout(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/assets/logo.png"));

		// División del Login
		panelLeft = new JPanel();
		panelLeft.setBackground(new Color(255, 255, 255));
		panelLeft.setBounds(0, 0, 416, 539);
		BodyLayout.add(panelLeft);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/assets/imagenLogin.jpeg"));
		panelLeft.setLayout(null);

		lblImagenLogin = new JLabel("");
		lblImagenLogin.setBounds(0, 0, 416, 539);
		panelLeft.add(lblImagenLogin);
		ImageIcon imagen1 = new ImageIcon(imagen.getImage().getScaledInstance(lblImagenLogin.getWidth(),
				lblImagenLogin.getHeight(), Image.SCALE_SMOOTH));
		lblImagenLogin.setIcon(imagen1);

		panelRight = new JPanel();
		panelRight.setBackground(new Color(255, 255, 255));
		panelRight.setBounds(414, 0, 501, 539);
		BodyLayout.add(panelRight);
		panelRight.setLayout(null);

		// Botón de visualización/ocultación de contraseña
		toggleButton = new JButton(new ImageIcon(getClass().getResource("/assets/icons/nover.png")));
		toggleButton.setBounds(412, 313, 29, 20);
		toggleButton.setOpaque(false);
		toggleButton.setContentAreaFilled(false);
		toggleButton.setBorderPainted(false);
		panelRight.add(toggleButton);

		// Logo centrado
		lblLogo = new JLabel("");
		lblLogo.setBounds(132, 66, 245, 51);
		panelRight.add(lblLogo);
		ImageIcon img = new ImageIcon(
				icon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(img);

		// Campo de correo electrónico
		labelEmail = new JLabel("Correo electrónico");
		labelEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelEmail.setBounds(74, 171, 157, 14);
		panelRight.add(labelEmail);

		inputEmail = new JTextField();
		inputEmail.setToolTipText("");
		inputEmail.setBounds(74, 196, 378, 51);
		inputEmail.setColumns(10);
		panelRight.add(inputEmail);

		// Campo de contraseña
		labelPassword = new JLabel("Contraseña");
		labelPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelPassword.setBounds(74, 270, 123, 14);
		panelRight.add(labelPassword);

		inputPassword = new JPasswordField();
		inputPassword.setBounds(74, 295, 378, 51);
		panelRight.add(inputPassword);

		// Botón de inicio de sesión
		btnLogin = new JButton("Iniciar sesión");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(74, 381, 378, 45);
		panelRight.add(btnLogin);

		// Enlace para registrar una nueva cuenta
		labelNoRegister = new JLabel("¿No tienes cuenta? Click aquí para");
		labelNoRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelNoRegister.setBounds(74, 454, 227, 14);
		labelNoRegister.setHorizontalAlignment(SwingConstants.LEFT);
		panelRight.add(labelNoRegister);

		linkRegister = new JLabel("registrarme");
		linkRegister.setBounds(311, 455, 130, 14);
		linkRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		linkRegister.setForeground(new Color(0, 128, 255));
		linkRegister.setHorizontalAlignment(SwingConstants.LEFT);
		panelRight.add(linkRegister);

		// Agregar listeners para eventos
		linkRegister.addMouseListener(this);
		btnLogin.addActionListener(this);
		toggleButton.addActionListener(this);
	}

	/**
	 * Alterna la visibilidad de la contraseña en el campo de entrada.
	 */
	private void togglePasswordVisibility() {
		char echoChar = (inputPassword.getEchoChar() == '\u2022') ? '\u0000' : '\u2022';
		inputPassword.setEchoChar(echoChar);
		toggleButton.setIcon((echoChar == '\u2022') ? new ImageIcon(getClass().getResource("/assets/icons/ver.png"))
				: new ImageIcon(getClass().getResource("/assets/icons/nover.png")));
	}

	/**
	 * Método para manejar eventos de clic del ratón.
	 * 
	 * @param e el evento de clic del ratón
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(linkRegister)) {
			VRegister registro = new VRegister(this, true, persona, 0, "");
			setVisible(false);
			registro.setLocationRelativeTo(this);
			registro.setVisible(true);
		}
	}

	/**
	 * Método para manejar eventos de acción.
	 * 
	 * @param e el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(toggleButton)) {
			togglePasswordVisibility();
		}
		if (e.getSource().equals(btnLogin)) {
			logicaLogin();
		}
	}

	/**
	 * Lógica para el inicio de sesión del usuario.
	 */
	public void logicaLogin() {
		String email = inputEmail.getText().trim();
		String password = new String(inputPassword.getPassword());
		boolean correcto = false, existe = false;
		correcto = camposVacios(correcto);
		if (!correcto) {
			JOptionPane.showMessageDialog(this, "Por favor, introduzca todos los campos obligatorios.",
					"Campos obligatorios incompletos", JOptionPane.ERROR_MESSAGE);
			borrar();
		} else {
			existe = Controlador.iniciarSesion(email, password);
			if (existe) {
				JOptionPane.showMessageDialog(null, "Bienvenido/a al sistema");
				this.setVisible(false);
				VMain vent = new VMain(this, true);
				vent.setLocationRelativeTo(this);
				vent.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "El email o la contraseña son incorrectos.",
						"Por favor, introduzca valores válidos", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Comprueba si los campos de entrada están vacíos.
	 * 
	 * @param correcto booleano que indica si los campos están completos
	 * @return true si los campos no están vacíos, false en caso contrario
	 */
	private boolean camposVacios(boolean correcto) {
		if (inputEmail.getText().trim().isEmpty() || new String(inputPassword.getPassword()).trim().isEmpty()) {
			correcto = false;
		} else {
			correcto = true;
		}
		return correcto;
	}

	/**
	 * Limpia los datos ingresados en el formulario.
	 */
	private void borrar() {
		inputEmail.setText("");
		inputPassword.setText("");
		inputEmail.requestFocus();
	}

	// Métodos vacíos para implementar MouseListener (necesarios para la clase)
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
