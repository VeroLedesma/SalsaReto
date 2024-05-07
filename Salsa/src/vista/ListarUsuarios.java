package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Persona;

public class ListarUsuarios extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane = new JPanel();
	private JTable tableDatosUsuario;
	private DefaultTableModel model;
	private JComboBox<String> cbUsuarios;
	private List<Persona> personas = new ArrayList<>();
	private JButton btnBuscar, btnEliminar, btnVolver, btnModificar;
	private Persona per = new Persona();

	/**
	 * Create the frame.
	 * 
	 * @param administracion
	 * 
	 * @param oscuro
	 */
	public ListarUsuarios(Administracion administracion, boolean modal) {
		super(administracion);
		setModal(modal);
		setBounds(100, 100, 711, 689);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		JLabel lblDatosUsuario = new JLabel("Lista de todos los usuarios");
		lblDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosUsuario.setBounds(207, 247, 242, 56);
		contentPane.add(lblDatosUsuario);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 297, 677, 246);
		contentPane.add(scrollPane);
		tableDatosUsuario = new JTable();
		model = new DefaultTableModel();
		tableDatosUsuario.setModel(model);
		model.addColumn("DNI");
		model.addColumn("NOMBRE");
		model.addColumn("APELLIDO");
		model.addColumn("FECHA DE NACIMIENTO");
		model.addColumn("DIRECCION");
		model.addColumn("EMAIL");
		model.addColumn("GENERO");
		scrollPane.setViewportView(tableDatosUsuario);

		cbUsuarios = new JComboBox<String>();
		cbUsuarios.setBounds(208, 50, 319, 41);
		contentPane.add(cbUsuarios);
		cargarUsuarios();
		JLabel lblSeleccionar = new JLabel("Seleciona un usuario");
		lblSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeleccionar.setBounds(28, 50, 170, 41);
		contentPane.add(lblSeleccionar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBuscar.setBounds(270, 137, 132, 34);
		btnBuscar.addActionListener(this);
		contentPane.add(btnBuscar);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(45, 588, 85, 34);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModificar.setBounds(516, 588, 114, 34);
		btnModificar.addActionListener(this);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar Usuario");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(270, 588, 170, 34);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

	}

	public void llenarTabla(Persona per) {
		Object[] fila = new Object[7];
		fila[0] = per.getDni();
		fila[1] = per.getNombre();
		fila[2] = per.getApellido();
		fila[3] = per.getFechaNacimiento();
		fila[4] = per.getDireccion();
		fila[5] = per.getEmail();
		fila[6] = per.getGenero();
		model.addRow(fila);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnBuscar)) {
			buscarUsuarioSeleccionado();
		}
		if (e.getSource().equals(btnVolver)) {
			volver();
		}
	}

	private void volver() {
		Administracion admin = new Administracion(null, true);
		admin.setVisible(true);
		this.dispose();
	}

	private void buscarUsuarioSeleccionado() {
		if (cbUsuarios.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Tienes que seleccionar un usuario");
		}
		Persona per = new Persona();
		String cadena = (String) cbUsuarios.getSelectedItem();
		int pos = cadena.indexOf(" ");
		String dni = cadena.substring(0, pos);
		for (Persona persona : personas) {
			if (persona.getDni().equalsIgnoreCase(dni)) {
				per = persona;
			}
		}
		try {
			personas = Controlador.listarUsuarios();
			llenarTabla(per);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void cargarUsuarios() {

		try {
			personas = Controlador.listarUsuarios();
			for (Persona per : personas) {
				cbUsuarios.addItem(per.getDni() + " " + per.getNombre());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cbUsuarios.setSelectedIndex(-1);
	}

}
