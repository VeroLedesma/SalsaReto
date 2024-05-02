package controlador;

<<<<<<< HEAD
import modelo.Articulo;
import modelo.Persona;
=======
>>>>>>> 4e0de91b5a39f571c30e00b7cd9f090ca4a6ad46

import modelo.Articulo;
import modelo.Persona;


public interface Dao {

	// Comprobacion de inicio de sesion
	


	boolean altaArticulo(Articulo art);

	boolean registrarUsuario(Persona per);


	Persona iniciarSesion(Persona per);

	// void comprobarUsuario(Persona per);

}