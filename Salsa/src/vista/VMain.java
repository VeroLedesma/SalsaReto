package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Articulo;

/**
 * La clase VMain representa la ventana principal de la aplicación. Muestra
 * diferentes categorías de artículos y un botón de menú.
 */
public class VMain extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel BodyLayout, itemsPanel;
	private JLabel logo;
	private JButton btnMenu, btnAniadirMiLista;

	private JScrollPane scrollPane;
	private String email;

	/**
	 * Constructor de la clase VMain.
	 * 
	 * @param login el JFrame padre desde el cual se abre este diálogo
	 * @param modal indica si el diálogo es modal
	 * @param email se le pasa el email
	 * @wbp.parser.constructor
	 */
	public VMain(VLogin login, boolean modal, String email) {
		super(login);
		setModal(modal);
		this.email = email;
		setBounds(100, 100, 1098, 834);
		BodyLayout = new JPanel();
		BodyLayout.setBackground(new Color(255, 255, 255));
		BodyLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BodyLayout);

		// Logo centrado
		logo = new JLabel("");
		logo.setBounds(288, 63, 419, 83);
		ImageIcon icon = new ImageIcon(getClass().getResource("/assets/logo.png"));
		ImageIcon img = new ImageIcon(
				icon.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH));
		BodyLayout.setLayout(null);
		BodyLayout.setLayout(null);
		logo.setIcon(img);
		BodyLayout.add(logo);

		// Contenedor para la rejilla de artículos

		itemsPanel = new JPanel();
		itemsPanel.setBounds(10, 168, 1058, 89);
		BodyLayout.add(itemsPanel);
		itemsPanel.setLayout(null);

		// Botón de menú
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(921, 58, 147, 46);
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMenu.addActionListener(this);
		BodyLayout.add(btnMenu);

		JButton btnTodos = new JButton("Articulos Destacados");
		btnTodos.setBounds(20, 267, 221, 55);
		btnTodos.setFont(new Font("Tahoma", Font.BOLD, 14));
		BodyLayout.add(btnTodos);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 332, 1058, 440);
		BodyLayout.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		visualizarArt(panel);

		btnAniadirMiLista = new JButton("Ver mi lista");
		btnAniadirMiLista.addActionListener(this);
		btnAniadirMiLista.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAniadirMiLista.setBounds(894, 267, 174, 40);
		BodyLayout.add(btnAniadirMiLista);
		btnAniadirMiLista.addActionListener(this);

	}

	private void visualizarArt(JPanel panel) {
		try {
			List<Articulo> listArts = Controlador.listarArticulos();
			for (Articulo art : listArts) {
				JLabel lblImagen1 = new JLabel(String.valueOf(art.getCodArticulo()));
				lblImagen1.setBounds(10, 28, 391, 359);
				panel.add(lblImagen1);
				lblImagen1.setIcon(new ImageIcon(getClass().getResource("/assets/" + art.getNombreImg())));

				lblImagen1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// Mostrar el cuadro de diálogo
						String[] opciones = { "Añadir", "Cancelar" };
						int respuesta = JOptionPane.showOptionDialog(null, "¿Deseas añadir algo?", "Añadir",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
						JLabel sourceLabel = (JLabel) e.getSource();
						// Manejar la respuesta
						if (respuesta == JOptionPane.YES_OPTION) {
							aniadirLista(Integer.parseInt(sourceLabel.getText()));
							// Aquí puedes añadir la lógica para añadir el ítem
						} else {
							System.out.println("Has cancelado la operación.");
						}
					}
				});

			}

		} catch (SQLException e) {

		}

	}

	/*
	 * Maneja los eventos de acción de los botones.
	 * 
	 * @param evento el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(btnMenu)) {
			irAlMenu();
		}
		if (evento.getSource().equals(btnAniadirMiLista)) {
			miLista();
		}

	}

	private void miLista() {
		VMiLista miLista = new VMiLista(this, true, email, null);
		this.dispose();
		miLista.setLocationRelativeTo(this);
		miLista.setVisible(true);
	}

	/**
	 * Muestra la ventana de miLista
	 */
	private void aniadirLista(int codArt) {

		VMiLista miLista = new VMiLista(this, true, email, codArt);
		miLista.setVisible(false);

	}

	/**
	 * Abre el menú de hamburguesas y cierra la ventana actual.
	 */
	private void irAlMenu() {
		this.dispose();
		VHamburger ven = new VHamburger(this, true);
		ven.setLocationRelativeTo(this);
		ven.setVisible(true);
	}
}
