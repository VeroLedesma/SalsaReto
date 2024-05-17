package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Articulo;
import modelo.Usuario;

/**
 * Esta ventana permite ver una lista de deseos que el usuario a agregado a la
 * misma.
 * 
 * @author Luis
 */
public class VMiLista extends JDialog implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel logo;
	private JButton btnVolver;
	private VMain main;
	private String email;
	private JTable tableDatosMisArticulos;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	// Comprobar si anda en modo diurno o nocturno
	// private boolean oscuro;

	/**
	 * Se visualiza una "Lista de deseos"
	 * 
	 * @param vMain Hace referencia a la ventana Hamburger, que es el menu
	 * @param b     Actua como valor para el modal, es decir que no permite que se
	 *              cambie entre ventanas
	 * @param email
	 */
	public VMiLista(VMain vMain, boolean b, String email, int codArt) {
		super(vMain);
		this.email = email;
		setModal(b);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 733, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		logo = new JLabel("");
		logo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		logo.setBounds(217, 28, 245, 51);
		logo.setBackground(Color.WHITE);
		contentPane.add(logo);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 131, 677, 252);
		contentPane.add(scrollPane);
		construirTabla();

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setBounds(10, 10, 85, 21);
		contentPane.add(btnVolver);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 125, 709, 2);
		contentPane.add(separator);

		aniadiAmiLista(codArt);
	}

	/**
	 * Muestra la ventana del main
	 */
	private void construirTabla() {
		String titulos[] = { "COLOR", "TEMPORADA", "PRECIO", "DESCUENTO", "NOMBRE TIPO" };
		String informacion[][];
		try {

			informacion = obtenerMatriz(); // Obtenemos la matriz de información de los usuarios
			modelo = new DefaultTableModel(informacion, titulos); // Establecemos el modelo por defecto de la tabla
			tableDatosMisArticulos = new JTable(modelo); // Agregamos el modelo a la tabla
			tableDatosMisArticulos.setDefaultEditor(Object.class, null); // Esto hace que la tabla deje de ser editable
			scrollPane.setViewportView(tableDatosMisArticulos);
			tableDatosMisArticulos.getSelectionModel().addListSelectionListener(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String[][] obtenerMatriz() throws SQLException {
		List<Articulo> articulos = Controlador.listarArticulos(); // Asumiendo que Controlador tiene un método para
		// obtener la lista de Articulos

		String matrizInfo[][] = new String[articulos.size()][6];
		for (int indice = 0; indice < articulos.size(); indice++) {
			matrizInfo[indice][0] = articulos.get(indice).getColor() + "";
			matrizInfo[indice][1] = articulos.get(indice).getTemporada() + "";
			matrizInfo[indice][2] = articulos.get(indice).getPrecio() + "";
			matrizInfo[indice][3] = articulos.get(indice).getPorcentajeDecuento() + "";
			matrizInfo[indice][4] = articulos.get(indice).getNombreTipo() + "";
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
		VMain ven = new VMain(null, true, email);
		this.dispose();
		ven.setLocationRelativeTo(this);
		ven.setVisible(true);
	}

	private void aniadiAmiLista(int codArt) {
		Usuario usuario = Controlador.obtenerUsuario(email);
		Articulo articulo = Controlador.obtenerArticulo(codArt);
		Controlador.aniadirCompra(usuario.getDni(), articulo.getCodArticulo());

	}

	@Override
	public void valueChanged(ListSelectionEvent evento) {
		int respuesta = 0;

		if (!evento.getValueIsAdjusting()) {
			respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres eliminar un articulo?", "Confirmación",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == 0) {
				eliminarArticulo();
			}
		}
	}

	private boolean eliminarArticulo() {
		int fila = tableDatosMisArticulos.getSelectedRow();
		boolean eliminar = Controlador
				.eliminarArticulo(Integer.parseInt(tableDatosMisArticulos.getValueAt(fila, 0).toString()));
		volver();
		return eliminar;
	}
}
