package modelo;

import java.time.LocalDate;

/**
 * Clase que representa a un usuario, que extiende de la clase Persona.
 */
public class Usuario extends Persona {

	private LocalDate fechaRegistro; // Fecha de registro del usuario

	/**
	 * Obtiene la fecha de registro del usuario.
	 * 
	 * @return La fecha de registro.
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

}