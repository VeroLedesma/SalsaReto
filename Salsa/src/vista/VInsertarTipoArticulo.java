package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Tipo;

/**
 * La clase VInsertarTipoArticulo representa un diálogo para insertar tipos de
 * artículos. Hereda de JDialog e implementa ActionListener para manejar eventos
 * de botones.
 */
public class VInsertarTipoArticulo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre, tfStock;
	private JButton btnVolver, btnEliminar, btnInsertarDatos;

	/**
	 * Constructor de la clase VInsertarTipoArticulo.
	 * 
	 * @param vAdministracion referencia a la ventana de administración
	 * @param modal           indica si el diálogo es modal
	 */
	public VInsertarTipoArticulo(VAdministracion vAdministracion, boolean modal) {
		super(vAdministracion);
		setModal(modal);
		setBounds(100, 100, 741, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Logo
		JLabel logo = new JLabel("");
		logo.setBounds(228, 10, 261, 69);
		logo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		contentPanel.add(logo);

		// Etiqueta y campo de texto para el nombre
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(483, 156, 73, 31);
		contentPanel.add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(483, 188, 167, 40);
		contentPanel.add(tfNombre);

		// Etiqueta y campo de texto para el stock
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(111, 156, 73, 31);
		contentPanel.add(lblStock);

		tfStock = new JTextField();
		tfStock.setColumns(10);
		tfStock.setBounds(111, 188, 167, 40);
		contentPanel.add(tfStock);

		// Botón volver
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(622, 10, 95, 31);
		contentPanel.add(btnVolver);
		btnVolver.addActionListener(this);

		// Botón insertar datos
		btnInsertarDatos = new JButton("Insertar datos");
		btnInsertarDatos.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInsertarDatos.setBounds(57, 410, 176, 31);
		btnInsertarDatos.addActionListener(this);
		contentPanel.add(btnInsertarDatos);

		// Botón eliminar
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(462, 410, 176, 31);
		btnEliminar.addActionListener(this);
		contentPanel.add(btnEliminar);
	}

	/**
	 * Maneja los eventos de los botones en el diálogo.
	 * 
	 * @param evento el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(btnInsertarDatos)) {
			InsertarDatosTipo();
		}
		if (evento.getSource().equals(btnVolver)) {
			volver();
		}
	}

	/**
	 * Cierra el diálogo actual y muestra la ventana de administración.
	 */
	private void volver() {
		VAdministracion admin = new VAdministracion(null, true);
		this.dispose();
		admin.setLocationRelativeTo(this);
		admin.setVisible(true);
	}

	/**
	 * Inserta los datos del tipo de artículo.
	 */
	private void InsertarDatosTipo() {
		Tipo tipo = new Tipo();
		cargarDatosTipo(tipo);
		this.limpiar();
	}

	/**
	 * Limpia los campos de texto del formulario.
	 */
	private void limpiar() {
		tfNombre.setText("");
		tfStock.setText("");
	}

	/**
	 * Carga los datos del tipo de artículo desde los campos de texto y llama al
	 * controlador para insertar el tipo.
	 * 
	 * @param tipo el objeto Tipo a cargar con los datos del formulario
	 */
	private void cargarDatosTipo(Tipo tipo) {
		boolean existe = false;
		tipo.setNombreTipo(tfNombre.getText());
		tipo.setStock(Integer.parseInt(tfStock.getText()));
		existe = Controlador.altaTipoArticulo(tipo);
		if (existe == false) {
			JOptionPane.showMessageDialog(null, "Se ha modificado el stock por que el tipo de articulo ya existe");
		} else {
			JOptionPane.showMessageDialog(null, "se ha insertado el tipo correctamente");
		}
	}
}
