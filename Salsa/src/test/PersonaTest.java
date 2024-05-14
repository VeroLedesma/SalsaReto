package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import modelo.Persona;
import modelo.Sexo;

public class PersonaTest {

	@Test
	void testSetAndGetDni() {
		Persona persona = new Persona();
		String dni = "12345678A";
		persona.setDni(dni);
		// Assert
		assertEquals(dni, persona.getDni());
	}

	@Test
	void testSetAndGetName() {
		Persona persona = new Persona();
		String nombre = "Juan";
		persona.setNombre(nombre);
		// Assert
		assertEquals(nombre, persona.getNombre());
	}

	@Test
	void testSetAndGetApellido() {
		Persona persona = new Persona();
		String apellido = "Pérez";
		persona.setApellido(apellido);
		// Assert
		assertEquals(apellido, persona.getApellido());
	}

	@Test
	void testSetAndGetEmail() {
		Persona persona = new Persona();
		String email = "juan@example.com";
		persona.setEmail(email);
		// Assert
		assertEquals(email, persona.getEmail());
	}

	@Test
	void testSetAndGetDireccion() {
		Persona persona = new Persona();
		String direccion = "Calle Principal 123";
		persona.setDireccion(direccion);
		// Assert
		assertEquals(direccion, persona.getDireccion());
	}

	@Test
	void testSetAndGetFechaNacimiento() {
		Persona persona = new Persona();
		LocalDate fechaNacimiento = LocalDate.of(1990, 5, 15);
		persona.setFechaNacimiento(fechaNacimiento);
		// Assert
		assertEquals(fechaNacimiento, persona.getFechaNacimiento());
	}

	@Test
	void testSetAndGetGenero() {
		Persona persona = new Persona();
		Sexo genero = Sexo.MASCULINO;
		persona.setGenero(genero);
		// Assert
		assertEquals(genero, persona.getGenero());
	}

	@Test
	void testSetAndGetContrasena() {
		Persona persona = new Persona();
		String contrasena = "contrasena123";
		persona.setContrasena(contrasena);
		// Assert
		assertEquals(contrasena, persona.getContrasena());
	}

	@Test
	void testNotNullDni() {
		Persona persona = new Persona();
		persona.setDni("123456789A");
		// Assert
		assertNotNull(persona.getDni());
	}

	@Test
	void testNotNullName() {
		Persona persona = new Persona();
		persona.setNombre("Juan");
		// Assert
		assertNotNull(persona.getNombre());
	}

	@Test
	void testNotNullApellido() {
		Persona persona = new Persona();
		persona.setApellido("Perez");
		// Assert
		assertNotNull(persona.getApellido());
	}

	@Test
	void testNotNullEmail() {
		Persona persona = new Persona();
		persona.setEmail("juan@example.com");
		// Assert
		assertNotNull(persona.getEmail());
	}

	@Test
	void testNotNullDireccion() {
		Persona persona = new Persona();
		persona.setDireccion("Calle Principal 123");
		// Assert
		assertNotNull(persona.getDireccion());
	}

	@Test
	void testNotNullFechaNacimiento() {
		Persona persona = new Persona();
		LocalDate fechaNacimiento = LocalDate.of(1990, 5, 15);
		persona.setFechaNacimiento(fechaNacimiento);
		// Assert
		assertNotNull(persona.getFechaNacimiento());
	}

}
