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

public class VListarUsuarios extends JDialog implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane = new JPanel();
	private JTable tableDatosUsuario;
	private JScrollPane scrollPane;
	private JButton btnEliminar, btnVolver;
	private DefaultTableModel modelo;

	/**
	 * Create the frame.
	 * 
	 * @param administracion
	 * @param per2
	 * 
	 */
	public VListarUsuarios(VAdministracion administracion, boolean modal) {
		super(administracion);
		setModal(modal);
		setBounds(100, 100, 754, 642);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		JLabel lblDatosUsuario = new JLabel("Lista de todos los usuarios");
		lblDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosUsuario.setBounds(235, 36, 242, 56);
		contentPane.add(lblDatosUsuario);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 131, 677, 252);
		contentPane.add(scrollPane);
		construirTabla();

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(94, 539, 97, 34);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		btnEliminar = new JButton("Eliminar Usuario");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(469, 539, 170, 34);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

	}

	private void construirTabla() {
		String titulos[] = { "DNI", "NOMBRE", "APELLIDO", "FECHA NACIMIENTO", "CONTRASEÑA", "DIRECCION", "EMAIL",
				"GENERO" };
		String informacion[][];
		try {
			informacion = obtenerMatriz();// obtenenmos la matriz de informacion de los usuarios
			modelo = new DefaultTableModel(informacion, titulos);// establecemos el modelo por defecto de la tabla
			tableDatosUsuario = new JTable(modelo);// agregamos el modelo a la tabla
			tableDatosUsuario.setDefaultEditor(Object.class, null);// esto hace que la tabla deje de ser editable
			scrollPane.setViewportView(tableDatosUsuario);
			tableDatosUsuario.getSelectionModel().addListSelectionListener(this);
		} catch (SQLException excepsion) {

			excepsion.printStackTrace();
		}
	}

	private String[][] obtenerMatriz() throws SQLException {

		List<Persona> personas = Controlador.listarUsuarios();
		String matrizInfo[][] = new String[personas.size()][8];
		for (int indice = 0; indice < personas.size(); indice++) {
			matrizInfo[indice][0] = personas.get(indice).getDni() + "";
			matrizInfo[indice][1] = personas.get(indice).getNombre() + "";
			matrizInfo[indice][2] = personas.get(indice).getApellido() + "";
			matrizInfo[indice][3] = personas.get(indice).getFechaNacimiento() + "";
			matrizInfo[indice][4] = personas.get(indice).getContrasena() + "";
			matrizInfo[indice][5] = personas.get(indice).getDireccion() + "";
			matrizInfo[indice][6] = personas.get(indice).getEmail() + "";
			matrizInfo[indice][7] = personas.get(indice).getGenero() + "";
		}
		return matrizInfo;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource().equals(btnVolver)) {
			volver();
		}

		if (evento.getSource().equals(btnEliminar)) {
			eliminarUsuario();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent evento) {
		if (!evento.getValueIsAdjusting()) {
			modificarActualizarUsuario();
		}
	}

	private void eliminarUsuario() {
//intentar hacer una funcion en mysql para borrar un usuario, queda pemndiente el hecho de ligar los usuarios 
		// creados en mysql con el inicio de sesion
	}

	private void modificarActualizarUsuario() {
		int fila = tableDatosUsuario.getSelectedRow();// se obtiene el numero de la fila seleccionada
		if (fila != -1) {// si la fila seleccionada es diferente de -1 se obtendra la informacion de la
							// fila que se ha seleccionado
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

	private void volver() {

		VAdministracion admin = new VAdministracion(null, true);
		this.dispose();
		admin.setLocationRelativeTo(this);
		admin.setVisible(true);
	}

}