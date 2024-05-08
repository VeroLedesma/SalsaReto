package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Persona;

public class ListarUsuarios extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane = new JPanel();
	private JTable tableDatosUsuario;
	private JScrollPane scrollPane;
	private JButton btnEliminar, btnVolver, btnModificar;

	/**
	 * Create the frame.
	 * 
	 * @param administracion
	 * @param per2
	 * 
	 */
	public ListarUsuarios(Administracion administracion, boolean modal, Persona per2) {
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
		btnVolver.setBounds(37, 539, 97, 34);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModificar.setBounds(596, 539, 114, 34);
		btnModificar.addActionListener(this);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar Usuario");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(295, 539, 170, 34);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

	}

	private void construirTabla() {
		String titulos[] = { "DNI", "NOMBRE", "APELLIDO", "FECHA NACIMIENTO", "DIRECCION", "EMAIL", "GENERO" };
		String informacion[][];
		try {
			informacion = obtenerMatriz();
			tableDatosUsuario = new JTable(informacion, titulos);
			scrollPane.setViewportView(tableDatosUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private String[][] obtenerMatriz() throws SQLException {

		List<Persona> personas = Controlador.listarUsuarios();
		String matrizInfo[][] = new String[personas.size()][7];
		for (int i = 0; i < personas.size(); i++) {
			matrizInfo[i][0] = personas.get(i).getDni() + "";
			matrizInfo[i][1] = personas.get(i).getNombre() + "";
			matrizInfo[i][2] = personas.get(i).getApellido() + "";
			matrizInfo[i][3] = personas.get(i).getFechaNacimiento() + "";
			matrizInfo[i][4] = personas.get(i).getDireccion() + "";
			matrizInfo[i][5] = personas.get(i).getEmail() + "";
			matrizInfo[i][6] = personas.get(i).getGenero() + "";
		}
		return matrizInfo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnVolver)) {
			volver();
		}
	}

	private void volver() {
		this.dispose();
		Administracion admin = new Administracion(null, true);
		admin.setVisible(true);
	}

}
