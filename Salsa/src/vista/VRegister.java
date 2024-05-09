package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.ZoneId;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;
import modelo.Persona;
import modelo.Sexo;
import modelo.Trabajador;
import modelo.Usuario;

public class VRegister extends JDialog implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JTextField textNombre, textEmail, textDni, textApellido, textDireccion, textNumeroSS;
	private JLabel Seleccione, lblPregunta, lblEmail, lblContrasena, lblDni, lblSexo, lblPrimerApellido,
			lblConfirmeLaContrasea, lblDireccion, lblNewLabel, lblDireccion_1, lblSexo_1, lblFechaDeRegistro,
			lblNmeroSeguridadSocial, lblInicioSesion, lblCamposObligatorios, lblNombre;
	private JButton btnRegistro;
	private JDateChooser dateFRegistro, dateFechaNacimiento;
	private JPasswordField passConfirmar, passContrasena;
	private JComboBox<Sexo> comboBoxGenero;
	private JCheckBox checkBoxUsuario, checkBoxTrabajador;

	// Lógica para la conexión
	// private Controlador controladorRutas;
	private Persona persona = new Persona();

	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnVolver;


	public VRegister(VLogin padre, boolean modal, Persona persona2) {
		super(padre);
		// this.controladorRutas = controladorRutas;
		this.persona = persona2;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				padre.setVisible(true);
			}
		});
		setModal(modal);
		setSize(729, 700);
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		getContentPane().setBackground(new Color(255, 255, 255));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().setLayout(null);

		textNombre = new JTextField();
		textNombre.setBounds(64, 126, 237, 29);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);

		lblNombre = new JLabel("Nombre *");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombre.setBounds(64, 101, 78, 14);
		getContentPane().add(lblNombre);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(64, 191, 237, 29);
		getContentPane().add(textEmail);

		lblEmail = new JLabel("Email *");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmail.setBounds(64, 166, 49, 14);
		getContentPane().add(lblEmail);

		lblContrasena = new JLabel("Contraseña*");
		lblContrasena.setFont(new Font("Dialog", Font.BOLD, 12));
		lblContrasena.setBounds(64, 231, 147, 14);
		getContentPane().add(lblContrasena);

		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(64, 321, 237, 29);
		getContentPane().add(textDni);

		lblDni = new JLabel("DNI*");
		lblDni.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDni.setBounds(64, 296, 147, 14);
		getContentPane().add(lblDni);

		lblSexo = new JLabel("Fecha de nacimiento ");
		lblSexo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSexo.setBounds(456, 166, 147, 14);
		getContentPane().add(lblSexo);

		lblPrimerApellido = new JLabel("Primer apellido*");
		lblPrimerApellido.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPrimerApellido.setBounds(456, 101, 131, 14);
		getContentPane().add(lblPrimerApellido);

		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(456, 126, 237, 29);
		getContentPane().add(textApellido);

		lblConfirmeLaContrasea = new JLabel("Confirma la contraseña*");
		lblConfirmeLaContrasea.setFont(new Font("Dialog", Font.BOLD, 12));
		lblConfirmeLaContrasea.setBounds(456, 231, 147, 14);
		getContentPane().add(lblConfirmeLaContrasea);

		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(456, 321, 237, 29);
		getContentPane().add(textDireccion);

		lblDireccion = new JLabel("Dirección *");
		lblDireccion.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDireccion.setBounds(456, 296, 147, 14);
		getContentPane().add(lblDireccion);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		lblNewLabel.setBounds(235, 11, 278, 62);
		getContentPane().add(lblNewLabel);

		lblDireccion_1 = new JLabel("");
		lblDireccion_1.setBounds(321, 450, 147, 14);
		getContentPane().add(lblDireccion_1);

		lblSexo_1 = new JLabel("Sexo");
		lblSexo_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSexo_1.setBounds(64, 374, 49, 14);
		getContentPane().add(lblSexo_1);

		btnRegistro = new JButton("Enviar los datos");
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegistro.setForeground(new Color(0, 0, 0));

		btnRegistro.setForeground(Color.WHITE);

		btnRegistro.addActionListener(this);
		btnRegistro.setBackground(new Color(0, 0, 255));
		btnRegistro.setBounds(227, 575, 286, 38);
		getContentPane().add(btnRegistro);

		dateFechaNacimiento = new JDateChooser();
		dateFechaNacimiento.getCalendarButton().addActionListener(this);
		dateFechaNacimiento.setBounds(456, 197, 165, 23);
		getContentPane().add(dateFechaNacimiento);

		lblCamposObligatorios = new JLabel("Campos obligatorios *");

		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCamposObligatorios.setBounds(122, 542, 147, 23);
		getContentPane().add(lblCamposObligatorios);

		checkBoxUsuario = new JCheckBox("Usuario", false);
		checkBoxUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCamposObligatorios.setBounds(122, 550, 286, 14);
		getContentPane().add(lblCamposObligatorios);

		checkBoxUsuario = new JCheckBox("Usuario", false);

		checkBoxUsuario.setBackground(new Color(255, 255, 255));
		getContentPane().add(checkBoxUsuario);
		checkBoxUsuario.setBounds(64, 507, 99, 23);
		getContentPane().add(checkBoxUsuario);

		checkBoxTrabajador = new JCheckBox("Trabajador", false);

		checkBoxTrabajador.setFont(new Font("Tahoma", Font.BOLD, 12));

		checkBoxTrabajador.setBackground(new Color(255, 255, 255));
		getContentPane().add(checkBoxTrabajador);
		checkBoxTrabajador.setBounds(162, 507, 99, 23);
		getContentPane().add(checkBoxTrabajador);

		Seleccione = new JLabel("Seleccione");
		Seleccione.setFont(new Font("Tahoma", Font.BOLD, 11));
		Seleccione.setBounds(64, 467, 131, 14);
		getContentPane().add(Seleccione);

		lblFechaDeRegistro = new JLabel("Fecha de registro ");
		lblFechaDeRegistro.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFechaDeRegistro.setBounds(456, 466, 147, 14);
		getContentPane().add(lblFechaDeRegistro);

		dateFRegistro = new JDateChooser();
		dateFRegistro.setBounds(456, 495, 165, 23);
		getContentPane().add(dateFRegistro);

		textNumeroSS = new JTextField();
		textNumeroSS.setColumns(10);
		textNumeroSS.setBounds(456, 412, 237, 29);
		getContentPane().add(textNumeroSS);

		lblNmeroSeguridadSocial = new JLabel("Número Seguridad Social *");
		lblNmeroSeguridadSocial.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNmeroSeguridadSocial.setBounds(456, 387, 214, 14);
		getContentPane().add(lblNmeroSeguridadSocial);

		passConfirmar = new JPasswordField();
		passConfirmar.setBounds(456, 256, 237, 29);
		getContentPane().add(passConfirmar);

		passContrasena = new JPasswordField();
		passContrasena.setBounds(64, 256, 237, 29);
		getContentPane().add(passContrasena);

		comboBoxGenero = new JComboBox<>();
		comboBoxGenero.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBoxGenero.setModel(new DefaultComboBoxModel<>(Sexo.values()));
		comboBoxGenero.setToolTipText("");
		comboBoxGenero.setEditable(true);
		comboBoxGenero.setBounds(64, 406, 131, 21);
		getContentPane().add(comboBoxGenero);

		// No se muestran los campos específicos de cada perfil
		// Deshabilitar el campo de número de seguridad social
		lblNmeroSeguridadSocial.setVisible(false);
		textNumeroSS.setVisible(false);
		// Mostrar el label y el campo de fecha de registro
		lblFechaDeRegistro.setVisible(false);
		dateFRegistro.setVisible(false);

		lblPregunta = new JLabel("¿Ya tienes cuenta?");
		lblPregunta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPregunta.setBounds(214, 623, 123, 16);
		getContentPane().add(lblPregunta);

		lblInicioSesion = new JLabel("Inicia Sesion");
		lblInicioSesion.setBounds(370, 623, 98, 14);
		lblInicioSesion.addMouseListener(this);
		lblInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInicioSesion.setForeground(new Color(0, 51, 255));
		getContentPane().add(lblInicioSesion);

		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModificar.setBounds(36, 577, 245, 34);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(425, 575, 245, 34);
		getContentPane().add(btnEliminar);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(561, 10, 144, 29);
		btnVolver.addActionListener(this);
		getContentPane().add(btnVolver);


		lblPregunta.setBounds(265, 626, 114, 13);
		getContentPane().add(lblPregunta);

		lblInicioSesion = new JLabel("Inicia Sesion");
		lblInicioSesion.setBounds(370, 624, 78, 13);
		lblInicioSesion.addMouseListener(this);
		lblInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInicioSesion.setForeground(new Color(0, 51, 255));
		getContentPane().add(lblInicioSesion);


		// Botones de eventos
		checkBoxUsuario.addActionListener(this);
		checkBoxTrabajador.addActionListener(this);
	}

	protected void inicioSesion() {
		VLogin log = new VLogin(persona);
		log.setVisible(true);
		setVisible(false);

	}

