package controlador;

import java.sql.SQLException;
import java.util.List;

import modelo.Articulo;
import modelo.ImpleDB;
import modelo.Persona;

public class Controlador {

	public static List<Persona> listarUsuarios() throws SQLException {
		Dao dao = new ImpleDB();
		return dao.listarUsuarios();
	}

	public static boolean iniciarSesion(String email, String contrasena) throws SQLException {
		Dao dao = new ImpleDB();
		return dao.iniciarSesion(email, contrasena);
	}

	public static boolean registrarUsuario(Persona per) {
		Dao dao = new ImpleDB();
		return dao.registrarUsuario(per);
	}
	// verificar si esto es necesario o no

	public static boolean altaArticulo(Articulo art) {
		Dao dao = new ImpleDB();
		return dao.altaArticulo(art);
	}

}
