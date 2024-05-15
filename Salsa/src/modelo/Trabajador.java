package modelo;

/**
<<<<<<< HEAD
 * Clase que representa a un trabajador, que extiende de la clase Persona.
=======
 * La clase Trabajador representa a un trabajador con sus datos personales y de seguridad social.
 * Extiende la clase Persona.
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
 */
public class Trabajador extends Persona {
    private String nnss; // Número de Seguridad Social del trabajador
    private Boolean encargado = false; // Indica si el trabajador es un encargado o no

<<<<<<< HEAD
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
=======
    /**
     * Obtiene el número de Seguridad Social del trabajador.
     * 
     * @return El número de Seguridad Social del trabajador.
     */
    public String getNnss() {
        return nnss;
    }

    /**
     * Establece el número de Seguridad Social del trabajador.
     * 
     * @param nnss El nuevo número de Seguridad Social del trabajador.
     */
    public void setNnss(String nnss) {
        this.nnss = nnss;
    }

    /**
     * Verifica si el trabajador es un encargado.
     * 
     * @return true si el trabajador es un encargado, false si no lo es.
     */
    public Boolean isEncargado() {
        return encargado;
    }
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8

    /**
     * Establece si el trabajador es un encargado.
     * 
     * @param encargado true si el trabajador es un encargado, false si no lo es.
     */
    public void setEncargado(Boolean encargado) {
        this.encargado = encargado;
    }
}