//	private void cambioFondo() {
//		// contentPane.setBackground(Color.DARK_GRAY); Acá creo que debo ver si viene
//		// del padre
//		lblFechaDeRegistro.setForeground(Color.WHITE);
//		lblConfirmeLaContrasea.setForeground(Color.WHITE);
//		lblContrasena.setForeground(Color.WHITE);
//		lblDireccion.setForeground(Color.WHITE);
//		lblDni.setForeground(Color.WHITE);
//		lblEmail.setForeground(Color.WHITE);
//		lblNombre.setForeground(Color.WHITE);
//		lblPrimerApellido.setForeground(Color.WHITE);
//		lblFecNa.setForeground(Color.WHITE);
//		lblCamposObligatorios.setForeground(Color.WHITE);
//		Seleccione.setForeground(Color.WHITE);
//		lblNmeroSeguridadSocial.setForeground(Color.WHITE);
//		lblPregunta.setForeground(Color.WHITE);
//		lblSexo_1.setForeground(Color.WHITE);
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		inicioSesion();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnRegistro)) {
			if (camposObligatoriosCompletos() == true) {
				registrarPersona();
			}
		}
		if (e.getSource().equals(checkBoxUsuario)) {
			seleccionarUsuario();
		}
		if (e.getSource().equals(checkBoxTrabajador)) {
			seleccionarTrabajador();
		}
		if (e.getSource().equals(btnRegistro)) {
			if (btnRegistro.isShowing() && camposObligatoriosCompletos() == false) {
				JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.",
						"Campos obligatorios incompletos", JOptionPane.ERROR_MESSAGE);
			} else if (btnRegistro.isShowing() && camposObligatoriosCompletos() == true) {
				this.dispose();
			}
		}

		if (e.getSource().equals(btnVolver)) {
			volver();
		}
	}

	private void volver() {
		VListarUsuarios ven = new VListarUsuarios(null, true, persona);
		ven.setVisible(true);
		this.dispose();

	}

	private boolean camposObligatoriosCompletos() {

		boolean correcto = false, dniValido = false, emailValido = false;
		char[] contrasena = passContrasena.getPassword();
		char[] confirmarContrasena = passConfirmar.getPassword();

		String dni = textDni.getText();
		String email = textEmail.getText();
		Persona per = new Persona();
		// Validar DNI y ema
		dniValido = per.validarDNI(dni);
		emailValido = per.validarEmail(email);

		// Verificar que todos los campos obligatorios estén completos
		if (!textNombre.getText().isEmpty() && contrasena.length >= 5 && contrasena.length <= 8 && dniValido
				&& emailValido && !email.isEmpty() && confirmarContrasena.length > 0
				&& (checkBoxUsuario.isSelected() || checkBoxTrabajador.isSelected())
				&& dateFechaNacimiento.getDate() != null) {
			correcto = true;
		} else {
			// Mostrar mensajes de error de cada uno de los apartados
			if (textNombre.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, ingrese su nombre.", "Campo obligatorio vacío",
						JOptionPane.ERROR_MESSAGE);
			} else if (contrasena.length < 5 || contrasena.length > 8) {
				JOptionPane.showMessageDialog(this, "La contraseña debe tener entre 5 y 8 caracteres.",
						"Error en la contraseña", JOptionPane.ERROR_MESSAGE);
			}
			if (!dniValido) {

				JOptionPane.showMessageDialog(this, "El DNI ingresado no es válido.", "Error en el DNI",
						JOptionPane.ERROR_MESSAGE);
			} else if (!emailValido || email.isEmpty()) {
				JOptionPane.showMessageDialog(this, "El email ingresado no es válido.", "Error en el email",
						JOptionPane.ERROR_MESSAGE);
			} else if (confirmarContrasena.length == 0) {
				JOptionPane.showMessageDialog(this, "Por favor, confirme su contraseña.", "Campo obligatorio vacío",
						JOptionPane.ERROR_MESSAGE);
			} else if (!(checkBoxUsuario.isSelected() || checkBoxTrabajador.isSelected())) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione si es Usuario o Trabajador.",
						"Campo obligatorio no seleccionado", JOptionPane.ERROR_MESSAGE);
			} else if (dateFechaNacimiento.getDate() == null) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione su fecha de nacimiento.",
						"Campo obligatorio no seleccionado", JOptionPane.ERROR_MESSAGE);
			}
			correcto = false;
		}

		return correcto;
	}

	private void seleccionarUsuario() {
		if (checkBoxUsuario.isSelected()) {
			// Deshabilitar el campo de número de seguridad social
			lblNmeroSeguridadSocial.setVisible(false);
			textNumeroSS.setVisible(false);
			// Mostrar el label y el campo de fecha de registro
			lblFechaDeRegistro.setVisible(true);
			dateFRegistro.setVisible(true);
			checkBoxTrabajador.setSelected(false);
		}
	}

	private void seleccionarTrabajador() {
		if (checkBoxTrabajador.isSelected()) {
			// Deshabilitar el campo de número de seguridad social
			lblNmeroSeguridadSocial.setVisible(true);
			textNumeroSS.setVisible(true);
			// Mostrar el label y el campo de fecha de registro
			lblFechaDeRegistro.setVisible(false);
			dateFRegistro.setVisible(false);
			checkBoxUsuario.setSelected(false);
		}

	}

	private void registrarPersona() {
		Persona persona = null;
		if (checkBoxUsuario.isSelected()) {
			persona = new Usuario();
			cargarDatosComunes(persona);

			((Usuario) persona)
					.setFechaRegistro(dateFRegistro.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

		} else if (checkBoxTrabajador.isSelected()) {
			persona = new Trabajador();

			cargarDatosComunes(persona);
			((Trabajador) persona).setNnss(textNumeroSS.getText());
		}

		// Comprobamos a traves de la interfaz si la cuenta existe
		Controlador.registrarUsuario(persona);
	}

	private void cargarDatosComunes(Persona persona) {
		persona.setApellido(textApellido.getText());
		persona.setNombre(textNombre.getText());
		persona.setDni(textDni.getText());
		persona.setEmail(textEmail.getText());
		persona.setFechaNacimiento(
				dateFechaNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		persona.setDireccion(textDireccion.getText());
		persona.setContrasena(new String(passContrasena.getPassword()));
		persona.setGenero((Sexo) comboBoxGenero.getSelectedItem());

	}

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

	// Implementación de los métodos MouseListener (no son necesarios, pero necesito
	// implemenetarlos en la clase si quiero hacer lo de los eventos)

}
