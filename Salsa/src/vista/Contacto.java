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
import javax.swing.border.EmptyBorder;

import controlador.Dao;

public class Contacto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblIconoTelefono;
	private JLabel lbllogo;
	JLabel lblIconoTelefono_1;

	/**
	 * Create the dialog.
	 */
	public Contacto(Dao dao, boolean oscuro) {
		setBounds(100, 100, 815, 993);
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
			lblFoto.setIcon(new ImageIcon(Contacto.class.getResource("/assets/leftimage.png")));
			lblFoto.setBounds(0, 354, 801, 592);
			contentPanel.add(lblFoto);
		}

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver(dao);
			}

		});
		btnVolver.setBounds(31, 22, 89, 23);
		contentPanel.add(btnVolver);

		JLabel lbllogo = new JLabel("");
		lbllogo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png")));
		lbllogo.setBounds(262, 51, 298, 54);
		contentPanel.add(lbllogo);

	}

	private void volver(Dao dao) {
		Hamburger ham = new Hamburger();
		ham.setVisible(true);
		setVisible(false);// TODO Auto-generated method stub

	}

}