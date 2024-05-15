package modelo;

import java.time.LocalDate;

/**
 * Clase que representa a una persona.
 * 
 * @author melany, santi, vero
 */
public class Persona {
	private String dni, nombre, apellido, email, direccion, contrasena;
	private LocalDate fechaNacimiento;
	private Sexo genero;

	// Getters y Setters

	/**
	 * Obtiene el DNI de la persona.
	 * 
	 * @return El DNI de la persona.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el DNI de la persona.
	 * 
	 * @param dni El DNI de la persona.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Obtiene el nombre de la persona.
	 * 
	 * @return El nombre de la persona.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la persona.
	 * 
	 * @param nombre El nombre de la persona.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el apellido de la persona.
	 * 
	 * @return El apellido de la persona.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido de la persona.
	 * 
	 * @param apellido El apellido de la persona.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Obtiene el email de la persona.
	 * 
	 * @return El email de la persona.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email de la persona.
	 * 
	 * @param email El email de la persona.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la dirección de la persona.
	 * 
	 * @return La dirección de la persona.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece la dirección de la persona.
	 * 
	 * @param direccion La dirección de la persona.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Obtiene la fecha de nacimiento de la persona.
	 * 
	 * @return La fecha de nacimiento de la persona.
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento de la persona.
	 * 
	 * @param fechaNacimiento La fecha de nacimiento de la persona.
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Obtiene el género de la persona.
	 * 
	 * @return El género de la persona.
	 */
	public Sexo getGenero() {
		return genero;
	}

	/**
	 * Establece el género de la persona.
	 * 
	 * @param genero El género de la persona.
	 */
	public void setGenero(Sexo genero) {
		this.genero = genero;
	}

	/**
	 * Obtiene la contraseña de la persona.
	 * 
	 * @return La contraseña de la persona.
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Establece la contraseña de la persona.
	 * 
	 * @param contrasena La contraseña de la persona.
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	// Métodos de validación

	/**
	 * Método para verificar la validez de un email.
	 * 
	 * @author vero
	 * @param email El email a validar.
	 * @return true si el email es válido, false si no lo es.
	 * @throws IllegalArgumentException Si el email proporcionado no es válido.
	 */
	public boolean validarEmail(String email) throws IllegalArgumentException {
		try {
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			if (email.matches(emailRegex)) {
				return true;
			} else {
				throw new IllegalArgumentException("El email proporcionado no es válido");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Método para verificar la validez de un DNI.
	 * 
	 * @author vero
	 * 
	 * @param dni El DNI a validar.
	 * @return true si el DNI es válido, false si no lo es.
	 * @throws IllegalArgumentException Si el DNI proporcionado no es válido.
	 */
	public boolean validarDNI(String dni) throws IllegalArgumentException {

		try {
			if (dni.length() == 9) {
				if (dni.substring(0, 8).matches("\\d+")) {
					String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
					char letraCalculada = letras.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
					char letraDNI = dni.charAt(8);
					if (letraCalculada == letraDNI) {
						return true;
					} else {
						throw new IllegalArgumentException("El DNI proporcionado no es válido");
					}
				}
			}
			throw new IllegalArgumentException("El DNI proporcionado no es válido");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
