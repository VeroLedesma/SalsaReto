package modelo;

/**
 * Clase que representa a un trabajador, que extiende de la clase Persona.
 */
public class Trabajador extends Persona {

	private String nnss; // Número de Seguridad Social
	private Boolean encargado = false; // Indica si el trabajador es un encargado

	/**
	 * Obtiene el número de Seguridad Social del trabajador.
	 * 
	 * @return El número de Seguridad Social.
	 */
	public String getNnss() {
		return nnss;
	}

	/**
	 * Establece el número de Seguridad Social del trabajador.
	 * 
	 * @param nnss El número de Seguridad Social.
	 */
	public void setNnss(String nnss) {
		this.nnss = nnss;
	}

	/**
	 * Verifica si el trabajador es un encargado.
	 * 
	 * @return true si el trabajador es un encargado, false de lo contrario.
	 */
	public Boolean isEncargado() {
		return encargado;
	}

	/**
	 * Establece si el trabajador es un encargado.
	 * 
	 * @param encargado true si el trabajador es un encargado, false de lo
	 *                  contrario.
	 */
	public void setEncargado(Boolean encargado) {
		this.encargado = encargado;
	}

}
