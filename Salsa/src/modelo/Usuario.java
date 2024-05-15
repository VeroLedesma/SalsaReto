package modelo;

import java.time.LocalDate;

/**
 * Clase que representa a un usuario, que extiende de la clase Persona. La clase
 * Usuario representa a un usuario del sistema. Extiende la clase Persona.
 * 
 * @author vero, santi, melany
 */
public class Usuario extends Persona {
	private LocalDate fechaRegistro; // Fecha de registro del usuario

	/**
	 * Establece la fecha de registro del usuario.
	 * 
	 * @param fechaRegistro La fecha de registro.
	 */
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Obtiene la fecha de registro del usuario.
	 * 
	 * @return La fecha de registro del usuario.
	 */
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

}