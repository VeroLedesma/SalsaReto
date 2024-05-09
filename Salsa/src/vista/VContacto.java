package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VContacto extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblIconoTelefono;
	private JLabel lblLogo;
	private JLabel lblIconoTelefono_1;
	private JButton btnVolver;

	/**
	 * Create the dialog.
	 * 
	 * @param oscuro
	 */
	public VContacto(VHamburger hamburger, boolean modal) {
		super(hamburger);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 847, 949);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitulo = new JLabel("¿Quieres saber más?");
			lblTitulo.setBounds(280, 99, 326, 81);
			lblTitulo.setBackground(new Color(255, 255, 255));
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
			contentPanel.add(lblTitulo);
		}
		{
			JLabel lblNumero = new JLabel("900800970");
			lblNumero.setBounds(157, 261, 162, 70);
			lblNumero.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPanel.add(lblNumero);
		}
		{
			JLabel lBTelefono = new JLabel("Teléfono ");
			lBTelefono.setBounds(157, 272, 99, 14);
			lBTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
			contentPanel.add(lBTelefono);
		}
		{
			JLabel lblNmeroGratutito = new JLabel("Número gratutito");
			lblNmeroGratutito.setBounds(157, 308, 142, 14);
			lblNmeroGratutito.setFont(new Font("Tahoma", Font.PLAIN, 13));
			contentPanel.add(lblNmeroGratutito);
		}
		{
			JLabel lblNumero = new JLabel("+351 925507494");
			lblNumero.setBounds(462, 261, 275, 70);
			lblNumero.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPanel.add(lblNumero);
		}
		{
			JLabel lBWhatsapp = new JLabel("Whatsapp");
			lBWhatsapp.setBounds(462, 272, 99, 14);
			lBWhatsapp.setFont(new Font("Tahoma", Font.PLAIN, 13));
			contentPanel.add(lBWhatsapp);
		}
		{
			lblIconoTelefono = new JLabel("");
			lblIconoTelefono.setIcon(new ImageIcon(getClass().getResource("/assets/telefono-inteligente.png")));
			lblIconoTelefono.setBounds(109, 261, 38, 70);
			contentPanel.add(lblIconoTelefono);
		}
		{
			lblIconoTelefono_1 = new JLabel("");
			lblIconoTelefono_1.setIcon(new ImageIcon(getClass().getResource("/assets/llamada-telefonica.png")));
			lblIconoTelefono_1.setBounds(418, 240, 43, 101);
			contentPanel.add(lblIconoTelefono_1);
		}
		{
			JLabel lblAyudamos = new JLabel("¡Ayúdamos!");
			lblAyudamos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblAyudamos.setBounds(359, 166, 234, 25);
			contentPanel.add(lblAyudamos);
		}
		{
			JLabel lblNewLabel = new JLabel("De las 8h a las 23h, del lunes a viernes");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(252, 200, 326, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblFoto = new JLabel("");
			lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
			lblFoto.setBackground(new Color(240, 240, 240));
			lblFoto.setIcon(new ImageIcon(VContacto.class.getResource("/assets/leftimage.jpg")));
			lblFoto.setBounds(90, 360, 600, 800);
			contentPanel.add(lblFoto);
		}
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setBounds(31, 22, 89, 23);
		contentPanel.add(btnVolver);

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		lblLogo.setBounds(262, 51, 298, 54);
		contentPanel.add(lblLogo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVolver)) {
			volver();
		}
	}

	private void volver() {
		VHamburger ham = new VHamburger(null, false);
		ham.setVisible(true);
		this.dispose();
	}

}