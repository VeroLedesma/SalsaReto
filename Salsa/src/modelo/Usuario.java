package modelo;

import java.time.LocalDate;

/**
 * La clase Usuario representa a un usuario del sistema.
 * Extiende la clase Persona.
 */
public class Usuario extends Persona {
	private LocalDate fechaRegistro; // Fecha de registro del usuario

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
     * @param fechaRegistro La nueva fecha de registro del usuario.
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}