package controlador;

import java.util.List;

import modelo.Articulo;
import modelo.ImpleDB;
import modelo.Persona;

public class Controlador {

	public static List<Persona> iniciarSesion() {
		Dao dao = new ImpleDB();
		return dao.iniciarSesion();
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
