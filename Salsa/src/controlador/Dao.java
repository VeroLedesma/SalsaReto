package controlador;

import java.util.List;
import java.util.Map;

import modelo.Articulo;
import modelo.Persona;
import modelo.Tipo;

public interface Dao {

	// Comprobacion de inicio de sesion

	public int altaArticulo(Articulo art);

	public boolean registrarUsuario(Persona per);

	public int introducirTipoArticulo(Tipo tipo);

	public List<Persona> listarUsuarios();

	public List<Articulo> listarArticulos();

	public Map<Integer, Tipo> listarTiposArticulos();

	public boolean iniciarSesion(String email, String contrasena);

	public boolean modificarUsuario(Persona per);

}