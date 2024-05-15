package modelo;

import java.time.LocalDate;

/**
<<<<<<< HEAD
 * Clase que representa a una persona.
 * 
 * @author melany, santi, vero
=======
 * La clase Persona representa a una persona con sus datos personales y métodos para validación.
 * 
 * @autor Vero
 * @version 1.0
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
 */
public class Persona {
	private String dni, nombre, apellido, email, direccion, contrasena;
	private LocalDate fechaNacimiento;
	private Sexo genero;

<<<<<<< HEAD
	// Getters y Setters

	/**
	 * Obtiene el DNI de la persona.
	 * 
	 * @return El DNI de la persona.
=======
	/**
	 * Obtiene el DNI de la persona.
	 * 
	 * @return el DNI de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el DNI de la persona.
	 * 
<<<<<<< HEAD
	 * @param dni El DNI de la persona.
=======
	 * @param dni el nuevo DNI de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Obtiene el nombre de la persona.
	 * 
<<<<<<< HEAD
	 * @return El nombre de la persona.
=======
	 * @return el nombre de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la persona.
	 * 
<<<<<<< HEAD
	 * @param nombre El nombre de la persona.
=======
	 * @param nombre el nuevo nombre de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el apellido de la persona.
	 * 
<<<<<<< HEAD
	 * @return El apellido de la persona.
=======
	 * @return el apellido de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido de la persona.
	 * 
<<<<<<< HEAD
	 * @param apellido El apellido de la persona.
=======
	 * @param apellido el nuevo apellido de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Obtiene el email de la persona.
	 * 
<<<<<<< HEAD
	 * @return El email de la persona.
=======
	 * @return el email de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email de la persona.
	 * 
<<<<<<< HEAD
	 * @param email El email de la persona.
=======
	 * @param email el nuevo email de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la dirección de la persona.
	 * 
<<<<<<< HEAD
	 * @return La dirección de la persona.
=======
	 * @return la dirección de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece la dirección de la persona.
	 * 
<<<<<<< HEAD
	 * @param direccion La dirección de la persona.
=======
	 * @param direccion la nueva dirección de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Obtiene la fecha de nacimiento de la persona.
	 * 
<<<<<<< HEAD
	 * @return La fecha de nacimiento de la persona.
=======
	 * @return la fecha de nacimiento de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento de la persona.
	 * 
<<<<<<< HEAD
	 * @param fechaNacimiento La fecha de nacimiento de la persona.
=======
	 * @param fechaNacimiento la nueva fecha de nacimiento de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Obtiene el género de la persona.
	 * 
<<<<<<< HEAD
	 * @return El género de la persona.
=======
	 * @return el género de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public Sexo getGenero() {
		return genero;
	}

	/**
	 * Establece el género de la persona.
	 * 
<<<<<<< HEAD
	 * @param genero El género de la persona.
=======
	 * @param genero el nuevo género de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public void setGenero(Sexo genero) {
		this.genero = genero;
	}

	/**
	 * Obtiene la contraseña de la persona.
	 * 
<<<<<<< HEAD
	 * @return La contraseña de la persona.
=======
	 * @return la contraseña de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Establece la contraseña de la persona.
	 * 
<<<<<<< HEAD
	 * @param contrasena La contraseña de la persona.
=======
	 * @param contrasena la nueva contraseña de la persona
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

<<<<<<< HEAD
	// Métodos de validación

	/**
	 * Método para verificar la validez de un email.
	 * 
	 * @author vero
	 * @param email El email a validar.
	 * @return true si el email es válido, false si no lo es.
	 * @throws IllegalArgumentException Si el email proporcionado no es válido.
=======
	/**
	 * Valida el formato del email proporcionado.
	 * 
	 * @param email el email a validar
	 * @return true si el email es válido, false en caso contrario
	 * @throws IllegalArgumentException si el email no es válido
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
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
<<<<<<< HEAD
	 * Método para verificar la validez de un DNI.
	 * 
	 * @author vero
	 * 
	 * @param dni El DNI a validar.
	 * @return true si el DNI es válido, false si no lo es.
	 * @throws IllegalArgumentException Si el DNI proporcionado no es válido.
=======
	 * Valida el formato del DNI proporcionado.
	 * 
	 * @param dni el DNI a validar
	 * @return true si el DNI es válido, false en caso contrario
	 * @throws IllegalArgumentException si el DNI no es válido
>>>>>>> fc1612582b416ac0111ffdcef2e175404bdf72b8
	 */
	public boolean validarDNI(String dni) throws IllegalArgumentException {
		try {
			if (dni.length() == 9 && dni.substring(0, 8).matches("\\d+")) {
				String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
				char letraCalculada = letras.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
				char letraDNI = dni.charAt(8);
				if (letraCalculada == letraDNI) {
					return true;
				} else {
					throw new IllegalArgumentException("El DNI proporcionado no es válido");
				}
			}
			throw new IllegalArgumentException("El DNI proporcionado no es válido");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
