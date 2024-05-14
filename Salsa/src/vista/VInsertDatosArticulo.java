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

public class VInsertDatosArticulo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfPrecio, tfColor, tfPorcentaje;
	private JComboBox<Temporada> comboBoxTemporada;
	private JComboBox<String> cbTipoPrenda;
	private Map<Integer, Tipo> tipoPrenda = new HashMap<>();
	private JButton btnSubirDatos, btnVolver, btnmodificar;

	// Lógica para la conexión
	// privat e static Controlador cont = new Controlador();

	/**
	 * Create the dialog.
	 * 
	 * @param modal
	 * @param administracion
	 * 
	 * @param selectedId
	 */
	public VInsertDatosArticulo(VAdministracion administracion, boolean modal) {
		super(administracion);
		setModal(modal);
		setBounds(100, 100, 862, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(262, 35, 292, 69);
		logo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		contentPanel.add(logo);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(526, 118, 51, 35);
		lblColor.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblColor);

		tfColor = new JTextField();
		tfColor.setBounds(526, 163, 225, 35);
		tfColor.setColumns(10);
		contentPanel.add(tfColor);

		JLabel lblTemporada = new JLabel("Temporada");
		lblTemporada.setBounds(107, 118, 84, 35);
		lblTemporada.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblTemporada);

		JLabel lblNombreTipo = new JLabel("Nombre del tipo de prenda");
		lblNombreTipo.setBounds(526, 274, 225, 35);
		lblNombreTipo.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblNombreTipo);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(107, 208, 56, 35);
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPanel.add(lblPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(107, 253, 225, 35);
		tfPrecio.setColumns(10);
		contentPanel.add(tfPrecio);

		btnSubirDatos = new JButton("Subir Datos");
		btnSubirDatos.addActionListener(this);

		btnSubirDatos.setBounds(143, 488, 203, 60);
		btnSubirDatos.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPanel.add(btnSubirDatos);

		JLabel lblPorcentaje = new JLabel("Porcentaje de descuento");
		lblPorcentaje.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPorcentaje.setBounds(107, 316, 196, 35);
		contentPanel.add(lblPorcentaje);

		tfPorcentaje = new JTextField();
		tfPorcentaje.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tfPorcentaje.setColumns(10);
		tfPorcentaje.setBounds(107, 361, 225, 35);
		contentPanel.add(tfPorcentaje);

		comboBoxTemporada = new JComboBox<>();
		comboBoxTemporada.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBoxTemporada.setModel(new DefaultComboBoxModel<>(Temporada.values()));
		comboBoxTemporada.setToolTipText("");
		comboBoxTemporada.setEditable(true);
		comboBoxTemporada.setBounds(107, 165, 196, 28);
		contentPanel.add(comboBoxTemporada);

		btnmodificar = new JButton("Modificar");
		btnmodificar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnmodificar.setBounds(567, 489, 155, 60);
		contentPanel.add(btnmodificar);

		btnVolver = new JButton("volver");
		btnVolver.addActionListener(this);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(722, 10, 113, 35);
		contentPanel.add(btnVolver);

		cbTipoPrenda = new JComboBox<String>();
		cbTipoPrenda.setModel(new DefaultComboBoxModel<>());
		cbTipoPrenda.setToolTipText("");
		cbTipoPrenda.setEditable(true);
		cbTipoPrenda.setBounds(526, 316, 225, 35);
		contentPanel.add(cbTipoPrenda);
		cargarTipoPrenda();
	}

	private void cargarTipoPrenda() {
		try {
			tipoPrenda = Controlador.listarTipoArticulos();
			for (Tipo tipo : tipoPrenda.values()) {
				cbTipoPrenda.addItem(tipo.getNombreTipo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbTipoPrenda.setSelectedIndex(-1);
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

	private void volver() { // vuelve a la ventana de administracion
		VAdministracion ven = new VAdministracion(null, true);
		this.dispose();
		ven.setLocationRelativeTo(this);
		ven.setVisible(true);

	}

	private void subirDatos() {
		Articulo art = new Articulo();
		try {
			cargarDatosArticulo(art);
			if (btnSubirDatos.isShowing()) {
				JOptionPane.showMessageDialog(this, "El articulo se ha dado de alta");
			}
		} catch (CreateException e) {
			e.printStackTrace();
		}
		this.limpiar();
	}

	private void limpiar() {
		tfColor.setText("");
		tfPorcentaje.setText("");
		tfPrecio.setText("");
		cbTipoPrenda.setSelectedItem("");
	}

	public void cargarDatosArticulo(Articulo articulo) throws CreateException {

		// guardamos el texto de tf en una variable

		// luego parseamos dicha variable
		articulo.setColor(tfColor.getText());
		String porcentaje = tfPorcentaje.getText();
		float porcent = Float.parseFloat(porcentaje);
		articulo.setPorcentajeDecuento(porcent);
		String precio = tfPrecio.getText();
		Float precio2 = Float.parseFloat(precio);
		articulo.setPrecio(precio2);
		articulo.setTemporada((Temporada) comboBoxTemporada.getSelectedItem());
		articulo.setNombreTipo((String) cbTipoPrenda.getSelectedItem());
		// para la creacion de un a excepcion pero hay que mirarlo, no se si esta bien
		int cod = Controlador.altaArticulo(articulo);
	}
}
