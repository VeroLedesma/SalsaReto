package controlador;

import java.util.List;
import java.util.Map;

import modelo.Articulo;
import modelo.Persona;
import modelo.Tipo;

/**
 * Interfaz que define las operaciones de acceso a datos.
 * 
 * @author melany, santiago
 * 
 */
public interface Dao {

	/**
	 * Da de alta un nuevo artículo.
	 *
	 * @param art el objeto Articulo a dar de alta.
	 * @return el ID del artículo recién creado.
	 */
	public int altaArticulo(Articulo art);

	/**
	 * Registra un nuevo usuario.
	 *
	 * @param per el objeto Persona a registrar.
	 * @return true si el registro fue exitoso, false en caso contrario.
	 */
	public boolean registrarUsuario(Persona per);

	/**
	 * Introduce un nuevo tipo de artículo.
	 *
	 * @param tipo el objeto Tipo a introducir.
	 * @return el ID del tipo de artículo recién creado.
	 */
	public int introducirTipoArticulo(Tipo tipo);

	/**
	 * Lista todos los usuarios.
	 *
	 * @return una lista de objetos Persona.
	 */
	public List<Persona> listarUsuarios();

	/**
	 * Lista todos los artículos.
	 *
	 * @return una lista de objetos Articulo.
	 */
	public List<Articulo> listarArticulos();

	/**
	 * Lista todos los tipos de artículos.
	 *
	 * @return un mapa con el ID del tipo como clave y el objeto Tipo como valor.
	 */
	public Map<Integer, Tipo> listarTiposArticulos();

	/**
	 * Inicia sesión con las credenciales proporcionadas.
	 *
	 * @param email      el email del usuario.
	 * @param contrasena la contraseña del usuario.
	 * @return true si las credenciales son correctas, false en caso contrario.
	 */
	public boolean iniciarSesion(String email, String contrasena);

	/**
	 * Modifica un usuario.
	 *
	 * @param per el objeto Persona a modificar.
	 * @return true si la modificación fue exitosa, false en caso contrario.
	 */
	public boolean modificarUsuario(Persona per);
}