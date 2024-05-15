package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Persona;
import modelo.Sexo;

/**
 * La clase VListarUsuarios representa una ventana de diálogo para listar y gestionar usuarios.
 * Hereda de JDialog e implementa ActionListener y ListSelectionListener para manejar eventos.
 */
public class VListarUsuarios extends JDialog implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane = new JPanel();
	private JTable tableDatosUsuario;
	private JScrollPane scrollPane;
	private JButton btnEliminar, btnVolver;
	private DefaultTableModel modelo;

	/**
	 * Constructor de la clase VListarUsuarios.
	 * 
	 * @param administracion referencia a la ventana de administración
	 * @param modal indica si el diálogo es modal
	 */
	public VListarUsuarios(VAdministracion administracion, boolean modal) {
		super(administracion);
		setModal(modal);
		setBounds(100, 100, 754, 642);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		// Etiqueta de título
		JLabel lblDatosUsuario = new JLabel("Lista de todos los usuarios");
		lblDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosUsuario.setBounds(235, 36, 242, 56);
		contentPane.add(lblDatosUsuario);

		// ScrollPane para la tabla
		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 131, 677, 252);
		contentPane.add(scrollPane);
		construirTabla();

		// Botón Volver
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(94, 539, 97, 34);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		// Botón Eliminar Usuario
		btnEliminar = new JButton("Eliminar Usuario");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(469, 539, 170, 34);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);
	}

	/**
	 * Construye la tabla con los datos de los usuarios.
	 */
	private void construirTabla() {
		String titulos[] = { "DNI", "NOMBRE", "APELLIDO", "FECHA NACIMIENTO", "CONTRASEÑA", "DIRECCION", "EMAIL",
				"GENERO" };
		String informacion[][];
		try {
			informacion = obtenerMatriz(); // Obtenemos la matriz de información de los usuarios
			modelo = new DefaultTableModel(informacion, titulos); // Establecemos el modelo por defecto de la tabla
			tableDatosUsuario = new JTable(modelo); // Agregamos el modelo a la tabla
			tableDatosUsuario.setDefaultEditor(Object.class, null); // Esto hace que la tabla deje de ser editable
			scrollPane.setViewportView(tableDatosUsuario);
			tableDatosUsuario.getSelectionModel().addListSelectionListener(this);
		} catch (SQLException excepcion) {
			excepcion.printStackTrace();
		}
	}

	/**
	 * Obtiene una matriz con la información de los usuarios.
	 * 
	 * @return una matriz de String con los datos de los usuarios
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */
	private String[][] obtenerMatriz() throws SQLException {
		List<Persona> personas = Controlador.listarUsuarios();
		String matrizInfo[][] = new String[personas.size()][8];
		for (int indice = 0; indice < personas.size(); indice++) {
			matrizInfo[indice][0] = personas.get(indice).getDni();
			matrizInfo[indice][1] = personas.get(indice).getNombre();
			matrizInfo[indice][2] = personas.get(indice).getApellido();
			matrizInfo[indice][3] = personas.get(indice).getFechaNacimiento().toString();
			matrizInfo[indice][4] = personas.get(indice).getContrasena();
			matrizInfo[indice][5] = personas.get(indice).getDireccion();
			matrizInfo[indice][6] = personas.get(indice).getEmail();
			matrizInfo[indice][7] = personas.get(indice).getGenero().toString();
		}
		return matrizInfo;
	}

	/**
	 * Maneja los eventos de los botones en la ventana de diálogo.
	 * 
	 * @param evento el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(btnVolver)) {
			volver();
		}

		if (evento.getSource().equals(btnEliminar)) {
			eliminarUsuario();
		}
	}

	/**
	 * Maneja los eventos de selección en la tabla.
	 * 
	 * @param evento el evento de selección de la tabla
	 */
	@Override
	public void valueChanged(ListSelectionEvent evento) {
		if (!evento.getValueIsAdjusting()) {
			modificarActualizarUsuario();
		}
	}

	/**
	 * Elimina el usuario seleccionado (sin implementación).
	 */
	private void eliminarUsuario() {
		// Implementación para eliminar un usuario pendiente
	}

	/**
	 * Modifica y actualiza la información del usuario seleccionado.
	 */
	private void modificarActualizarUsuario() {
		int fila = tableDatosUsuario.getSelectedRow(); // Se obtiene el número de la fila seleccionada
		if (fila != -1) { // Si la fila seleccionada es diferente de -1, se obtiene la información de la fila seleccionada
			Persona per = new Persona();
			per.setDni(tableDatosUsuario.getValueAt(fila, 0).toString());
			per.setNombre(tableDatosUsuario.getValueAt(fila, 1).toString());
			per.setApellido(tableDatosUsuario.getValueAt(fila, 2).toString());
			per.setFechaNacimiento(LocalDate.parse(tableDatosUsuario.getValueAt(fila, 3).toString()));
			per.setContrasena(tableDatosUsuario.getValueAt(fila, 4).toString());
			per.setDireccion(tableDatosUsuario.getValueAt(fila, 5).toString());
			per.setEmail(tableDatosUsuario.getValueAt(fila, 6).toString());
			per.setGenero(Sexo.valueOf(tableDatosUsuario.getValueAt(fila, 7).toString()));

			VRegister modificacion = new VRegister(null, true, per, fila, "modificar");
			this.dispose();
			modificacion.setLocationRelativeTo(this);
			modificacion.setVisible(true);
		}
	}

	/**
	 * Vuelve a la ventana de administración.
	 */
	private void volver() {
		VAdministracion admin = new VAdministracion(null, true);
		this.dispose();
		admin.setLocationRelativeTo(this);
		admin.setVisible(true);
	}


}


