package test;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import modelo.Usuario;

public class UsuarioTest {

	@Test
	void testFechaRegistro() {
		Usuario user = new Usuario();
		LocalDate fechaRegistro = LocalDate.of(2004, 04, 23);
		user.setFechaRegistro(fechaRegistro);
		// Assert
		assertEquals(fechaRegistro, user.getFechaRegistro());
	}
	
}
