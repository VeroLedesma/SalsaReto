package controlador;

import java.util.List;

import modelo.Articulo;
import modelo.Persona;

public interface Dao {

	// Comprobacion de inicio de sesion

	public boolean altaArticulo(Articulo art);

	public boolean registrarUsuario(Persona per);

	public List<Persona> iniciarSesion();

	// void comprobarUsuario(Persona per);

}