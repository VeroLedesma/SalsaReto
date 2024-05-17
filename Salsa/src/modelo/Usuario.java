package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario, que extiende de la clase Persona. La clase
 * Usuario representa a un usuario del sistema. Extiende la clase Persona.
 * 
 * @author vero, santi, melany
 */
public class Usuario extends Persona {
	private LocalDate fechaRegistro;// Fecha de registro del usuario
	private List<Articulo> articulos = new ArrayList<>();

	/**
	 * Obtiene la fecha de registro del usuario.
	 * 
	 * @return La fecha de registro del usuario.
	 */
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Establece la fecha de registro del usuario.
	 * 
	 * @param fechaRegistro La fecha de registro.
	 */
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
}