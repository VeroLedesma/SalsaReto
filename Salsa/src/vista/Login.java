
package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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

public class Login extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel BodyLayout, panelLeft, panelRight;
	private JLabel lblLogo, labelEmail, labelPassword, labelNoRegister, linkRegister;
	private JTextField inputEmail;
	private JPasswordField inputPassword;
	private JButton toggleButton, btnLogin;
	private List<Persona> personas = new ArrayList<>();
	// Lógica para la conexión
	// private Controlador controladorRutas;
	private Persona persona = new Persona();
	private JLabel lblImagenLogin;
	
	//Constructor vacio para el cierre de sesión

	// Página de Inicio
	public Login(Persona persona) {
		// this.controladorRutas = controladorRutas;
		this.persona = persona;
		setBounds(100, 100, 931, 574);
		BodyLayout = new JPanel();
		BodyLayout.setBackground(new Color(255, 255, 255));
		BodyLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BodyLayout);
		BodyLayout.setLayout(null);
		ImageIcon icon = new ImageIcon(getClass().getResource("/assets/logo.png"));

		// Division Login
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

		// Establece el icono "nover" por defecto
		toggleButton = new JButton(new ImageIcon(getClass().getResource("/assets/icons/nover.png")));
		toggleButton.setBounds(412, 313, 29, 20);
		// Establece el botón como transparente
		toggleButton.setOpaque(false);
		// No rellena el área del botón
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

		labelEmail = new JLabel("Correo electrónico");
		labelEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelEmail.setBounds(74, 171, 157, 14);
		panelRight.add(labelEmail);

		inputEmail = new JTextField();
		inputEmail.setToolTipText("");
		inputEmail.setBounds(74, 196, 378, 51);
		inputEmail.setColumns(10);
		panelRight.add(inputEmail);

		labelPassword = new JLabel("Contraseña");
		labelPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelPassword.setBounds(74, 270, 123, 14);
		panelRight.add(labelPassword);

		inputPassword = new JPasswordField();
		inputPassword.setBounds(74, 295, 378, 51);
		panelRight.add(inputPassword);

		btnLogin = new JButton("Iniciar sesión");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(74, 381, 378, 45);
		panelRight.add(btnLogin);

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

		// Botones de eventos
		linkRegister.addMouseListener(this);
		btnLogin.addActionListener(this);
		toggleButton.addActionListener(this);

	}



	// Visualizar/Desvisualizar la contraseña
	private void togglePasswordVisibility() {
		// Si el echoChar es '•', cambiar a mostrar texto, de lo contrario, ocultar
		// texto
		char echoChar = (inputPassword.getEchoChar() == '\u2022') ? '\u0000' : '\u2022';
		// Establecer el echoChar según la lógica anterior
		inputPassword.setEchoChar(echoChar);
		// Cambiar el icono según el echoChar
		toggleButton.setIcon((echoChar == '\u2022') ? new ImageIcon(getClass().getResource("/assets/icons/ver.png"))
				: new ImageIcon(getClass().getResource("/assets/icons/nover.png")));
	}

	// Métodos para ahorrar los action listener
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(linkRegister)) {
			Register registro = new Register(this, true, persona);
			registro.setVisible(true);
			setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(toggleButton)) {
			togglePasswordVisibility();
		}
		if (e.getSource().equals(btnLogin)) {
			logicaLogin();
		}
	}

	public void logicaLogin() {
		// Obtener los valores de correo electrónico y contraseña de los campos de
		// entrada
		String email = inputEmail.getText().trim();
		String password = new String(inputPassword.getPassword());
		boolean correcto = false, existe = false;
		// Si la persona no tiene ningún dato le mandamos error
		correcto = camposVacios(correcto);
		if (correcto == false) {
			JOptionPane.showMessageDialog(this, "Por favor, introduzca todos los campos obligatorios.",
					"Campos obligatorios incompletos", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje de error
			borrar();
		} else {
			existe = Controlador.iniciarSesion(email, password);

			if (existe == true) {
				// le enviamos un mensaje de bienvenida
				JOptionPane.showMessageDialog(null, "Bienvenido/a al sistema");
				this.setVisible(false);
				Main vent = new Main(this, true);
				vent.setVisible(true);

				this.dispose();
			} else if (existe == false) {
				// si el inicio de sesion es incorrecto le enviaremos un mensaje de error
				JOptionPane.showMessageDialog(this, "el email o la contraseña son incorrectos.",
						"porfavor introduzca valores validos", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	// Metodo para recoger los campos vacios de los input
	private boolean camposVacios(boolean correcto) {
		if (inputEmail.getText().trim().isEmpty() || new String(inputPassword.getPassword()).trim().isEmpty()) {
			correcto = false;
		} else {
			correcto = true;
		}
		return correcto;

	}


	
	// Limpiamos los datos tecleados del formulario
	private void borrar() {
		inputEmail.setText("");

		inputPassword.setText("");
		inputEmail.requestFocus();
	}

	// Implementación de los métodos MouseListener (no son necesarios, pero necesito
	// implemenetarlos en la clase si quiero hacer lo de los eventos)
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