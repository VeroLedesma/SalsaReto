package controlador;

import modelo.Persona;
import vista.VLogin;

public class Principal {

	public static void main(String[] args) {
		// Instanciamos y creamos un objeto para el controlador y para la persona
		Persona persona = new Persona();

		// Le pasamos la ventana principal que en nuestro caso es el Login
		VLogin login = new VLogin(persona);
		login.setVisible(true);

	}
}
