package modelo;

import java.time.LocalDate;

/**
<<<<<<< HEAD
 * Clase que representa a un usuario, que extiende de la clase Persona.
=======
 * La clase Usuario representa a un usuario del sistema.
 * Extiende la clase Persona.
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
 */
public class Usuario extends Persona {
    private LocalDate fechaRegistro; // Fecha de registro del usuario

<<<<<<< HEAD
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
=======
    /**
     * Obtiene la fecha de registro del usuario.
     * 
     * @return La fecha de registro del usuario.
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8

    /**
     * Establece la fecha de registro del usuario.
     * 
     * @param fechaRegistro La nueva fecha de registro del usuario.
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}