package modelo;

import java.time.LocalDate;

public class Persona {
	private String dni, nombre, apellido, email, direccion, contrasena, rol;
	private LocalDate fechaNacimiento;
	private Sexo genero;

	// Getters y Setters
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Sexo getGenero() {
		return genero;
	}

	public void setGenero(Sexo genero) {
		this.genero = genero;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	// Método para verificar un email
	public boolean validarEmail(String email) throws IllegalArgumentException {
		boolean correcto = false;
		try {
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			if (email.matches(emailRegex)) {
				correcto = true;
			} else {
				throw new IllegalArgumentException("El email proporcionado no es válido");
			}
		} catch (Exception e) {
			e.printStackTrace();
			correcto = false;
		}
		return correcto;
	}

	// Método para verificar un DNI
	public boolean validarDNI(String dni) throws IllegalArgumentException {
		boolean correcto = false;
		try {
			if (dni.length() == 9) {
				if (dni.substring(0, 8).matches("\\d+")) {
					String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
					char letraCalculada = letras.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
					char letraDNI = dni.charAt(8);
					if (letraCalculada == letraDNI) {
						correcto = true;
					} else {
						throw new IllegalArgumentException("El DNI proporcionado no es válido");
					}
				}
			}
			throw new IllegalArgumentException("El DNI proporcionado no es válido");
		} catch (Exception e) {
			e.printStackTrace();
			correcto = false;
		}
		return correcto;
	}

}
