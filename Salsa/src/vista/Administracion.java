package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Administracion extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelAdvise;
	private JLabel lblAdvise1, lblAdvise2, lblAdvise3, lblLogo;
	// Lógica para la conexión
//	private Controlador controladorRutas = new Controlador();

	private JButton btnVolver, btnModificarDatosArtculo, btnInsertarNuevoArtculo,btnListarUsuarios ;

	private Main main;
	public Administracion(boolean modal, Hamburger hamburger) {

		super(hamburger);
		setModal(modal);

		setBounds(100, 100, 669, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creamos un JPanel para contener los JLabel
		panelAdvise = new JPanel();
		panelAdvise.setForeground(Color.RED);
		panelAdvise.setBounds(26, 170, 608, 80); // Establecemos las dimensiones y la posición del panel
		panelAdvise.setBorder(new LineBorder(new Color(255, 0, 0), 3, true)); // Establecemos el borde para el panel
		contentPane.add(panelAdvise);
		panelAdvise.setLayout(null);

		lblAdvise1 = new JLabel("Has iniciado sesión como usuario de administración,");
		lblAdvise1.setForeground(Color.BLACK);
		lblAdvise1.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
		lblAdvise1.setBounds(67, 10, 442, 20);
		panelAdvise.add(lblAdvise1);

		lblAdvise2 = new JLabel("cualquier cambio que se realice es recomendable");
		lblAdvise2.setForeground(Color.BLACK);
		lblAdvise2.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
		lblAdvise2.setBounds(67, 30, getWidth() - 40, 20);
		panelAdvise.add(lblAdvise2);

		lblAdvise3 = new JLabel("consultarlo previamente con el resto de desarrolladores.");
		lblAdvise3.setForeground(Color.BLACK);
		lblAdvise3.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
		lblAdvise3.setBounds(67, 50, getWidth() - 40, 20);
		panelAdvise.add(lblAdvise3);

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("/assets/logo.png"));
		lblLogo.setBounds(194, 52, 245, 51);
		contentPane.add(lblLogo);

		btnListarUsuarios = new JButton("Listar Usuarios");
		btnListarUsuarios.addActionListener(this);
		btnListarUsuarios.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnListarUsuarios.setBounds(194, 287, 234, 60);
		contentPane.add(btnListarUsuarios);
		
		btnModificarDatosArtculo = new JButton("Modificar Datos Artículo");
		btnModificarDatosArtculo.addActionListener(this);
		btnModificarDatosArtculo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModificarDatosArtculo.setBounds(194, 388, 234, 60);
		contentPane.add(btnModificarDatosArtculo);
		btnInsertarNuevoArtculo = new JButton("Insertar Nuevo Artículo");
		btnInsertarNuevoArtculo.addActionListener(this);

		btnInsertarNuevoArtculo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInsertarNuevoArtculo.setBounds(194, 492, 234, 60);
		contentPane.add(btnInsertarNuevoArtculo);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVolver.addActionListener(this);
		btnVolver.setBounds(10, 10, 85, 21);
		contentPane.add(btnVolver);

//		if (oscuro) {
//			cambioFondo();
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnModificarDatosArtculo)) {
			moDatosArticulo();
		}
		if (e.getSource().equals(btnInsertarNuevoArtculo)) {
			insertDat();
		}
		if (e.getSource().equals(btnListarUsuarios)) {
			listarUsuarios();
		}
	}

//	private void cambioFondo() {
//		contentPane.setBackground(Color.DARK_GRAY);
//		panelAdvise.setBackground(Color.DARK_GRAY);
//		lblAdvise1.setForeground(Color.WHITE);
//		lblAdvise2.setForeground(Color.WHITE);
//		lblAdvise3.setForeground(Color.WHITE);
//	}

	protected void insertDat() {
		InsertDatosArticulo insert = new InsertDatosArticulo(this, true);
		insert.setVisible(true);
		setVisible(false);
	}

	protected void moDatosArticulo() {
		MoDatosArticulo modArt = new MoDatosArticulo(this, true);
		modArt.setVisible(true);
		setVisible(false);
	}

	protected void listarUsuarios() {
		ListarUsuarios mod = new ListarUsuarios(this, true);
		mod.setVisible(true);
		setVisible(false);
	}

	protected void volver() {
		this.dispose();
		Hamburger ham = new Hamburger(main, false);
		ham.setVisible(true);
		this.dispose();
	}

}
