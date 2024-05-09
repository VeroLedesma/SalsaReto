package vista;


import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VMoDatosArticulo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private final ButtonGroup buttonGroup = new ButtonGroup();

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Articulo;

public class VMoDatosArticulo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane = new JPanel();
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("/assets/logo.png"));
		logo.setBounds(222, 32, 245, 51);
		contentPane.add(logo);

		JLabel lblNewLabel = new JLabel("Codigo del Articulo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(94, 124, 125, 15);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(54, 149, 200, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(412, 149, 200, 31);
		contentPane.add(textField_1);

		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblColor.setBounds(412, 126, 125, 15);
		contentPane.add(lblColor);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(412, 242, 200, 31);
		contentPane.add(textField_2);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModelo.setBounds(412, 217, 125, 15);
		contentPane.add(lblModelo);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(412, 334, 200, 31);
		contentPane.add(textField_3);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(412, 309, 125, 15);
		contentPane.add(lblNombre);

		JLabel lblTemporada = new JLabel("Temporada");
		lblTemporada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTemporada.setBounds(118, 201, 73, 15);
		contentPane.add(lblTemporada);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Verano");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(54, 222, 103, 21);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnPrimavera = new JRadioButton("Primavera");
		buttonGroup.add(rdbtnPrimavera);
		rdbtnPrimavera.setBounds(54, 252, 103, 21);
		contentPane.add(rdbtnPrimavera);

		JRadioButton rdbtnOtoo = new JRadioButton("Otoño");
		buttonGroup.add(rdbtnOtoo);
		rdbtnOtoo.setBounds(54, 286, 103, 21);
		contentPane.add(rdbtnOtoo);

		JRadioButton rdbtnInvierno = new JRadioButton("Invierno");
		buttonGroup.add(rdbtnInvierno);
		rdbtnInvierno.setBounds(54, 317, 103, 21);
		contentPane.add(rdbtnInvierno);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(98, 399, 93, 31);
		contentPane.add(textField_4);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecio.setBounds(45, 406, 43, 15);
		contentPane.add(lblPrecio);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStock.setBounds(381, 408, 43, 15);
		contentPane.add(lblStock);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(434, 399, 93, 31);
		contentPane.add(textField_5);

		JButton btnNewButton = new JButton("Modificar los datos");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 204));
		btnNewButton.setBounds(247, 481, 202, 31);
		contentPane.add(btnNewButton);

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
		btnVolver.setBounds(37, 539, 97, 34);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModificar.setBounds(597, 526, 114, 34);
		btnModificar.addActionListener(this);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar Articulo");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(295, 539, 170, 34);
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
