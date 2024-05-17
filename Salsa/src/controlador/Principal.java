package controlador;

import modelo.Persona;
import vista.VLogin;

/**
 * Clase principal que inicia la aplicación.
 */
public class Principal {

	/**
	 * Método principal que se ejecuta al iniciar la aplicación.
	 * 
	 * @author melany
	 * @param args argumentos de la línea de comandos (no utilizados).
	 */
	public static void main(String[] args) {
		// Instanciamos y creamos un objeto para el controlador y para la persona
		Persona persona = new Persona();

		// Le pasamos la ventana principal que en nuestro caso es el Login
		VLogin login = new VLogin(persona);
		login.setVisible(true);
	}
}
