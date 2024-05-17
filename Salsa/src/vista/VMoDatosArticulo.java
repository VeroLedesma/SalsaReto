package vista;

import java.awt.BorderLayout;
import java.awt.Font;
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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Controlador;
import modelo.Articulo;
import modelo.Temporada;

/**
 * En esta ventana se permite realizar las modificaciones de datos que requiera
 * el Trabajador. Una vez realizados, se cambiarán los datos en la base de
 * datos.
 * 
 * @author Luis
 */
public class VMoDatosArticulo extends JDialog implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane = new JPanel();
	private JTable tableDatosArticulo;
	private JScrollPane scrollPane;
	private JButton btnEliminar, btnVolver;

	/**
	 * Constructor de la clase VMoDatosArticulo
	 * 
	 * @param modal          Hace que no se pueda alternar entre ventanas
	 * @param administracion Hace referencia a la ventana de Administracion
	 * 
	 */
	public VMoDatosArticulo(VAdministracion administracion, boolean modal) {
		super(administracion);
		setModal(modal);

		setBounds(100, 100, 715, 573);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("/assets/logo.png"));
		logo.setBounds(222, 32, 245, 51);
		contentPane.add(logo);

		setBounds(100, 100, 754, 642);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		JLabel lblDatosArticulo = new JLabel("Lista de todos los articulos");
		lblDatosArticulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosArticulo.setBounds(235, 36, 242, 56);
		contentPane.add(lblDatosArticulo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 131, 677, 252);
		contentPane.add(scrollPane);

		construirTabla();
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(55, 475, 97, 34);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		btnEliminar = new JButton("Eliminar Articulo");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(519, 475, 170, 34);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

	}

	// Tabla donde se muestran los datos del articulo y si seleccionas una fila te
	// dirige a una ventana para poder modificar algunos datos
	private void construirTabla() {
		String titulos[] = { "COD", "COLOR", "TEMPORADA", "PRECIO", "DESCUENTO", "NOMBRE TIPO ARTICULO" };
		String informacion[][];
		try {
			informacion = obtenerMatriz();
			tableDatosArticulo = new JTable(informacion, titulos);
			tableDatosArticulo.setDefaultEditor(Object.class, null);// esto hace que la tabla no pueda editarse
			scrollPane.setViewportView(tableDatosArticulo);
			tableDatosArticulo.getSelectionModel().addListSelectionListener(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Se imprimen los datos de los articulos en la tabla
	private String[][] obtenerMatriz() throws SQLException {

		List<Articulo> articulos = Controlador.listarArticulos(); // Asumiendo que Controlador tiene un método para
																	// obtener la lista de Articulos

		String matrizInfo[][] = new String[articulos.size()][6];
		for (int indice = 0; indice < articulos.size(); indice++) {
			matrizInfo[indice][0] = articulos.get(indice).getCodArticulo() + "";
			matrizInfo[indice][1] = articulos.get(indice).getColor() + "";
			matrizInfo[indice][2] = articulos.get(indice).getTemporada() + "";
			matrizInfo[indice][3] = articulos.get(indice).getPrecio() + "";
			matrizInfo[indice][4] = articulos.get(indice).getPorcentajeDecuento() + "";
			matrizInfo[indice][5] = articulos.get(indice).getNombreTipo() + "";
		}
		return matrizInfo;
	}

	/**
	 * Se crea un metodo que permite volver a la ventana anterior
	 * 
	 * @param e Permite realizar una accion de evento
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnVolver)) {
			volver();
		}
	}

	// Se vuelve a la ventana anterior, que es la de administración
	private void volver() {
		VAdministracion admin = new VAdministracion(null, true);
		this.dispose();
		admin.setLocationRelativeTo(this);
		admin.setVisible(true);

	}

	@Override
	public void valueChanged(ListSelectionEvent evento) {
//aqui añadiremos  la accion que se hará para eliminar o modificar el usuario
		int respuesta = 0;
		if (!evento.getValueIsAdjusting()) {

			respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres modificar un articulo?", "Confirmación",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == 0) {
				modificarArticulo();
			} else {
				respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres eliminar un articulo?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == 0) {
					eliminarArticulo();
				}
			}
		}

	}

	private boolean eliminarArticulo() {
		int fila = tableDatosArticulo.getSelectedRow();
		boolean eliminar = Controlador
				.eliminarArticulo(Integer.parseInt(tableDatosArticulo.getValueAt(fila, 0).toString()));
		volver();
		return eliminar;

	}

	private void modificarArticulo() {
		int fila = tableDatosArticulo.getSelectedRow(); // Se obtiene el número de la fila seleccionada
		if (fila != -1) { // Si la fila seleccionada es diferente de -1, se obtiene la información de la
							// fila seleccionada
			Articulo articulo = new Articulo();
			articulo.setCodArticulo(Integer.parseInt(tableDatosArticulo.getValueAt(fila, 0).toString()));
			articulo.setColor(tableDatosArticulo.getValueAt(fila, 1).toString());
			articulo.setTemporada(Temporada.valueOf(tableDatosArticulo.getValueAt(fila, 2).toString()));
			articulo.setPrecio(Float.parseFloat(tableDatosArticulo.getValueAt(fila, 3).toString()));
			articulo.setPorcentajeDecuento(Float.parseFloat(tableDatosArticulo.getValueAt(fila, 4).toString()));
			articulo.setNombreTipo(tableDatosArticulo.getValueAt(fila, 5).toString());
			VInsertDatosArticulo modificacion = new VInsertDatosArticulo(null, true, articulo, fila, 1, "modificar");
			this.dispose();
			modificacion.setLocationRelativeTo(this);
			modificacion.setVisible(true);
		}
	}
}