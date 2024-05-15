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

/**
 * Esta ventana permite registrar a un usuario en la base de datos
 * solo en caso de que todos los campos se rellenen correctamente
 * cumpliendo con los valores que se piden en cada campo.
 * @author Luis
 */
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
	private Persona per = new Persona();
	private JButton btnVolver;

	/**
	 * Constructor de la ventana VRegister
	 * 
	 * @param padre Hace referencia a la ventana padre que es la de Login
	 * @param modal Permite que no se pueda cambiar entre una ventana y otra
	 * @param persona2 Hace referencia a la clase persona
	 * @param fila Hace referencia a la fila que se seleccione
	 * @param btnNombre Hace referencia al botón de modificación de datos
	 */
	public VRegister(VLogin padre, boolean modal, Persona persona2, int fila, String btnNombre) {
		super(padre);
		this.per = persona2;

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

		btnRegistro = new JButton();
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegistro.setForeground(new Color(0, 64, 128));
		btnRegistro.addActionListener(this);
		btnRegistro.setForeground(Color.BLACK);
		btnRegistro.setBackground(new Color(255, 255, 255));
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
		lblPregunta.setBounds(235, 623, 123, 16);
		getContentPane().add(lblPregunta);

		lblInicioSesion = new JLabel("Inicia Sesion");
		lblInicioSesion.setBounds(415, 623, 98, 14);
		lblInicioSesion.addMouseListener(this);
		lblInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInicioSesion.setForeground(new Color(0, 51, 255));
		getContentPane().add(lblInicioSesion);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(561, 10, 144, 29);
		btnVolver.addActionListener(this);

		lblPregunta.setBounds(265, 626, 114, 13);
		getContentPane().add(lblPregunta);

		// Botones de eventos
		checkBoxUsuario.addActionListener(this);
		checkBoxTrabajador.addActionListener(this);

		// aqui los campos que se rellenen en los textfield, si el boton es modificar
		if (btnNombre.equalsIgnoreCase("Modificar")) {
			getContentPane().add(btnVolver);
			textDni.setText(persona2.getDni());
			textNombre.setText(persona2.getNombre());
			textApellido.setText(persona2.getApellido());
			dateFechaNacimiento.setDate(java.sql.Date.valueOf(persona2.getFechaNacimiento()));
			passContrasena.setText(persona2.getContrasena());
			textDireccion.setText(persona2.getDireccion());
			textEmail.setText(persona2.getEmail());
			comboBoxGenero.setSelectedItem(persona2.getGenero());
			btnRegistro.setText("Modificar");
		} else {// si el boton es registro se abre la ventana del login
			btnRegistro.setText("Enviar datos");
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent evento) {
					padre.setVisible(true);
					btnVolver.setVisible(false);// hacemos el boton de volver invisible en la ventana de registro
				}
			});
		}

	}

	//Inicia un evento cuando clicas el botón
	@Override
	public void mouseClicked(MouseEvent e) {
		inicioSesion();
	}

	//Te envía a la ventana de inicio de sesión (Login)
	protected void inicioSesion() {
		VLogin log = new VLogin(per);
		log.setLocationRelativeTo(this);
		setVisible(false);
		log.setVisible(true);

	}

	/**
	 * Realiza el evento de enviar los datos a la base de datos.
	 * Si no se rellenan los campos obligatorios, aparece un mensaje de error.
	 * 
	 * @param evento Inicia un evento de acción.
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource().equals(btnRegistro)) {
			System.out.println("entrando en el registro");
			if (btnRegistro.getText().equalsIgnoreCase("Enviar datos")) {
				if (camposObligatoriosCompletos() == true) {
					registrarPersona();
				}
			} else if (btnRegistro.getText().equalsIgnoreCase("Modificar")) {
				System.out.println("antes de modificar");
				modificar();
			}
		}
		if (evento.getSource().equals(checkBoxUsuario)) {
			seleccionarUsuario();
		}
		if (evento.getSource().equals(checkBoxTrabajador)) {
			seleccionarTrabajador();
		}
		if (evento.getSource().equals(btnRegistro)) {
			if (btnRegistro.isShowing() && camposObligatoriosCompletos() == false) {
				JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.",
						"Campos obligatorios incompletos", JOptionPane.ERROR_MESSAGE);
			} else if (btnRegistro.isShowing() && camposObligatoriosCompletos() == true) {
				this.dispose();
			}
		}

		if (evento.getSource().equals(btnVolver)) {
			volver();
		}
	}

	// Permite modificar los datos de los usuarios o los trabajadores
	private void modificar() {
		boolean modificado;
		System.out.println(per.getDni());
		Persona per = cargarDatosComunes();
		modificado = Controlador.modificarUsuario(per);

		System.out.println(modificado);
		if (modificado == true) {
			JOptionPane.showMessageDialog(null, "se ha modificado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "No se ha modificado nada", null, JOptionPane.ERROR_MESSAGE);
		}
	}

	//Te redirige a la ventana de ListarUsuarios
	private void volver() {


		VListarUsuarios ven = new VListarUsuarios(null, true);
		this.dispose();
		ven.setLocationRelativeTo(this);
		ven.setVisible(true);

	}

	//Comprueba que los campos obligatorios se han rellenado
	//y realiza la comprobación de que el DNI y el email estén correctamente
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

	//Permite seleccionar el usuario con un checkbox
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
	//Permite seleccionar el usuario de trabajador con un checkbox
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

	//Permite registrar a una persona en la base de datos
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
			int respuesta = 0;
			JOptionPane.showConfirmDialog(null, "¿Eres un encargado?", "Confirmación", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				((Trabajador) persona).setEncargado(true);

			} else if (respuesta == JOptionPane.NO_OPTION) {
				((Trabajador) persona).setEncargado(false);
			}
		}
		// Comprobamos a traves de la interfaz si la cuenta existe
		Controlador.registrarUsuario(persona);
	}

	//Esta parte realiza toda la lógica con la conexión de la base de datos 
	//y si existe ese usuario.
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

	private Persona cargarDatosComunes() {
		Persona per = new Persona();
		per.setApellido(textApellido.getText());
		per.setNombre(textNombre.getText());
		per.setDni(textDni.getText());
		per.setEmail(textEmail.getText());
		per.setFechaNacimiento(dateFechaNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		per.setDireccion(textDireccion.getText());
		per.setContrasena(new String(passContrasena.getPassword()));
		per.setGenero((Sexo) comboBoxGenero.getSelectedItem());
		return per;

	}
	// Implementación de los métodos MouseListener (no son necesarios, pero necesito
	// implemenetarlos en la clase si se quiere hacer el eventos del mouse)

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