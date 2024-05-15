package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Tipo;

public class VInsertarTipoArticulo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre, tfStock;
	private JButton btnVolver, btnEliminar, btnInsertarDatos;

	/**
	 * Create the dialog.
	 * 
	 * @param modal
	 * @param vAdministracion
	 */
	public VInsertarTipoArticulo(VAdministracion vAdministracion, boolean modal) {
		super(vAdministracion);
		setModal(modal);
		setBounds(100, 100, 741, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(228, 10, 261, 69);
		logo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		contentPanel.add(logo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(483, 156, 73, 31);
		contentPanel.add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(483, 188, 167, 40);
		contentPanel.add(tfNombre);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(111, 156, 73, 31);
		contentPanel.add(lblStock);

		tfStock = new JTextField();
		tfStock.setColumns(10);
		tfStock.setBounds(111, 188, 167, 40);
		contentPanel.add(tfStock);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(622, 10, 95, 31);
		contentPanel.add(btnVolver);
		btnVolver.addActionListener(this);
		btnInsertarDatos = new JButton("Insertar datos");
		btnInsertarDatos.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInsertarDatos.setBounds(57, 410, 176, 31);
		btnInsertarDatos.addActionListener(this);
		contentPanel.add(btnInsertarDatos);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(462, 410, 176, 31);
		btnEliminar.addActionListener(this);
		contentPanel.add(btnEliminar);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(btnInsertarDatos)) {
			InsertarDatosTipo();
		}
		if (evento.getSource().equals(btnVolver)) {
			volver();
		}
	}

	private void volver() {
		VAdministracion admin = new VAdministracion(null, true);
		this.dispose();
		admin.setLocationRelativeTo(this);
		admin.setVisible(true);

	}

	private void InsertarDatosTipo() {
		Tipo tipo = new Tipo();
		cargarDatosTipo(tipo);
		this.limpiar();
	}

	private void limpiar() {
		tfNombre.setText("");
		tfStock.setText("");
	}

	private void cargarDatosTipo(Tipo tipo) {

		tipo.setNombreTipo(tfNombre.getText());
		tipo.setStock(Integer.parseInt(tfStock.getText()));
		int codigo = Controlador.altaTipoArticulo(tipo);
	}
}
