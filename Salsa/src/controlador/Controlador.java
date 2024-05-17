package controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import modelo.Articulo;
import modelo.ImpleDB;
import modelo.Persona;
import modelo.Tipo;
import modelo.Usuario;

/**
 * Clase Controlador que maneja la lógica de la aplicación.
 */
public class Controlador {
	/**
	 * Lista todos los usuarios.
	 ** 
	 * @author melany, santi
	 * @return una lista de objetos Persona.
	 * @throws SQLException si ocurre un error en la base de datos.
	 */
	public static List<Persona> listarUsuarios() throws SQLException {
		Dao dao = new ImpleDB();
		return dao.listarUsuarios();
	}

	/**
	 * Lista todos los artículos.
	 *
	 * @return una lista de objetos Articulo.
	 * @throws SQLException si ocurre un error en la base de datos.
	 */
	public static List<Articulo> listarArticulos() throws SQLException {
		Dao dao = new ImpleDB();
		return dao.listarArticulos();
	}

	/**
	 * Lista todos los tipos de artículos.
	 *
	 * @return un mapa con el ID del tipo como clave y el objeto Tipo como valor.
	 * @throws SQLException si ocurre un error en la base de datos.
	 */
	public static Map<Integer, Tipo> listarTipoArticulos() throws SQLException {
		Dao dao = new ImpleDB();
		return dao.listarTiposArticulos();
	}

	/**
	 * Modifica un usuario.
	 *
	 * @param per el objeto Persona a modificar.
	 * @return true si la modificación fue exitosa, false en caso contrario.
	 */
	public static boolean modificarUsuario(Persona per) {
		Dao dao = new ImpleDB();
		return dao.modificarUsuario(per);
	}

	public static boolean modificarArticulo(Articulo art) {
		Dao dao = new ImpleDB();
		return dao.modificarArticulo(art);
	}

	/**
	 * Inicia sesión con las credenciales proporcionadas.
	 *
	 * @param email      el email del usuario.
	 * @param contrasena la contraseña del usuario.
	 * @return true si las credenciales son correctas, false en caso contrario.
	 */
	public static boolean iniciarSesion(String email, String contrasena) {
		Dao dao = new ImpleDB();
		return dao.iniciarSesion(email, contrasena);
	}

	/**
	 * Registra un nuevo usuario.
	 *
	 * @param per el objeto Persona a registrar.
	 * @return true si el registro fue exitoso, false en caso contrario.
	 */
	public static boolean registrarUsuario(Persona per) {
		Dao dao = new ImpleDB();
		return dao.registrarUsuario(per);
	}

	/**
	 * Da de alta un nuevo tipo de artículo.
	 *
	 * @param tipo el objeto Tipo a dar de alta.
	 * @return el ID del tipo de artículo recién creado.
	 */
	public static boolean altaTipoArticulo(Tipo tipo) {
		Dao dao = new ImpleDB();
		return dao.introducirTipoArticulo(tipo);
	}

	/**
	 * Da de alta un nuevo artículo.
	 *
	 * @param art el objeto Articulo a dar de alta.
	 * @return el ID del artículo recién creado.
	 */
	public static boolean altaArticulo(Articulo art) {
		Dao dao = new ImpleDB();
		return dao.altaArticulo(art);
	}

	public static Usuario obtenerUsuario(String email) {
		Dao dao = new ImpleDB();
		return dao.obtenerUsuario(email);
	}

	public static int comprobarEncargado() {
		Dao dao = new ImpleDB();

		return dao.comprobarEncargado();

	}

	public static boolean eliminarArticulo(int art) {
		Dao dao = new ImpleDB();
		return dao.eliminarArticulo(art);
	}

	public static boolean eliminarPersona(String per) {
		Dao dao = new ImpleDB();
		return dao.eliminarPersona(per);
	}

	public static Articulo obtenerArticulo(int codArt) {
		Dao dao = new ImpleDB();
		return dao.obtenerArticulo(codArt);
	}

	public static boolean aniadirCompra(String dni, int codArticulo) {
		Dao dao = new ImpleDB();
		return dao.aniadirCompra(dni, codArticulo);

	}

}
