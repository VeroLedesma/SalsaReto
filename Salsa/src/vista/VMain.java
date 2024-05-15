package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * La clase VMain representa la ventana principal de la aplicación.
 * Muestra diferentes categorías de artículos y un botón de menú.
 */
public class VMain extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel BodyLayout, emptyRow, itemsPanel;
	private JLabel logo;
	private JButton btnCamisetas, btnSudaderas, btnPantalones, btnMenu;

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
		logo.setBounds(378, 38, 326, 66);
		ImageIcon icon = new ImageIcon(getClass().getResource("/assets/logo.png"));
		ImageIcon img = new ImageIcon(
				icon.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH));
		logo.setIcon(img);
		BodyLayout.add(logo);

		// Añadir fila vacía debajo del logo
		emptyRow = new JPanel();
		emptyRow.setBounds(0, 150, 1066, 46);
		BodyLayout.add(emptyRow);

		// Contenedor para la rejilla de artículos
		itemsPanel = new JPanel();
		itemsPanel.setBounds(44, 316, 982, 318);
		BodyLayout.add(itemsPanel);
		itemsPanel.setLayout(new GridLayout(0, 4, 8, 10));

		// Botones para las categorías de artículos
		btnCamisetas = new JButton("Camisetas");
		btnCamisetas.addActionListener(this);
		btnCamisetas.setBounds(203, 226, 165, 46);
		BodyLayout.add(btnCamisetas);

		btnSudaderas = new JButton("Sudaderas");
		btnSudaderas.setBounds(663, 226, 165, 46);
		BodyLayout.add(btnSudaderas);

		btnPantalones = new JButton("Pantalones");
		btnPantalones.setBounds(426, 226, 165, 46);
		BodyLayout.add(btnPantalones);

		// Botón de menú
		btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMenu.addActionListener(this);
		btnMenu.setBounds(877, 10, 113, 30);
		BodyLayout.add(btnMenu);

		// Agrega elementos a la rejilla de artículos
		for (int i = 0; i < 8; i++) {
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
