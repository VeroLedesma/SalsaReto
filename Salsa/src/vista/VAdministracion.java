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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Clase VAdministracion representa la ventana de administración para gestionar
 * stock de artículos y usuarios. Hereda de JDialog y maneja eventos de los
 * botones.
 */
public class VAdministracion extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane, panelAdvise;
    private JLabel lblAdvise1, lblAdvise2, lblAdvise3, lblLogo;
    private JButton btnVolver, btnModificarDatosArticulo, btnInsertarNuevoArticulo, btnListarUsuarios, btnInsertarTipo;

    private VMain main;

    /**
     * Constructor de la clase VAdministracion.
     * 
     * @param hamburger referencia al objeto VHamburger
     * @param modal     indica si el diálogo es modal
     */
    public VAdministracion(VHamburger hamburger, boolean modal) {
        super(hamburger);
        setModal(modal);
        setBounds(100, 100, 669, 692);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Panel de avisos
        panelAdvise = new JPanel();
        panelAdvise.setForeground(Color.RED);
        panelAdvise.setBounds(26, 170, 608, 80);
        panelAdvise.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
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

        lblAdvise3 = new JLabel("consultarlo previamente.");
        lblAdvise3.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdvise3.setForeground(Color.BLACK);
        lblAdvise3.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
        lblAdvise3.setBounds(67, 50, 427, 20);
        panelAdvise.add(lblAdvise3);

        lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(getClass().getResource("/assets/logo.png"))); // Usa rutas relativas
        lblLogo.setBounds(194, 52, 245, 51);
        contentPane.add(lblLogo);

        // Botón para mostrar datos de un artículo
        btnModificarDatosArticulo = new JButton("Mostrar Datos Artículo");
        btnModificarDatosArticulo.addActionListener(this);
        btnModificarDatosArticulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnModificarDatosArticulo.setBounds(194, 400, 234, 60);
        contentPane.add(btnModificarDatosArticulo);

        // Botón para insertar un nuevo artículo
        btnInsertarNuevoArticulo = new JButton("Insertar Nuevo Artículo");
        btnInsertarNuevoArticulo.addActionListener(this);
        btnInsertarNuevoArticulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnInsertarNuevoArticulo.setBounds(194, 330, 234, 60);
        contentPane.add(btnInsertarNuevoArticulo);

        // Botón para volver a la pantalla anterior
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnVolver.addActionListener(this);
        btnVolver.setBounds(10, 10, 85, 21);
        contentPane.add(btnVolver);

        // Botón para listar usuarios
        btnListarUsuarios = new JButton("Listar Usuarios");
        btnListarUsuarios.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnListarUsuarios.setBounds(194, 466, 234, 60);
        btnListarUsuarios.addActionListener(this);
        contentPane.add(btnListarUsuarios);

        // Botón para insertar un nuevo tipo de artículo
        btnInsertarTipo = new JButton("Insertar Tipo de Articulo");
        btnInsertarTipo.addActionListener(this);
        btnInsertarTipo.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnInsertarTipo.setBounds(194, 260, 234, 60);
        contentPane.add(btnInsertarTipo);
    }

    /**
     * Maneja los eventos de los botones en la ventana de administración.
     * 
     * @param e el evento de acción
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnModificarDatosArticulo)) {
            moDatosArticulo();
        } else if (e.getSource().equals(btnInsertarNuevoArticulo)) {
            insertDat();
        } else if (e.getSource().equals(btnListarUsuarios)) {
            listarUsuarios();
        } else if (e.getSource().equals(btnVolver)) {
            volver();
        } else if (e.getSource().equals(btnInsertarTipo)) {
            insertarTipo();
        }
    }

    /**
     * Abre la ventana para insertar un nuevo tipo de artículo.
     */
    private void insertarTipo() {
        VInsertarTipoArticulo tipo = new VInsertarTipoArticulo(this, true);
        this.dispose();
        tipo.setLocationRelativeTo(this);
        tipo.setVisible(true);
    }

    /**
     * Abre la ventana para insertar datos de un nuevo artículo.
     */
    protected void insertDat() {
        VInsertDatosArticulo insert = new VInsertDatosArticulo(this, true);
        this.dispose();
        insert.setLocationRelativeTo(this);
        insert.setVisible(true);
    }

    /**
     * Abre la ventana para modificar datos de un artículo existente.
     */
    protected void moDatosArticulo() {
        VMoDatosArticulo modArt = new VMoDatosArticulo(this, true);
        this.dispose();
        modArt.setLocationRelativeTo(this);
        modArt.setVisible(true);
    }

    /**
     * Abre la ventana para listar usuarios.
     */
    protected void listarUsuarios() {
        VListarUsuarios mod = new VListarUsuarios(this, true);
        this.dispose();
        mod.setLocationRelativeTo(this);
        mod.setVisible(true);
    }

    /**
     * Vuelve a la ventana de hamburguesa.
     */
    protected void volver() {
        VHamburger ham = new VHamburger(main, false);
        this.dispose();
        ham.setLocationRelativeTo(this);
        ham.setVisible(true);
    }
}
