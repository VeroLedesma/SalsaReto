package vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Articulo;

public class VMoDatosArticulo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JPanel contentPane=new JPanel();
	private JTable tableDatosArticulo;
	private JScrollPane scrollPane;
	private JButton btnEliminar, btnVolver, btnModificar;


	/**
	 * Create the frame.

	 * @param modal
	 * @param administracion

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
		btnVolver.setBounds(58, 458, 97, 34);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModificar.setBounds(597, 458, 114, 34);
		btnModificar.addActionListener(this);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar Articulo");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(273, 458, 170, 34);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

	}



	private void construirTabla() {
		String titulos[] = { "COD", "COLOR", "MODELO","TEMPORADA", "PRECIO", "DESCUENTO" };
		String informacion[][];
		try {
			informacion = obtenerMatriz();
			tableDatosArticulo = new JTable(informacion, titulos);
			scrollPane.setViewportView(tableDatosArticulo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private String[][] obtenerMatriz() throws SQLException {

	    List<Articulo> articulos = Controlador.listarArticulos(); // Asumiendo que Controlador tiene un método para obtener la lista de Articulos

	    String matrizInfo[][] = new String[articulos.size()][6];
	    for (int i = 0; i < articulos.size(); i++) {
	        matrizInfo[i][0] = articulos.get(i).getCodArticulo()+"";
	        matrizInfo[i][1] = articulos.get(i).getColor()+"";
	        matrizInfo[i][2] = articulos.get(i).toString()+"";
	        matrizInfo[i][3] = articulos.get(i).getTemporada()+"";
	        matrizInfo[i][4] = articulos.get(i).getPrecio()+"";
	        matrizInfo[i][5] = articulos.get(i).getPorcentajeDecuento()+"";

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
		VAdministracion admin = new VAdministracion(null, true);
		admin.setVisible(true);
		this.dispose();

	}

}
