package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import excepciones.CreateException;
import modelo.Articulo;
import modelo.Temporada;
import modelo.Tipo;

/**
 * La clase VInsertDatosArticulo representa un diálogo para insertar datos de
 * artículos. Hereda de JDialog e implementa ActionListener para manejar eventos
 * de botones.
 */
public class VInsertDatosArticulo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfPrecio, tfColor, tfPorcentaje;
	private JComboBox<Temporada> comboBoxTemporada;
	private JComboBox<String> cbTipoPrenda;
	private Map<Integer, Tipo> tipoPrenda = new HashMap<>();
	private JButton btnSubirDatos, btnVolver;
	private int pantallaDiferente;
	private Articulo articulo = new Articulo();

	/**
	 * Constructor de la clase VInsertDatosArticulo.
	 * 
	 * @param administracion referencia a la ventana de administración
	 * @param modal          indica si el diálogo es modal
	 * @param fila
	 * @param articulo
	 */
	public VInsertDatosArticulo(VAdministracion administracion, boolean modal, Articulo articulo, int fila,
			int pantallaDiferente, String modificar) {
		super(administracion);
		this.articulo = articulo;

		setModal(modal);
		setBounds(100, 100, 862, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Logo
		JLabel logo = new JLabel("");
		logo.setBounds(262, 35, 292, 69);
		logo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		contentPanel.add(logo);

		// Etiqueta y campo de texto para el color
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(526, 118, 51, 35);
		lblColor.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblColor);

		tfColor = new JTextField();
		tfColor.setBounds(526, 163, 225, 35);
		tfColor.setColumns(10);
		contentPanel.add(tfColor);

		// Etiqueta para la temporada
		JLabel lblTemporada = new JLabel("Temporada");
		lblTemporada.setBounds(107, 118, 84, 35);
		lblTemporada.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblTemporada);

		// Etiqueta para el nombre del tipo de prenda
		JLabel lblNombreTipo = new JLabel("Nombre del tipo de prenda");
		lblNombreTipo.setBounds(526, 274, 225, 35);
		lblNombreTipo.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblNombreTipo);

		// Etiqueta y campo de texto para el precio
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(107, 208, 56, 35);
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(107, 253, 225, 35);
		tfPrecio.setColumns(10);
		contentPanel.add(tfPrecio);

		// Botón subir datos
		btnSubirDatos = new JButton();
		btnSubirDatos.addActionListener(this);
		btnSubirDatos.setBounds(326, 488, 203, 60);
		btnSubirDatos.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPanel.add(btnSubirDatos);

		// Etiqueta y campo de texto para el porcentaje de descuento
		JLabel lblPorcentaje = new JLabel("Porcentaje de descuento");
		lblPorcentaje.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPorcentaje.setBounds(107, 316, 196, 35);
		contentPanel.add(lblPorcentaje);

		tfPorcentaje = new JTextField();
		tfPorcentaje.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tfPorcentaje.setColumns(10);
		tfPorcentaje.setBounds(107, 361, 225, 35);
		contentPanel.add(tfPorcentaje);

		// ComboBox para la temporada
		comboBoxTemporada = new JComboBox<>();
		comboBoxTemporada.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBoxTemporada.setModel(new DefaultComboBoxModel<>(Temporada.values()));
		comboBoxTemporada.setToolTipText("");
		comboBoxTemporada.setEditable(true);
		comboBoxTemporada.setBounds(107, 165, 196, 28);
		contentPanel.add(comboBoxTemporada);

		// Botón volver
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(722, 10, 113, 35);
		contentPanel.add(btnVolver);
		this.pantallaDiferente = pantallaDiferente;

		// ComboBox para el tipo de prenda
		cbTipoPrenda = new JComboBox<String>();
		cbTipoPrenda.setModel(new DefaultComboBoxModel<>());
		cbTipoPrenda.setToolTipText("");
		cbTipoPrenda.setEditable(true);
		cbTipoPrenda.setBounds(526, 316, 225, 35);
		contentPanel.add(cbTipoPrenda);
		// Cargar tipos de prenda
		cargarTipoPrenda();
		if (modificar.equalsIgnoreCase("modificar")) {
			tfColor.setText(articulo.getColor());
			comboBoxTemporada.setSelectedItem(articulo.getTemporada());
			tfPrecio.setText(String.valueOf(articulo.getPrecio()));
			tfPorcentaje.setText(String.valueOf(articulo.getPorcentajeDecuento()));
			cbTipoPrenda.setSelectedItem(articulo.getNombreTipo());
			btnSubirDatos.setText("Modificar");

		} else {
			btnSubirDatos.setText("Subir Datos");

		}

	}

	/**
	 * Carga los tipos de prenda en el comboBox.
	 */
	private void cargarTipoPrenda() {
		try {
			tipoPrenda = Controlador.listarTipoArticulos();
			for (Tipo tipo : tipoPrenda.values()) {
				cbTipoPrenda.addItem(tipo.getNombreTipo() + "_" + tipo.getCodTipo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbTipoPrenda.setSelectedIndex(-1);
	}

	/**
	 * Maneja los eventos de los botones en el diálogo.
	 * 
	 * @param evento el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(btnSubirDatos)) {
			if (btnSubirDatos.getText().equalsIgnoreCase("Subir datos")) {
				subirDatos();
			} else if (btnSubirDatos.getText().equalsIgnoreCase("Modificar")) {
				System.out.println("antes de modificar");
				modificar();
			}
		}
		if (evento.getSource().equals(btnVolver)) {
			volver(pantallaDiferente);
		}

	}

	private void modificar() {
		boolean modificado;
		Articulo art = cargarDatosArticulo();
		modificado = Controlador.modificarArticulo(art);
		if (modificado == true) {
			JOptionPane.showMessageDialog(null, "se ha modificado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "No se ha modificado nada", null, JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Vuelve a la ventana de administración.
	 * 
	 * @param pantallaDiferente
	 */
	private void volver(int pantallaDiferente) {
		if (pantallaDiferente == 0) {
			VAdministracion ven = new VAdministracion(null, true);
			this.dispose();
			ven.setLocationRelativeTo(this);
			ven.setVisible(true);

		} else {
			VMoDatosArticulo ven = new VMoDatosArticulo(null, true);
			this.dispose();
			ven.setLocationRelativeTo(this);
			ven.setVisible(true);
		}
	}

	/**
	 * Sube los datos del artículo.
	 */
	private void subirDatos() {
		Articulo art = new Articulo();
		try {
			cargarDatosArticulo(art);
			if (btnSubirDatos.isShowing()) {
				JOptionPane.showMessageDialog(this, "El artículo se ha dado de alta");
			}
		} catch (CreateException e) {
			e.printStackTrace();
		}
		this.limpiar();
	}

	/**
	 * Limpia los campos de texto del formulario.
	 */
	private void limpiar() {
		tfColor.setText("");
		tfPorcentaje.setText("");
		tfPrecio.setText("");
		cbTipoPrenda.setSelectedItem("");
	}

	/**
	 * Carga los datos del artículo desde los campos de texto y llama al controlador
	 * para insertar el artículo.
	 * 
	 * @param articulo el objeto Articulo a cargar con los datos del formulario
	 * @throws CreateException si ocurre un error al crear el artículo
	 */
	public void cargarDatosArticulo(Articulo articulo) throws CreateException {
		articulo.setColor(tfColor.getText());
		String porcentaje = tfPorcentaje.getText();
		float porcent = Float.parseFloat(porcentaje);
		articulo.setPorcentajeDecuento(porcent);
		String precio = tfPrecio.getText();
		Float precio2 = Float.parseFloat(precio);
		articulo.setPrecio(precio2);
		articulo.setTemporada((Temporada) comboBoxTemporada.getSelectedItem());
		articulo.setNombreTipo((String) cbTipoPrenda.getSelectedItem());

		Controlador.altaArticulo(articulo);
	}

	public Articulo cargarDatosArticulo() {
		// este metod devuelve un articulo cargandolo asi en la pantalla
		String porcentaje = tfPorcentaje.getText();
		float porcent = Float.parseFloat(porcentaje);
		articulo.setPorcentajeDecuento(porcent);
		String precio = tfPrecio.getText();
		Float precio2 = Float.parseFloat(precio);
		articulo.setPrecio(precio2);
		articulo.setTemporada((Temporada) comboBoxTemporada.getSelectedItem());
		articulo.setNombreTipo((String) cbTipoPrenda.getSelectedItem());

		return articulo;
	}
}