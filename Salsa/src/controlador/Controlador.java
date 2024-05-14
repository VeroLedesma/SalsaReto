package controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import modelo.Articulo;
import modelo.ImpleDB;
import modelo.Persona;
import modelo.Tipo;

public class Controlador {

	public static List<Persona> listarUsuarios() throws SQLException {
		Dao dao = new ImpleDB();
		return dao.listarUsuarios();
	}

	public static List<Articulo> listarArticulos() throws SQLException {
		Dao dao = new ImpleDB();
		return dao.listarArticulos();
	}

	public static Map<Integer, Tipo> listarTipoArticulos() throws SQLException {
		Dao dao = new ImpleDB();
		return dao.listarTiposArticulos();
	}

	public static boolean modificarUsuario(Persona per) {
		Dao dao = new ImpleDB();
		return dao.modificarUsuario(per);
	}

	public static boolean iniciarSesion(String email, String contrasena) {
		Dao dao = new ImpleDB();
		return dao.iniciarSesion(email, contrasena);
	}

	public static boolean registrarUsuario(Persona per) {
		Dao dao = new ImpleDB();
		return dao.registrarUsuario(per);
	}
	// verificar si esto es necesario o no

	public static int altaTipoArticulo(Tipo tipo) {
		Dao dao = new ImpleDB();
		return dao.introducirTipoArticulo(tipo);
	}

	public static int altaArticulo(Articulo art) {
		Dao dao = new ImpleDB();
		return dao.altaArticulo(art);
	}

}
