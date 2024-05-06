package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MoDatosUsuario extends JDialog {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * 
	 * @param administracion
	 * 
	 * @param oscuro
	 */
	public MoDatosUsuario(Administracion administracion, boolean modal) {
		super(administracion);
		setModal(modal);
		setBounds(100, 100, 711, 689);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
