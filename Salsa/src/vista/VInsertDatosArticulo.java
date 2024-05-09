package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import excepciones.CreateException;
import modelo.Articulo;
import modelo.Temporada;

public class VInsertDatosArticulo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfModelo, tfNombre, tfPrecio, tfStock, tfColor, tfCodArticulo, tfPorcentaje;
	private JComboBox<Temporada> comboBoxTemporada;
	private JButton btnSubirDatos, btnVolver, btnmodificar;

	// Lógica para la conexión
	// private static Controlador cont = new Controlador();

	/**
	 * Create the dialog.
	 * 
	 * @param modal
	 * @param administracion
	 * @param selectedId
	 */
	public VInsertDatosArticulo(VAdministracion administracion, boolean modal) {
		super(administracion);
		setModal(modal);

		setBounds(100, 100, 859, 704);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(262, 35, 292, 69);
		logo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		contentPanel.add(logo);

		JLabel lblCodArticulo = new JLabel("Codigo del articulo");
		lblCodArticulo.setBounds(103, 118, 155, 35);
		lblCodArticulo.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblCodArticulo);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(537, 118, 51, 35);
		lblColor.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblColor);

		tfCodArticulo = new JTextField();
		tfCodArticulo.setBounds(103, 163, 155, 35);
		contentPanel.add(tfCodArticulo);
		tfCodArticulo.setColumns(10);

		tfColor = new JTextField();
		tfColor.setBounds(537, 163, 225, 35);
		tfColor.setColumns(10);
		contentPanel.add(tfColor);

		JLabel lblTemporada = new JLabel("Temporada");
		lblTemporada.setBounds(103, 208, 84, 35);
		lblTemporada.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblTemporada);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(536, 208, 84, 35);
		lblModelo.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblModelo);

		tfModelo = new JTextField();
		tfModelo.setBounds(537, 253, 225, 35);
		tfModelo.setColumns(10);
		contentPanel.add(tfModelo);

		tfNombre = new JTextField();
		tfNombre.setBounds(537, 352, 225, 35);
		tfNombre.setColumns(10);
		contentPanel.add(tfNombre);

		JLabel lblNombreTipo = new JLabel("Nombre del tipo de prenda");
		lblNombreTipo.setBounds(537, 316, 225, 35);
		lblNombreTipo.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblNombreTipo);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(107, 316, 56, 35);
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblPrecio);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(536, 409, 84, 35);
		lblStock.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblStock);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(103, 366, 225, 35);
		tfPrecio.setColumns(10);
		contentPanel.add(tfPrecio);

		tfStock = new JTextField();
		tfStock.setBounds(537, 454, 142, 35);
		tfStock.setColumns(10);
		contentPanel.add(tfStock);

		btnSubirDatos = new JButton("Subir Datos");
		btnSubirDatos.addActionListener(this);
		btnSubirDatos.setBounds(103, 562, 203, 60);
		btnSubirDatos.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPanel.add(btnSubirDatos);

		JLabel lblPorcentaje = new JLabel("Porcentaje de descuento");
		lblPorcentaje.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPorcentaje.setBounds(103, 409, 196, 35);
		contentPanel.add(lblPorcentaje);

		tfPorcentaje = new JTextField();
		tfPorcentaje.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tfPorcentaje.setColumns(10);
		tfPorcentaje.setBounds(103, 454, 225, 35);
		contentPanel.add(tfPorcentaje);

		comboBoxTemporada = new JComboBox<>();
		comboBoxTemporada.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBoxTemporada.setModel(new DefaultComboBoxModel<>(Temporada.values()));
		comboBoxTemporada.setToolTipText("");
		comboBoxTemporada.setEditable(true);
		comboBoxTemporada.setBounds(103, 256, 196, 28);
		contentPanel.add(comboBoxTemporada);

		btnmodificar = new JButton("Modificar");
		btnmodificar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnmodificar.setBounds(607, 563, 155, 60);
		contentPanel.add(btnmodificar);

		btnVolver = new JButton("volver");
		btnVolver.addActionListener(this);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(722, 10, 113, 35);
		contentPanel.add(btnVolver);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSubirDatos)) {
			subirDatos();
		}
		if (e.getSource().equals(btnVolver)) {
			volver();

		}
		if (e.getSource().equals(btnmodificar)) {
			modificarDatos();
		}
	}

	private void modificarDatos() {

	}

	private void volver() {
		VAdministracion ven = new VAdministracion(null, true);
		ven.setVisible(true);
		this.dispose();
	}

	private void subirDatos() {
		Articulo art = new Articulo();
		try {
			cargarDatosArticulo(art);
		} catch (CreateException e) {
			e.printStackTrace();
		}
	}

	public void cargarDatosArticulo(Articulo articulo) throws CreateException {

		// guardamos el texto de tf en una variable
		String cod = tfCodArticulo.getText();
		// luego parseamos dicha variable
		int codigoArt = Integer.parseInt(cod);
		// y la ponemos para cargar los datos
		articulo.setCodArticulo(codigoArt);
		articulo.setColor(tfColor.getText());
		String porcentaje = tfPorcentaje.getText();
		float porcent = Float.parseFloat(porcentaje);
		articulo.setPorcentajeDecuento(porcent);
		String precio = tfPrecio.getText();
		Float precio2 = Float.parseFloat(precio);
		articulo.setPrecio(precio2);
		articulo.setModelo(tfModelo.getText());
		articulo.setTemporada((Temporada) comboBoxTemporada.getSelectedItem());
		// para la creacion de un a excepcion pero hay que mirarlo, no se si esta bien
		Controlador.altaArticulo(articulo);
	}
}
