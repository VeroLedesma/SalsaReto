package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import modelo.Tipo;

class TipoTest {

	@Test
	public void testGetSetCodTipo() {
		Tipo tipo = new Tipo();

		tipo.setCodTipo(101);
		assertEquals(Integer.valueOf(101), tipo.getCodTipo());
		assertNotNull("Comprobar que el código obtenido no sea nulo", tipo.getCodTipo());
		assertTrue("El código obtenido es mayor que cero", tipo.getCodTipo() > 0);
	}

	@Test
	public void testGetSetNombreTipo() {
		Tipo tipo = new Tipo();

		tipo.setNombreTipo("Pantalón");

		assertEquals("Pantalon", tipo.getNombreTipo());
		assertNotNull("Verificar que el nombre obtenido no sea nulo", tipo.getNombreTipo());

	}

	@Test
	public void testGetSetStock() {
		Tipo tipo = new Tipo();

		tipo.setStock(50);

		assertEquals(Integer.valueOf(50), tipo.getStock());
		assertNotNull("Verificar que el stock obtenido no sea nulo", tipo.getStock());
		assertTrue("Ver que el stock obtenido es mayor o igual que cero", tipo.getStock() >= 0);
	}
}
