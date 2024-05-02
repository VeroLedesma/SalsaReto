package controlador;

import modelo.Articulo;
import modelo.Persona;


public interface Dao {

	// Comprobacion de inicio de sesion
	


	boolean altaArticulo(Articulo art);

	boolean registrarUsuario(Persona per);


	Persona iniciarSesion(Persona per);

	// void comprobarUsuario(Persona per);

}