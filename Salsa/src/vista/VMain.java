package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * La clase VMain representa la ventana principal de la aplicación. Muestra
 * diferentes categorías de artículos y un botón de menú.
 */
public class VMain extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel BodyLayout, itemsPanel;
	private JLabel logo;
	private JButton btnCamisetas, btnSudaderas, btnPantalones, btnMenu;

	private JScrollPane scrollPane;

	/**
	 * Constructor de la clase VMain.
	 * 
	 * @param login el JFrame padre desde el cual se abre este diálogo
	 * @param modal indica si el diálogo es modal
	 */
	public VMain(VLogin login, boolean modal) {
		super(login);
		setModal(modal);
		setBounds(100, 100, 1082, 836);
		BodyLayout = new JPanel();
		BodyLayout.setBackground(new Color(255, 255, 255));
		BodyLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BodyLayout);
		BodyLayout.setLayout(null);

		// Logo centrado
		logo = new JLabel("");
		logo.setBounds(348, 21, 342, 76);
		ImageIcon icon = new ImageIcon(getClass().getResource("/assets/logo.png"));
		ImageIcon img = new ImageIcon(
				icon.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH));
		logo.setIcon(img);
		BodyLayout.add(logo);

		// Contenedor para la rejilla de artículos

		itemsPanel = new JPanel();
		itemsPanel.setBounds(10, 226, 1048, 428);
		BodyLayout.add(itemsPanel);
		itemsPanel.setLayout(null);

		// Botones para las categorías de artículos
		btnCamisetas = new JButton("Camisetas");
		btnCamisetas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCamisetas.addActionListener(this);
		btnCamisetas.setBounds(280, 146, 165, 46);
		BodyLayout.add(btnCamisetas);

		btnSudaderas = new JButton("Sudaderas");
		btnSudaderas.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSudaderas.setBounds(825, 146, 165, 46);
		BodyLayout.add(btnSudaderas);

		btnPantalones = new JButton("Pantalones");
		btnPantalones.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnPantalones.setBounds(563, 146, 165, 46);

		BodyLayout.add(btnPantalones);

		// Botón de menú
		btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMenu.addActionListener(this);
		btnMenu.setBounds(925, 10, 113, 30);
		BodyLayout.add(btnMenu);

		JButton btnTodos = new JButton("Todos los articulos");
		btnTodos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTodos.setBounds(10, 150, 181, 38);
		BodyLayout.add(btnTodos);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 226, 1048, 428);
		BodyLayout.add(scrollPane);

		// Agrega elementos a la rejilla de artículos
		for (int i = 0; i < 8; i++) {// Por ejemplo aqui se agregan 8 elementos

			JPanel item = new JPanel();
			item.setBackground(Color.GRAY);
			itemsPanel.add(item);
		}
	}

	/**
	 * Maneja los eventos de acción de los botones.
	 * 
	 * @param e el evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnMenu)) {
			irAlMenu();
		}
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
